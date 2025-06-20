package modelo;

public class Sala {

    private int numero;
    private int capacidad;
    private int filas;
    private int columnas;
    private Asiento[][] asientos;
    private Pelicula peliculaAsignada;

    public void asignarPelicula(Pelicula pelicula) {
        this.peliculaAsignada = pelicula;
        pelicula.setSalaAsignada(this);
    }

    public Sala(int numero, int filas, int columnas){
        this.numero = numero;
        this.capacidad = filas * columnas;
        this.asientos = new Asiento[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                asientos[i][j] = new Asiento(i + 1, j + 1);
            }
        }
    }

    public boolean hayAsientosDisponibles(){
        for(int i = 0; i < filas ; i++ ){
            for (int j = 0; j < columnas; j++){
                if (!asientos[i][j].getEstado().equalsIgnoreCase("Ocupado")){
                    return true;
                }
            }
        }
        return false;
    }

    public String getEstado(){
        return hayAsientosDisponibles() ? "Disponible" : "Ocupado";
    }


    //Metodos getters

    public int getNumero() {
        return numero;
    }

    public int getCapacidad(){
        return capacidad;
    }

    public Asiento[][] getAsientos() {
        return asientos;
    }

    public Pelicula getPeliculaAsignada(){
        return peliculaAsignada;
    }

    public int getFilas(){
        return filas;
    }

    public int getColumnas(){
        return columnas;
    }

    @Override
    public String toString(){
        return "Sala" + numero + " - " + getEstado();
    }


    public void mostrarPlanoAsientos() {
        int filas = asientos.length;
        int columnas = asientos[0].length;

        // Mostrar encabezado de columnas
        System.out.print("   "); // espacio para el número de fila
        for (int col = 1; col <= columnas; col++) {
            System.out.printf("  %2d", col); // columna con dos dígitos
        }
        System.out.println();

        // Mostrar cada fila con número y estado de asientos
        for (int i = 0; i < filas; i++) {
            System.out.printf("%2d ", i + 1); // número de fila (comienza en 1)
            for (int j = 0; j < columnas; j++) {
                String estado = asientos[i][j].getEstado();
                if (estado.equalsIgnoreCase("Ocupado")) {
                    System.out.print(" [O]");
                } else {
                    System.out.print(" [-]");
                }
            }
            System.out.println();
        }
    }


}
