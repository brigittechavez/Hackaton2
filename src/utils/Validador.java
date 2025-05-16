package utils;

public final class Validador {

    private Validador() {}

    public static boolean validarNombre(String nombre) {
        return nombre != null && !nombre.trim().isEmpty() && nombre.matches("^[a-zA-Z\\s]+$");
    }
    
    public static boolean validarApellido(String apellido) {
        return apellido != null && !apellido.trim().isEmpty() && apellido.matches("^[a-zA-Z\\s]+$");
    }

    public static boolean validarTelefono(String telefono) {
        return telefono != null && telefono.matches("^\\d{10}$");
    }
    
    public static boolean validarCampoNoVacio(String campo) {
        return campo != null && !campo.trim().isEmpty();
    }
    
    public static boolean validarCamposNoVacios(String... campos) {
        for (String campo : campos) {
            if (!validarCampoNoVacio(campo)) {
                return false;
            }
        }
        return true;
    }
}