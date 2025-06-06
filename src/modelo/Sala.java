package modelo;

public class Sala {

    private int numero;
    private int capacidad;
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
    public Asiento[][] getAsientos() {
        return asientos;
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

    public int getNumero() {
        return numero;
    }
}
