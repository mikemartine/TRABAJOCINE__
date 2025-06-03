package modelo;

public class Usuario {

    //private int id;      podemos omitir

    private String username;
    private String password;
    private String rol;


    //inicializar usuario

    public Usuario(String username, String password, String rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public Usuario() {
    }
    //setter y getter

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }
}
