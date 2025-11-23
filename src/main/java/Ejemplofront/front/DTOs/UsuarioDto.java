package Ejemplofront.front.DTOs;



public class UsuarioDto {

    private Long id;


    private String nombre;


    private String correo;


    private String tipo;


    private String estado;

    // ====== Constructores ======

    // No-args constructor (NECESARIO para: new UsuarioDto())
    public UsuarioDto() {
    }

    // All-args constructor (útil si quieres construir rápido)
    public UsuarioDto(Long id, String nombre, String correo, String tipo, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.tipo = tipo;
        this.estado = estado;
    }

    // ====== Getters & Setters ======
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
