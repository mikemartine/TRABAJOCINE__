package modelo;

import java.util.ArrayList;
import java.util.List;

public class Funciones_Modelo {

    List<Usuario> listaUsuarios = new ArrayList<>();
    Usuario usuarioInicial;

    List<Cliente> listaClientes = new ArrayList<>();
    Cliente clienteInicial;

    public Funciones_Modelo() {
        cargas_Clientes(); // cargar automáticamente
        cargas_Usuarios();
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }


    //CARGAMOS CLientes para la tabla
    public void cargas_Clientes(){
        Cliente cliente = new Cliente(1,"Carlos","carlos@gmail.com",new Membresia("",0));
        Cliente cliente2 = new Cliente(2,"Juan","juan@gmail.com",new Membresia("Normal",100));
        Cliente cliente3 = new Cliente(3,"Felipe","felipe@gmail.com",new Membresia("Normal",100));
        Cliente cliente4 = new Cliente(4,"Esteban","esteban@gmail.com",new Membresia("Premiun",500));
        Cliente cliente5 = new Cliente(5,"Julio","julio@gmail.com",new Membresia("Premiun",500));
        listaClientes.add(cliente); listaClientes.add(cliente2); listaClientes.add(cliente3); listaClientes.add(cliente4); listaClientes.add(cliente5);
    }
    public void cargas_Usuarios(){
        Usuario usuario = new Usuario("David","jodfof.sdfsd","Administrador");
        Usuario usuario2 = new Usuario("Josue","jiiefsfd","Taquilla");
        Usuario usuario3 = new Usuario("Mateo","ofgfgddd","Operador");
        Usuario usuario4 = new Usuario("Esteban","apppasd","Operador");
        listaUsuarios.add(usuario); listaUsuarios.add(usuario2); listaUsuarios.add(usuario3); listaUsuarios.add(usuario4);

    }




    //CARGAMOS USUARIO ADMIN
    public void cargar_Usuario_Administrador(){
        String username = "admin";
        String contrasena = "1234";
        String rol = "Administrador";
        Usuario usuario = new Usuario(username,contrasena,rol);
        listaUsuarios.add(usuario);
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
