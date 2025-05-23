package ui;

import models.Contacto;
import services.Agenda;
import utils.Validador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VentanaAgenda extends JFrame {
    private Agenda agenda = new Agenda();

    private JTextField txtNombre = new JTextField(10);
    private JTextField txtApellido = new JTextField(10);
    private JTextField txtTelefono = new JTextField(10);
    private JTextArea txtResultados = new JTextArea(10, 30);

    public VentanaAgenda() {
        // Look and Feel  (Nimbus)
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Agenda Telefónica");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de entrada de datos
        JPanel panelEntrada = new JPanel(new GridLayout(4, 2, 5, 5));
        panelEntrada.setBackground(new Color(240, 240, 245));
        panelEntrada.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 220), 1, true),
                        "Datos del contacto"
                ),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Etiquetas personalizadas
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre.setForeground(new Color(70, 70, 120));

        JLabel lblApellido = new JLabel("Apellido");
        lblApellido.setFont(new Font("Arial", Font.BOLD, 14));
        lblApellido.setForeground(new Color(70, 70, 120));

        JLabel lblTelefono = new JLabel("Teléfono");
        lblTelefono.setFont(new Font("Arial", Font.BOLD, 14));
        lblTelefono.setForeground(new Color(70, 70, 120));

        // Inputs
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        txtApellido.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTelefono.setFont(new Font("Arial", Font.PLAIN, 14));

        // Añadir a panelEntrada
        panelEntrada.add(lblNombre);
        panelEntrada.add(txtNombre);
        panelEntrada.add(lblApellido);
        panelEntrada.add(txtApellido);
        panelEntrada.add(lblTelefono);
        panelEntrada.add(txtTelefono);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(new Color(220, 220, 235));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // Botones
        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEliminar = new JButton("Eliminar");  // Botón para eliminar
        JButton btnListar = new JButton("Listar contactos");
        JButton btnLimpiar = new JButton("Limpiar");

        // Estética de botones
        configurarBoton(btnAgregar, new Color(70, 130, 180));
        configurarBoton(btnBuscar, new Color(60, 179, 113));
        configurarBoton(btnEliminar, new Color(220, 20, 60));  // Color rojo para eliminar
        configurarBoton(btnListar, new Color(106, 90, 205));
        configurarBoton(btnLimpiar, new Color(178, 34, 34));

        // Eventos
        btnAgregar.addActionListener(this::agregarContacto);
        btnBuscar.addActionListener(this::buscarContacto);
        btnEliminar.addActionListener(this::eliminarContacto);  // Evento para eliminar
        btnListar.addActionListener(this::mostrarContactos);
        btnLimpiar.addActionListener(this::limpiarCampos);

        // Agregar botones
        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnEliminar);  // Añadir botón al panel
        panelBotones.add(btnListar);
        panelBotones.add(btnLimpiar);

        // Área de resultados
        txtResultados.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtResultados.setEditable(false);
        txtResultados.setBackground(new Color(250, 250, 250));
        txtResultados.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        JScrollPane scrollPane = new JScrollPane(txtResultados);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Añadir todo al panel principal
        panel.add(panelEntrada, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // Configurar ventana
        setContentPane(panel);
        setMinimumSize(new Dimension(600, 450));  // Aumentado para acomodar el nuevo botón
        setPreferredSize(new Dimension(700, 500));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para configurar botones
    private void configurarBoton(JButton boton, Color colorFondo) {
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    // Métodos de acción
    private void agregarContacto(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        String telefono = txtTelefono.getText().trim();

        // Validación de campos
        if (!Validador.validarCamposNoVacios(nombre, apellido, telefono)) {
            txtResultados.setText("Error: Todos los campos son obligatorios.");
            return;
        }
        
        if (!Validador.validarNombre(nombre)) {
            txtResultados.setText("Error: El nombre debe contener solo letras y espacios.");
            return;
        }
        
        if (!Validador.validarApellido(apellido)) {
            txtResultados.setText("Error: El apellido debe contener solo letras y espacios.");
            return;
        }
        
        if (!Validador.validarTelefono(telefono)) {
            txtResultados.setText("Error: El teléfono debe contener exactamente 10 dígitos.");
            return;
        }

        // Verificar si la agenda está llena
        if (agenda.agendaLlena()) {
            txtResultados.setText("Error: La agenda está llena. No se pueden agregar más contactos.");
            return;
        }

        // Crear y agregar contacto
        Contacto nuevo = new Contacto(nombre, apellido, telefono);
        
        if (agenda.agregarContacto(nuevo)) {
            txtResultados.setText("Contacto añadido correctamente.");
            limpiarCampos(null);
        } else {
            txtResultados.setText("Error: Ya existe un contacto con ese nombre y apellido.");
        }
    }

    // Método para eliminar contactos
    private void eliminarContacto(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            txtResultados.setText("Error: Nombre y apellido son necesarios para eliminar un contacto.");
            return;
        }

        // Crear contacto para buscar coincidencia (solo con nombre y apellido)
        Contacto aEliminar = new Contacto(nombre, apellido, "");
        
        // Eliminar contacto usando el método de la clase Agenda
        if (agenda.eliminarContacto(aEliminar)) {
            txtResultados.setText("Contacto eliminado correctamente.");
            limpiarCampos(null);
        } else {
            txtResultados.setText("Error: No se encontró ningún contacto con ese nombre y apellido.");
        }
    }

    private void buscarContacto(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            txtResultados.setText("Error: Nombre y apellido son necesarios para buscar.");
            return;
        }

        // Buscar contacto por nombre y apellido
        Contacto buscado = new Contacto(nombre, apellido, "");
        List<Contacto> contactos = agenda.obtenerContactos();
        
        boolean encontrado = false;
        for (Contacto c : contactos) {
            if (c.equals(buscado)) {
                txtResultados.setText("Contacto encontrado:\n" + c);
                // Pre-rellenar el campo de teléfono para facilitar una posible actualización
                txtTelefono.setText(c.getTelefono());
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            txtResultados.setText("No se encontró ningún contacto con ese nombre y apellido.");
        }
    }

    private void mostrarContactos(ActionEvent e) {
        List<Contacto> lista = agenda.obtenerContactos();
        if (lista.isEmpty()) {
            txtResultados.setText("La agenda está vacía.");
        } else {
            StringBuilder sb = new StringBuilder("Lista de contactos:\n");
            for (Contacto c : lista) {
                sb.append("- ").append(c).append("\n");
            }
            txtResultados.setText(sb.toString());
        }
    }

    private void limpiarCampos(ActionEvent e) {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtNombre.requestFocus();
    }
}