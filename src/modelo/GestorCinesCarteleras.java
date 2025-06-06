package modelo;

import java.util.ArrayList;

public class GestorCinesCarteleras {

    private static GestorCinesCarteleras instancia;
    private ArrayList<Cine> listaCines;
    private Cartelera cartelera1;

    // Constructor privado para evitar instanciación fuera de la clase
    private GestorCinesCarteleras() {
        listaCines = new ArrayList<>();
        cargarDatosPrueba(); // Cargar datos iniciales
    }

    // Método para obtener la instancia única (singleton)
    public static GestorCinesCarteleras getInstancia() {
        if (instancia == null) {
            instancia = new GestorCinesCarteleras();
        }
        return instancia;
    }

    // Método para cargar datos de prueba
    private void cargarDatosPrueba() {
        // Cines
        Cine cine1 = new Cine(1, "Multicines", "Cuenca", "Don Bosco");
        Cine cine2 = new Cine(2, "Cineplex", "Quito", "Centro Norte");
        listaCines.add(cine1);
        listaCines.add(cine2);

        // Películas
        Pelicula peli1 = new Pelicula(1, "Oppenheimer", "Drama", "Ingles", 180, "PG-13", "Historia del creador de la bomba atómica.");
        Pelicula peli2 = new Pelicula(2, "Toy Story", "Infantil", "Español", 150, "PG-13", "Historia de un mundo de juguetes");

        // Crear Carteleras y asignar películas
        cartelera1 = new Cartelera("18:00", "Lunes");
        cartelera1.agregarPelicula(peli1);
        cartelera1.agregarPelicula(peli2);

        //listaCarteleras.add(cartelera1);

        // Si quieres agregar más cines, carteleras o películas puedes hacerlo aquí
    }

    // Métodos para obtener los datos

    public ArrayList<Cine> getListaCines() {
        return listaCines;
    }

    public void setCartelera1(Cartelera cartelera1) {
        this.cartelera1 = cartelera1;
    }

    public Cartelera getCartelera1() {
        return cartelera1;
    }

    public Cine obtenerCinePorId(int id) {
        for (Cine cine : listaCines) {
            if (cine.getId() == id) {
                return cine;
            }
        }
        return null;
    }


    //SI EXISTE MAS CARTELERAS DEBO PONER ID EN CARTELERA PARA ESO DEBO DEVOLVER

    // Obtener película por ID
    public Pelicula obtenerPeliculaPorId(int id) {
        for (Pelicula pelicula : cartelera1.getListaPeliculas()) {
            if (pelicula.getId() == id) {
                return pelicula;
            }
        }
        return null;
    }
}