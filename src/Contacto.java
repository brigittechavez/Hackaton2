class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Contacto)) return false;
        Contacto otro = (Contacto) obj;
        return nombre.equalsIgnoreCase(otro.nombre) && apellido.equalsIgnoreCase(otro.apellido);
    }

    @Override
    public int hashCode() {
        return (nombre.toLowerCase() + apellido.toLowerCase()).hashCode();
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + telefono;
    }
}



class Agenda {
    private ArrayList<Contacto> contactos;
    private int capacidadMaxima;

    public Agenda(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
    }

    public Agenda() {
        this(10);
    }


    public boolean añadirContacto(Contacto c) {
        if (c.getNombre().isEmpty() || c.getApellido().isEmpty()) {
            System.out.println("El nombre y apellido no pueden estar vacíos.");
            return false;
        }
        if (contactos.size() >= capacidadMaxima) {
            System.out.println("La agenda está llena. No se pueden añadir más contactos.");
            return false;
        }
        if (existeContacto(c)) {
            System.out.println("El contacto ya existe en la agenda.");
            return false;
        }
        contactos.add(c);
        System.out.println("Contacto añadido correctamente.");
        return true;
    }


    public boolean existeContacto(Contacto c) {
        for (Contacto contacto : contactos) {
            if (contacto.equals(c)) {
                return true;
            }
        }
        return false;
    }


    public void listarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("La agenda está vacía.");
            return;
        }
        Collections.sort(contactos, Comparator
                .comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Contactos en la agenda:");
        for (Contacto c : contactos) {
            System.out.println(c);
        }
    }


    public void buscaContacto(String nombre, String apellido) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre.trim()) && c.getApellido().equalsIgnoreCase(apellido.trim())) {
                System.out.println("Teléfono: " + c.getTelefono());
                return;
            }
        }
        System.out.println("No se encontró el contacto con nombre: " + nombre + " " + apellido);
    }
}
