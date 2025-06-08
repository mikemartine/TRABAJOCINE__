package modelo;

public class Cliente {

    private int id;
    private String nombre;
    private String email;
    private Membresia membresia;

    public Cliente(int id, String nombre, String email, Membresia membresia) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.membresia = membresia;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }
}
