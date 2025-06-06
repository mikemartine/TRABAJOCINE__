package modelo;

import java.util.ArrayList;

public class Cartelera {

    private String hora;
    private String dia;

    ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

    public Cartelera(String hora, String dia) {
        this.hora = hora;
        this.dia = dia;
    }

    public void agregarPelicula(Pelicula pelicula) {
        listaPeliculas.add(pelicula);
    }

    public ArrayList<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public String getHora() {
        return hora;
    }

    public String getDia() {
        return dia;
    }

}
