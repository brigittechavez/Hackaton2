package ui;

import models.Contacto;
import services.Agenda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
        JButton btnListar = new JButton("Listar contactos");
        JButton btnLimpiar = new JButton("Limpiar");

        // Estética de botones
        configurarBoton(btnAgregar, new Color(70, 130, 180));
        configurarBoton(btnBuscar, new Color(60, 179, 113));
        configurarBoton(btnListar, new Color(106, 90, 205));
        configurarBoton(btnLimpiar, new Color(178, 34, 34));

        // Eventos
        btnAgregar.addActionListener(this::agregarContacto);
        btnBuscar.addActionListener(this::buscarContacto);
        btnListar.addActionListener(this::mostrarContactos);
        btnLimpiar.addActionListener(this::limpiarCampos);

        // Agregar botones
        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
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
        setMinimumSize(new Dimension(500, 450));
        setPreferredSize(new Dimension(600, 500));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Metodo para configurar botones
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

        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
            txtResultados.setText("Error: Todos los campos son obligatorios.");
            return;
        }

        Contacto nuevo = new Contacto(nombre, apellido, telefono);
        agenda.añadirContacto(nuevo);
        txtResultados.setText("Contacto añadido correctamente.");
        limpiarCampos(null);
    }

    private void buscarContacto(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            txtResultados.setText("Error: Nombre y apellido son necesarios para buscar.");
            return;
        }

        Contacto c = agenda.buscaContacto(nombre, apellido);
        if (c != null) {
            txtResultados.setText("Contacto encontrado:\n" + c);
        } else {
            txtResultados.setText("No se encontró ningún contacto con ese nombre y apellido.");
        }
    }

    private void mostrarContactos(ActionEvent e) {
        ArrayList<Contacto> lista = agenda.listarContactos();
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

    // Método main para ejecutar la app
    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaAgenda::new);
    }
}
