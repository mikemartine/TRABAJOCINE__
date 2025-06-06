import controlador.Controlador_Gestion_Cartelera;
import controlador.Controlador_Login;
import controlador.Controlador_Principal;
import modelo.Cartelera;
import modelo.Cine;
import modelo.Pelicula;
import modelo.Sala;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        new Controlador_Login();
        //new Controlador_Principal();
        //new Controlador_Gestion_Cartelera();
        /*ArrayList<Cine> listaCines = new ArrayList<>();
        ArrayList<Cartelera> listaFunciones = new ArrayList<>();
        Cine cine1 = new Cine(1,"Multicines","Cuenca","Don Bosco");

        // Crear salas
        Sala sala1 = new Sala(1, 5, 6);//30 asientos
        Sala sala2 = new Sala(2, 4, 4);//16 asientos

        cine1.agregarSala(sala1);
        cine1.agregarSala(sala2);

        Pelicula peli1 = new Pelicula(1,"Oppenheimer","Drama","Ingles",180,"PG-13","Historia del creador de la bomba atómica.");
        Pelicula peli2 = new Pelicula(2,"Toy Story","Infantil","Español",150,"PG-13","Historia de un mundo de juguetes");

        //este ya seria el otro btn
        sala1.asignarPelicula(peli1);
        sala2.asignarPelicula(peli2);

        //asignar cartelera
        Cartelera cartelera = new Cartelera("19:00", "Viernes");
        cartelera.agregarPelicula(peli1);
        cartelera.agregarPelicula(peli2);
        listaFunciones.add(cartelera);
// Mostrar cartelera
        for (Pelicula p : cartelera.getListaPeliculas()) {
            System.out.println("Película: " + p.getTitulo() +
                    " | Sala: " + p.getSalaAsignada().getNumero() +
                    " | Hora: " + cartelera.getHora() +
                    " | Día: " + cartelera.getDia());
        }

        Sala sala11 = new Sala(1, 5, 8); // 5 filas, 8 columnas
        sala11.getAsientos()[0][0].ocupar("Carlos");
        sala11.getAsientos()[2][3].ocupar("Ana");
        sala11.mostrarPlanoAsientos();*/
    }


}