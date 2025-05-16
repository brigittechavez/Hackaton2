package ui;
import models.Contacto;
import services.Agenda;
import utils.Validador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class VentanaAgenda extends JFrame {
    private Agenda agenda = new Agenda();

    private JTextField txtNombre= new JTextField(10);
    private JTextField txtApellido= new JTextField(10);
    private JTextField txtTelefono= new JTextField(10);
    private JTextArea txtResultados= new JTextArea(10, 30);

    public VentanaAgenda() {
        setTitle("Agenda telefónica");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Este es el panel principal de la app
        JPanel panel = new JPanel(new BorderLayout());

        //seccion de formulario
        JPanel panelEntrada = new JPanel(new GridLayout(4, 2, 5, 5));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelEntrada.add(new JLabel("Nombre"));
        panelEntrada.add(txtNombre);

        panelEntrada.add(new JLabel("Apellido"));
        panelEntrada.add(txtApellido);

        panelEntrada.add(new JLabel("Teléfono"));
        panelEntrada.add(txtTelefono);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar  contactos");
        JButton btnLimpiar = new JButton("Limpiar");

        btnAgregar.addActionListener(this::añadirContacto);
        btnBuscar.addActionListener(this::buscarContacto);

        btnListar.addActionListener(this::mostrarContactos);
        btnLimpiar.addActionListener(this::limpiarCampos);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnListar);
        panelBotones.add(btnLimpiar);

        txtResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultados);

        panel.add(panelEntrada, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        setContentPane(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Estos métodos deben estar FUERA del constructor, al mismo nivel
    private void añadirContacto(ActionEvent e) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();

        // Validar datos (puedes usar la clase Validador si tiene métodos implementados)
        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
            txtResultados.setText("Error: Todos los campos son obligatorios");
            return;
        }

        // Crear y agregar contacto
        Contacto nuevoContacto = new Contacto(nombre, apellido, telefono);
        agenda.añadirContacto(nuevoContacto);

        // Mostrar confirmación
        txtResultados.setText("Contacto añadido correctamente");
        limpiarCampos(null);  // Limpiar campos después de agregar
    }

    private void buscarContacto(ActionEvent e) {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            txtResultados.setText("Error: Nombre y apellido son necesarios para buscar");
            return;
        }

        // Buscar contacto
        Contacto contactoEncontrado = agenda.buscaContacto(nombre, apellido);
        
        if (contactoEncontrado != null) {
            txtResultados.setText("Contacto encontrado: " + contactoEncontrado);
        } else {
            txtResultados.setText("No se encontró ningún contacto con ese nombre y apellido.");
        }
    }

    private void mostrarContactos(ActionEvent e) {
        ArrayList<Contacto> listaContactos = agenda.listarContactos();
        
        if (listaContactos.isEmpty()) {
            txtResultados.setText("La agenda está vacía.");
        } else {
            StringBuilder resultado = new StringBuilder("Lista de contactos:\n");
            for (Contacto c : listaContactos) {
                resultado.append("- ").append(c).append("\n");
            }
            txtResultados.setText(resultado.toString());
        }
    }

    private void limpiarCampos(ActionEvent e) {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtNombre.requestFocus();
    }
}