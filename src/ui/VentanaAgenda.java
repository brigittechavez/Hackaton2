package ui;
import models.Contacto;
import services.Agenda;
import utils.Validador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaAgenda {
    private Agenda agenda = new Agenda();

    private JTextField txtNombre= new JTextField(10);
    private JTextField txtApellido= new JTextField(10);
    private JTextField txtTelefono= new JTextField(10);
    private JTextArea txtResultados= new JTextArea(10, 30);

    public VentanaAgenda() {
        setTitle("Agenda telefónica");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Este es erl panel principal de la app
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

        //panelEntrada.add(new JLabel("Nombre"));
        //panelEntrada.add(txtNombre);


        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar  contactos");
        JButton btnLimpiar = new JButton("Limpiar");

        btnAgregar.addActionListener(this::agregarContacto);
        btnBuscar.addActionListener(this::buscarContacto);

        btnListar.addActionListener(this::mostrarContatos);
        btnListar.addActionListener(e-> limpiarCampos());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnListar);
        panelBotones.add(btnLimpiar);




    }


}
