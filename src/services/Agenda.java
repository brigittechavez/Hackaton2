package services;
import models.Contacto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Agenda {
    // Lista para almacenar los contactos
    private ArrayList<Contacto> contactos;
    // Tamaño máximo permitido para la agenda
    private int tamañoMax;

    // Constructor vacio
    public Agenda() {
        this(10);
    }

    // Constructor llenito
    public Agenda(int tamañoMax) {
        this.tamañoMax = tamañoMax;
        this.contactos = new ArrayList<>();
    }

    // Método que indica si la agenda está llena (no se pueden añadir más contactos)
    public boolean agendaLlena() {
        return contactos.size() >= tamañoMax;
    }

    // Método que devuelve cuántos espacios libres quedan en la agenda
    public int espacioLibres() {
        return tamañoMax - contactos.size();
    }

    // Método para añadir un contacto a la agenda
    public void añadirContacto(Contacto c) {
        // Validar que nombre y apellido no estén vacíos
        if (c.getNombre().isEmpty() || c.getApellido().isEmpty()) {
            System.out.println("Error: El nombre y apellido no pueden estar vacíos.");
            return;
        }

        // Añadir el contacto a la lista
        contactos.add(c);
        System.out.println("Contacto añadido correctanente");
    }

    // Método para verificar si un contacto existe en la agenda
    public boolean existeContacto(Contacto c) {
        for (Contacto contacto : contactos) {
            // Compara usando equals que ignora mayúsculas/minúsculas en nombre y apellido
            if (contacto.equals(c)) {
                return true;
            }
        }
        return false;
    }

    // Método para listar todos los contactos ordenados alfabéticamente por nombre y apellido
    public ArrayList<Contacto> listarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return contactos;
        }
        // Ordenar contactos ignorando mayúsculas/minúsculas
        Collections.sort(contactos, new Comparator<Contacto>() {
            @Override
            public int compare(Contacto c1, Contacto c2) {
                int cmpNombre = c1.getNombre().compareToIgnoreCase(c2.getNombre());
                if (cmpNombre != 0) return cmpNombre;
                return c1.getApellido().compareToIgnoreCase(c2.getApellido());
            }
        });

        // Imprimir contactos ordenados
        for (Contacto c : contactos) {
            System.out.println(c);
        }
        return contactos; // Añade este return
    }

    // Método para buscar un contacto por nombre y apellido y mostrar su teléfono
    public Contacto buscaContacto(String nombre, String apellido) {
        nombre = nombre.trim().toLowerCase();
        apellido = apellido.trim().toLowerCase();
        for (Contacto c : contactos) {
            if (c.getNombre().toLowerCase().equals(nombre) && c.getApellido().toLowerCase().equals(apellido)) {
                System.out.println("Teléfono de " + c.getNombre() + " " + c.getApellido() + ": " + c.getTelefono());
                return c; // Retorna el contacto encontrado, no contactoEncontrado
            }
        }
        System.out.println("Contacto no encontrado.");
        return null; // Añade este return
    }

    // Método para eliminar un contacto de la agenda
    public void eliminarContacto(Contacto c) {
        if (contactos.remove(c)) {
            System.out.println("Contacto eliminado correctamente.");
        } else {
            System.out.println("El contacto no existe en la agenda.");
        }
    }

    // Método para modificar el teléfono de un contacto existente
    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        nombre = nombre.trim().toLowerCase();
        apellido = apellido.trim().toLowerCase();
        for (Contacto c : contactos) {
            if (c.getNombre().toLowerCase().equals(nombre) && c.getApellido().toLowerCase().equals(apellido)) {
                c.setTelefono(nuevoTelefono);
                System.out.println("Teléfono modificado correctamente.");
                return;
            }
        }
        System.out.println("El contacto no existe en la agenda.");
    }

    public ArrayList<Contacto> getContactos() {
        return contactos;
    }

}