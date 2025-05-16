package ui;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // iniciar la interfaz grafica
        SwingUtilities.invokeLater(VentanaAgenda::new);
    }
}