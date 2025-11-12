package Ejemplofront.front.entities;





public class Libro {


    private Long id;


    private String titulo;


    private String autor;


    private int anioPublicacion;


    private String categoria;


    private String estado; // disponible, prestado, reservado, deteriorado

    // 🔹 Constructor vacío (obligatorio para JPA)
    public Libro() {}

    // 🔹 Constructor con parámetros
    public Libro(String titulo, String autor, int anioPublicacion, String categoria, String estado) {
        setTitulo(titulo);
        setAutor(autor);
        setAnioPublicacion(anioPublicacion);
        setCategoria(categoria);
        setEstado(estado);
    }

    // ===== Getters y Setters con validaciones =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        this.titulo = titulo;
    }

    public String getAutor() { return autor; }
    public void setAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede estar vacío");
        }
        this.autor = autor;
    }

    public int getAnioPublicacion() { return anioPublicacion; }
    public void setAnioPublicacion(int anioPublicacion) {
        if (anioPublicacion <= 0) {
            throw new IllegalArgumentException("El año de publicación debe ser mayor que 0");
        }
        this.anioPublicacion = anioPublicacion;
    }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía");
        }
        this.categoria = categoria;
    }

    public String getEstado() { return estado; }
    public void setEstado(String estado) {
        if (estado == null ||
                !(estado.equalsIgnoreCase("disponible") ||
                        estado.equalsIgnoreCase("prestado") ||
                        estado.equalsIgnoreCase("reservado") ||
                        estado.equalsIgnoreCase("deteriorado"))) {
            throw new IllegalArgumentException("El estado debe ser disponible, prestado, reservado o deteriorado");
        }
        this.estado = estado.toLowerCase();
    }
}
