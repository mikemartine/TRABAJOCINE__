package modelo;

import java.util.ArrayList;

//esta base de datos solo sera por el momento para no tener datos quemados y ya despues podemos cargar datos reales
//desde archivos
public class BaseDatosCine {

    private static final ArrayList<Cine> listaCines = new ArrayList<>();

    //crear cines por cuidad
    public static void cargarDatosDePrueba(){

        //para cuenca
        Cine millenium = new Cine (1, "Millenium Plaza", "Cuenca","Av.Solano");
        millenium.agregarSala(new Sala(1,14,24));
        millenium.agregarSala(new Sala(2,13,23));
        millenium.agregarSala(new Sala(3,12,22));
        millenium.agregarSala(new Sala(4,11,21));
        millenium.agregarSala(new Sala(5,10,20));

        Cine mallDeRio = new Cine (2, "Mall del Rio", "Cuenca","Av. Felipe II");
        mallDeRio.agregarSala(new Sala(1,20,24));
        mallDeRio.agregarSala(new Sala(2,19,23));
        mallDeRio.agregarSala(new Sala(3,18,22));
        mallDeRio.agregarSala(new Sala(4,17,21));
        mallDeRio.agregarSala(new Sala(5,16,20));

        Cine batanShopping = new Cine(3,"Multicines Batan","Cuenca","Av.Remgio Crespo Toral");
        batanShopping.agregarSala(new Sala(1,10,20));
        batanShopping.agregarSala(new Sala(2,11,21));
        batanShopping.agregarSala(new Sala(3,12,22));
        batanShopping.agregarSala(new Sala(4,13,23));
        batanShopping.agregarSala(new Sala(5,14,24));

        //para quito

        Cine quitoCentro = new Cine(1, "Quito Centro", "Quito","Av. Amazonas");
        quitoCentro.agregarSala(new Sala(1, 30,30));
        quitoCentro.agregarSala(new Sala(2, 28,29));
        quitoCentro.agregarSala(new Sala(3, 27,28));
        quitoCentro.agregarSala(new Sala(4, 26,27));
        quitoCentro.agregarSala(new Sala(5, 25,26));

        Cine elValle =  new Cine(2,"El Valle","Quito","El condado");
        elValle.agregarSala(new Sala(1,10,10));
        elValle.agregarSala(new Sala(2,11,11));
        elValle.agregarSala(new Sala(3,12,12));
        elValle.agregarSala(new Sala(4,10,10));
        elValle.agregarSala(new Sala(5,11,11));

        Cine seisDiciembre = new Cine(3,"SuperCine 6 de Diciembre","Quito","Av.Republica");
        seisDiciembre.agregarSala(new Sala(1,10,10));
        seisDiciembre.agregarSala(new Sala(2,11,10));
        seisDiciembre.agregarSala(new Sala(3,12,10));
        seisDiciembre.agregarSala(new Sala(4,13,10));
        seisDiciembre.agregarSala(new Sala(5,14,10));

        //para guayaquil
        Cine sanMarino = new Cine(1,"San Marino Shopping","Guayaquil","Av.Libertad");
        sanMarino.agregarSala(new Sala(1,10,10));
        sanMarino.agregarSala(new Sala(2,11,9));
        sanMarino.agregarSala(new Sala(3,12,8));
        sanMarino.agregarSala(new Sala(4,13,7));
        sanMarino.agregarSala(new Sala(5,14,6));

        Cine orellana = new Cine(2,"Super Cines Orellana","Guayaquil","Av.Fransisco de Orellana");
        orellana.agregarSala(new Sala(1,20,20));
        orellana.agregarSala(new Sala(2,19,20));
        orellana.agregarSala(new Sala(3,18,20));
        orellana.agregarSala(new Sala(4,17,20));
        orellana.agregarSala(new Sala(5,16,20));

        Cine mallDelSol =  new Cine(3,"Multicines Mall del sol", "Guayaquil","Av. J. T. Marengo");
        mallDelSol.agregarSala(new Sala(1,10,10));
        mallDelSol.agregarSala(new Sala(2,9,10));
        mallDelSol.agregarSala(new Sala(3,8,10));
        mallDelSol.agregarSala(new Sala(4,7,10));
        mallDelSol.agregarSala(new Sala(5,6,10));

        //limpiar antes de cargar
        listaCines.clear();
        //Agregar a la lista todos los cines
        //CUENCA
        listaCines.add(millenium);
        listaCines.add(mallDeRio);
        listaCines.add(batanShopping);
        //QUITO
        listaCines.add(quitoCentro);
        listaCines.add(elValle);
        listaCines.add(seisDiciembre);
        //GUAYAQUIL
        listaCines.add(sanMarino);
        listaCines.add(orellana);
        listaCines.add(mallDelSol);

    }


    public static ArrayList<Cine> obtenerCinesPorCuidad(String cuidad){
            ArrayList<Cine> filtrados = new ArrayList<>();
            for(Cine cine : listaCines){
                if (cine.getCiudad().equalsIgnoreCase(cuidad)){
                    filtrados.add(cine);
                }
            }
            return filtrados;
    }

    public static ArrayList<String> obtenerCuidadesDisponibles(){
        ArrayList<String> ciudades = new ArrayList<>();
        for (Cine cine : listaCines){
            if (!ciudades.contains(cine.getCiudad())){
                ciudades.add(cine.getCiudad());
            }
        }
        return ciudades;
    }

}
