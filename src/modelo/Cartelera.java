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

    // Buscar película por ID
    public Pelicula buscarPeliculaPorId(int id) {
        for (Pelicula pelicula : listaPeliculas) {
            if (pelicula.getId() == id) {
                return pelicula;
            }
        }
        return null; // Si no se encuentra la película
    }

    // Eliminar película por ID
    public boolean eliminarPelicula(int id) {
        return listaPeliculas.removeIf(pelicula -> pelicula.getId() == id);
    }

    public String getHora() {return hora;}
    public String getDia() {return dia;}

}
