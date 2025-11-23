package Ejemplofront.front.entities;




public class Usuario {


    private Long id;

    private String nombre;
    private String correo;
    private String tipo;   // estudiante, docente, empleado
    private String estado; // activo, moroso, vetado

    public Usuario() {}

    public Usuario(String nombre, String correo, String tipo, String estado) {
        setNombre(nombre);
        setCorreo(correo);
        setTipo(tipo);
        setEstado(estado);
    }

    // Getters y Setters con validaciones
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) {
        if (correo == null || !correo.endsWith("@correo.itm.edu.co")) {
            throw new IllegalArgumentException("El correo debe ser institucional (@correo.itm.edu.co).");
        }
        this.correo = correo;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) {
        if (!tipo.equalsIgnoreCase("estudiante") &&
                !tipo.equalsIgnoreCase("docente") &&
                !tipo.equalsIgnoreCase("empleado")) {
            throw new IllegalArgumentException("El tipo de usuario debe ser estudiante, docente o empleado.");
        }
        this.tipo = tipo;
    }

    public String getEstado() { return estado; }
    public void setEstado(String estado) {
        if (!estado.equalsIgnoreCase("activo") &&
                !estado.equalsIgnoreCase("moroso") &&
                !estado.equalsIgnoreCase("vetado")) {
            throw new IllegalArgumentException("El estado debe ser activo, moroso o vetado.");
        }
        this.estado = estado;
    }
}