package services;
import models.Contacto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Agenda {
    private List<Contacto> contactos;

    public Agenda() {
        contactos = new ArrayList<>();
    }

    public boolean agregarContacto(Contacto contacto) {
        if (!contactos.contains(contacto)) {
            contactos.add(contacto);
            return true;
        }
        return false;
    }

    public boolean eliminarContacto(Contacto contacto) {
        return contactos.remove(contacto);
    }


    public List<Contacto> obtenerContactos() {
        return new ArrayList<>(contactos); // Copia defensiva
    }
}