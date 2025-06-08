package modelo;

import java.util.ArrayList;

public class Cartelera {

   private String hora;
    private String dia;

    ArrayList<Pelicula> listaPeliculas = new ArrayList<>();


    private ArrayList<Funcion> funciones;
    public Cartelera() {
        this.funciones = new ArrayList<>();
    }

    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
    }

    public ArrayList<Funcion> getFunciones() {
        return funciones;
    }
    public Funcion buscarFuncionPorPeliculaId(int idPelicula) {
        for (Funcion f : funciones) {
            if (f.getPelicula().getId() == idPelicula) return f;
        }
        return null;
    }



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

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {return hora;}
    public String getDia() {return dia;}

}
