package modelo;

import java.util.ArrayList;
import java.util.List;

public class Funciones_Modelo {

    List<Usuario> listaUsuarios = new ArrayList<>();
    Usuario usuarioInicial;


    //CARGAMOS USUARIO ADMIN
    public void cargar_Usuario_Administrador(){
        String username = "admin";
        String contrasena = "1234";
        String rol = "Administrador";
        Usuario usuario = new Usuario(username,contrasena,rol);
        //listaUsuarios.add(usuario);
    }

    public Usuario login(String username, String password) {
        usuarioInicial = null;

        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                usuarioInicial = usuario ;  //si existe
                System.out.println("Cargado Usuario Prueba: "+usuarioInicial.toString());
                return usuarioInicial; // Usuario encontrado
            }
        }
        return null; // Usuario o contraseña erróneos
    }

}
