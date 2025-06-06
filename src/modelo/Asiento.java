package modelo;

public class Asiento {

    private int fila;
    private int columna;
    private String estado;
    private String asistente;

    public Asiento(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.estado = "Disponible";
        this.asistente = null;
    }
    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public String getEstado() { return estado; }
    public String getAsistente() { return asistente; }
    public void ocupar(String nombreCliente) {
        this.estado = "Ocupado";
        this.asistente = nombreCliente;
    }

    public void liberar() {
        this.estado = "Disponible";
        this.asistente = null;
    }


}
