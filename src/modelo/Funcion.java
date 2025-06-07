package modelo;

public class Funcion {

    private Pelicula pelicula;
    private Sala sala;
    private String hora;
    private String dia;

    public Funcion(Pelicula pelicula, Sala sala, String hora, String dia) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.hora = hora;
        this.dia = dia;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public Sala getSala() {
        return sala;
    }

    public String getHora() {
        return hora;
    }

    public String getDia() {
        return dia;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}
