package modelo;

public class Pelicula {

    private int id;
    private String titulo;
    private String genero;
    private String idioma;
    private int duracion;
    private String clasificacion;
    private String sinopsis;

    private Sala salaAsignada; // nueva relación

    public Pelicula(int id, String titulo, String genero, String idioma, int duracion, String clasificacion, String sinopsis) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.idioma = idioma;
        this.duracion = duracion;
        this.clasificacion = clasificacion;
        this.sinopsis = sinopsis;
        this.salaAsignada = null;
    }

    public Sala getSalaAsignada() {
        return salaAsignada;
    }

    public void setSalaAsignada(Sala sala) {
        this.salaAsignada = sala;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
