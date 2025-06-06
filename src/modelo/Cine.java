package modelo;

import java.util.ArrayList;

public class Cine {

    private int id;
    private String nombre;
    private String ciudad;
    private String direccion;

    private ArrayList<Sala> listaSalas;

    public Cine(int id, String nombre, String ciudad, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        listaSalas = new ArrayList<>();
    }

    public void agregarSala(Sala sala) {
        listaSalas.add(sala);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ArrayList<Sala> getListaSalas() {
        return listaSalas;
    }

    public void setListaSalas(ArrayList<Sala> listaSalas) {
        this.listaSalas = listaSalas;
    }
}
