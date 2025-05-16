
package models;

public class Contacto {
    private String nombre;
    private String apellido; // Cambiado de correo a apellido
    private String telefono;

    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre.trim();
        this.apellido = apellido.trim(); // Ahora es apellido
        this.telefono = telefono.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + telefono;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Contacto)) return false;
        Contacto otro = (Contacto) obj;

        return nombre.equalsIgnoreCase(otro.nombre) &&
                apellido.equalsIgnoreCase(otro.apellido);
    }

    @Override
    public int hashCode() {
        return (nombre.toLowerCase() + apellido.toLowerCase()).hashCode();
    }
}