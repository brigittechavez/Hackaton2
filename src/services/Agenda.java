package services;
import models.Contacto;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private static final int CAPACIDAD_MAXIMA = 10;
    private List<Contacto> contactos;

    public Agenda() {
        contactos = new ArrayList<>();
    }

    public boolean agendaLlena() {
        return contactos.size() >= CAPACIDAD_MAXIMA;
    }

    public boolean agregarContacto(Contacto contacto) {
        if (agendaLlena()) {
            return false;
        }

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