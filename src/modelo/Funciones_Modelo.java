package modelo;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Funciones_Modelo {

    List<Usuario> listaUsuarios = new ArrayList<>();
    Usuario usuarioInicial;

    List<Cliente> listaClientes = new ArrayList<>();
    Cliente clienteInicial;

    public Funciones_Modelo() {
        File archivoClientes = new File("clientes.txt");
        if (!archivoClientes.exists()) {
            cargas_Clientes();
            guardarClientesEnArchivo();
            System.out.println("Clientes iniciales guardados en clientes.txt.");
        } else {
            cargarClientesDesdeArchivo(); // solo si ya existe
        }

        cargarUsuariosDesdeArchivo();

    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void cargarUsuariosDesdeArchivo() {
        listaUsuarios.clear(); // limpiar por si acaso
        File archivo = new File("usuarios.txt");

        if (!archivo.exists()) {
            System.out.println("usuarios.txt no existe, se creará cuando se registre un usuario.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    Usuario usuario = new Usuario(partes[0], partes[1], partes[2]);
                    listaUsuarios.add(usuario);
                }
            }
            System.out.println("Usuarios cargados desde archivo.");
        } catch (IOException e) {
            System.out.println("Error al leer usuarios.txt: " + e.getMessage());
        }
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
        Usuario usuario0 = new Usuario("admin","1234","Administrador");
        Usuario usuario = new Usuario("David","jodfof.sdfsd","Administrador");
        Usuario usuario2 = new Usuario("Josue","jiiefsfd","Taquilla");
        Usuario usuario3 = new Usuario("Mateo","ofgfgddd","Operador");
        Usuario usuario4 = new Usuario("Esteban","apppasd","Operador");
        listaUsuarios.add(usuario0);
        listaUsuarios.add(usuario); listaUsuarios.add(usuario2); listaUsuarios.add(usuario3); listaUsuarios.add(usuario4);
    }

    //LOGIN CON TXT
    public Usuario loginDesdeArchivo(String username, String password) {
        String linea;
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String userArchivo = partes[0];
                    String passArchivo = partes[1];
                    String rolArchivo = partes[2];

                    if (userArchivo.equals(username) && passArchivo.equals(password)) {
                        usuarioInicial = new Usuario(userArchivo, passArchivo, rolArchivo);
                        System.out.println("Login exitoso desde archivo: " + usuarioInicial.getUsername()+" Su Rol es: "+usuarioInicial.getRol());
                        return usuarioInicial;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer usuarios.txt: " + e.getMessage());
        }
        return null; // No se encontró el usuario
    }
    public boolean registrarUsuario(Usuario nuevoUsuario) {
        try (FileWriter writer = new FileWriter("usuarios.txt", true)) { // true → modo append
            String linea = nuevoUsuario.getUsername() + "," +
                    nuevoUsuario.getPassword() + "," +
                    nuevoUsuario.getRol() + "\n";
            writer.write(linea);
            listaUsuarios.add(nuevoUsuario); // también lo agregas a la lista en memoria
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void guardarClientesEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt"))) {
            for (Cliente c : listaClientes) {
                writer.write(c.getId() + "," + c.getNombre() + "," + c.getEmail() + "," +
                        c.getMembresia().getNombre() + "," + c.getMembresia().getPuntos());
                writer.newLine();
            }
            System.out.println("Clientes guardados en clientes.txt");
        } catch (IOException e) {
            System.out.println("Error al guardar clientes: " + e.getMessage());
        }
    }
    public void cargarClientesDesdeArchivo() {
        listaClientes.clear(); // Limpiar lista actual
        File archivo = new File("clientes.txt");

        if (!archivo.exists()) {
            System.out.println("clientes.txt no existe, se creará cuando se agregue un cliente.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String email = partes[2];
                    String nombreMembresia = partes[3];
                    int puntos = Integer.parseInt(partes[4]);

                    Cliente cliente = new Cliente(id, nombre, email, new Membresia(nombreMembresia, puntos));
                    listaClientes.add(cliente);
                }
            }
            System.out.println("Clientes cargados desde archivo.");
        } catch (IOException e) {
            System.out.println("Error al leer clientes.txt: " + e.getMessage());
        }
    }



}
