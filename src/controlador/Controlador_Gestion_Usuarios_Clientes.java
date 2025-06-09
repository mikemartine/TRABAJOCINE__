package controlador;

import modelo.Cliente;
import modelo.Funciones_Modelo;
import modelo.Membresia;
import modelo.Usuario;
import vista.Vista_Gestion_Usuarios_Clientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Controlador_Gestion_Usuarios_Clientes implements ActionListener {

    Vista_Gestion_Usuarios_Clientes vista_gestion_usuarios_clientes;
    Funciones_Modelo modeloUsuariosClientes;
    DefaultTableModel modeloTablaUsuarios;
    DefaultTableModel modeloTablaClientes;



    public Controlador_Gestion_Usuarios_Clientes() {

        vista_gestion_usuarios_clientes = new Vista_Gestion_Usuarios_Clientes();
        modeloUsuariosClientes = new Funciones_Modelo();

        iniciarlizarVentana();
        iniciarlizarTablas();

        //los clientes ya se cargaron en el constructorModeloUsuarios
        cargarClientesEnTabla();
        cargarUsuariosEnTabla();


        vista_gestion_usuarios_clientes.btnAgregarCliente.addActionListener(this);
        vista_gestion_usuarios_clientes.btnEditarCliente.addActionListener(this);
        vista_gestion_usuarios_clientes.btnEditarUsuario.addActionListener(this);
        vista_gestion_usuarios_clientes.btnAgregarUsuarios.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista_gestion_usuarios_clientes.btnAgregarCliente){
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID del cliente:"));
                boolean idExistente = modeloUsuariosClientes.getListaClientes()
                        .stream().anyMatch(c -> c.getId() == id);

                if (idExistente) {
                    JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese ID.");
                    return;
                }
                String nombre = JOptionPane.showInputDialog("Ingrese nombre:");
                String email = JOptionPane.showInputDialog("Ingrese email:");
                String tipoMembresia = JOptionPane.showInputDialog("Tipo de membresía (Normal / Premium):");
                int puntos = Integer.parseInt(JOptionPane.showInputDialog("Puntos de membresía:"));

                Cliente nuevo = new Cliente(id, nombre, email, new Membresia(tipoMembresia, puntos));
                modeloUsuariosClientes.getListaClientes().add(nuevo);
                modeloUsuariosClientes.guardarClientesEnArchivo();
                cargarClientesEnTabla(); // actualiza la tabla
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al ingresar datos. Verifica los campos.");
            }
        }
        if(e.getSource() == vista_gestion_usuarios_clientes.btnEditarCliente){
            try {
                int idBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente a editar/eliminar:"));
                Cliente clienteEncontrado = null;

                for (Cliente c : modeloUsuariosClientes.getListaClientes()) {
                    if (c.getId() == idBuscar) {
                        clienteEncontrado = c;
                        break;
                    }
                }

                if (clienteEncontrado == null) {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    return;
                }

                String[] opciones = {"Modificar", "Eliminar"};
                int opcion = JOptionPane.showOptionDialog(null, "¿Qué desea hacer con este cliente?", "Opciones",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

                if (opcion == 0) { // Modificar
                    String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", clienteEncontrado.getNombre());
                    String nuevoEmail = JOptionPane.showInputDialog("Nuevo email:", clienteEncontrado.getEmail());
                    String nuevaMembresia = JOptionPane.showInputDialog("Nuevo tipo de membresía:", clienteEncontrado.getMembresia().getNombre());
                    int nuevosPuntos = Integer.parseInt(JOptionPane.showInputDialog("Nuevos puntos:", clienteEncontrado.getMembresia().getPuntos()));

                    clienteEncontrado.setNombre(nuevoNombre);
                    clienteEncontrado.setEmail(nuevoEmail);
                    clienteEncontrado.setMembresia(new Membresia(nuevaMembresia, nuevosPuntos));

                    modeloUsuariosClientes.guardarClientesEnArchivo();
                    cargarClientesEnTabla();
                } else if (opcion == 1) { // Eliminar
                    modeloUsuariosClientes.getListaClientes().remove(clienteEncontrado);
                    modeloUsuariosClientes.guardarClientesEnArchivo();
                    cargarClientesEnTabla();

                }

                //cargarClientesEnTabla(); // actualiza tabla

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al procesar datos. Intente de nuevo.");
            }
        }
        if(e.getSource() == vista_gestion_usuarios_clientes.btnAgregarUsuarios){
            try {
                String username = JOptionPane.showInputDialog("Ingrese username:");
                String password = JOptionPane.showInputDialog("Ingrese password:");

                // Crear ComboBox con roles
                String[] roles = {"Administrador", "Operador", "Cajero"};
                JComboBox<String> comboRol = new JComboBox<>(roles);

                // Mostrar el ComboBox en un JOptionPane
                int opcion = JOptionPane.showConfirmDialog(null, comboRol, "Seleccione rol", JOptionPane.OK_CANCEL_OPTION);

                if (opcion != JOptionPane.OK_OPTION) {
                    return; // Usuario canceló
                }

                String rol = (String) comboRol.getSelectedItem();

                // Validar existencia
                boolean existe = modeloUsuariosClientes.getListaUsuarios().stream()
                        .anyMatch(u -> u.getUsername().equalsIgnoreCase(username));

                if (existe) {
                    JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese username.");
                    return;
                }

                Usuario nuevo = new Usuario(username, password, rol);
                modeloUsuariosClientes.getListaUsuarios().add(nuevo);

                // Guardar en archivo
                try (FileWriter fw = new FileWriter("usuarios.txt", true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {

                    out.println(username + "," + password + "," + rol);


                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, "Error al guardar el usuario en el archivo.");
                }

                cargarUsuariosEnTabla(); // actualiza la tabla

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al ingresar datos. Verifica los campos.");
            }
        }
        if(e.getSource() == vista_gestion_usuarios_clientes.btnEditarUsuario){
            try {
                String usernameBuscar = JOptionPane.showInputDialog("Ingrese el username del usuario a editar/eliminar:");
                Usuario usuarioEncontrado = null;

                for (Usuario u : modeloUsuariosClientes.getListaUsuarios()) {
                    if (u.getUsername().equalsIgnoreCase(usernameBuscar)) {
                        usuarioEncontrado = u;
                        break;
                    }
                }

                if (usuarioEncontrado == null) {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
                    return;
                }

                String[] opciones = {"Modificar", "Eliminar"};
                int opcion = JOptionPane.showOptionDialog(null, "¿Qué desea hacer con este usuario?", "Opciones",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

                if (opcion == 0) { // Modificar
                    String nuevoUsername = JOptionPane.showInputDialog("Nuevo username:", usuarioEncontrado.getUsername());
                    String nuevoPassword = JOptionPane.showInputDialog("Nueva contraseña:", usuarioEncontrado.getPassword());
                    String nuevoRol = JOptionPane.showInputDialog("Nuevo rol:", usuarioEncontrado.getRol());

                    usuarioEncontrado.setUsername(nuevoUsername);
                    usuarioEncontrado.setPassword(nuevoPassword);
                    usuarioEncontrado.setRol(nuevoRol);

                } else if (opcion == 1) { // Eliminar
                    modeloUsuariosClientes.getListaUsuarios().remove(usuarioEncontrado);
                }

                try (FileWriter fw = new FileWriter("usuarios.txt", false); // false para sobrescribir
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {

                    for (Usuario u : modeloUsuariosClientes.getListaUsuarios()) {
                        out.println(u.getUsername() + "," + u.getPassword() + "," + u.getRol());
                    }

                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null, "Error al guardar cambios en el archivo.");
                }


                cargarUsuariosEnTabla(); // Actualiza tabla

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al procesar datos. Intente de nuevo.");
            }
        }
    }
    public void cargarClientesEnTabla() {
        modeloTablaClientes.setRowCount(0); // LIMPIA LAS FILAS EXISTENTES
        for (Cliente c : modeloUsuariosClientes.getListaClientes()) {
            modeloTablaClientes.addRow(new Object[]{
                    c.getId(),
                    c.getNombre(),
                    c.getEmail(),
                    c.getMembresia().getNombre(),
                    c.getMembresia().getPuntos()
            });
        }
    }
    public void cargarUsuariosEnTabla() {
        modeloTablaUsuarios.setRowCount(0); // Limpia la tabla antes de llenarla
        for (Usuario u : modeloUsuariosClientes.getListaUsuarios()) {
            modeloTablaUsuarios.addRow(new Object[]{
                    u.getUsername(),
                    u.getPassword(),
                    u.getRol()
            });
        }
    }

    public void iniciarlizarTablas(){
        modeloTablaUsuarios = new DefaultTableModel(new String[]{"Username", "Password", "Rol"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTablaClientes = new DefaultTableModel(new String[]{"ID", "Nombre", "Email", "Mebresia", "Puntos"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista_gestion_usuarios_clientes.tableUsuariosInternos.setModel(modeloTablaUsuarios);
        vista_gestion_usuarios_clientes.tableClientes.setModel(modeloTablaClientes);

    }
    public void iniciarlizarVentana(){
        vista_gestion_usuarios_clientes.JPanelGestionUsuarios.setPreferredSize(new Dimension(600, 400));

        //para tener una mejor vista de la ventana la configuramos
        vista_gestion_usuarios_clientes.setContentPane(vista_gestion_usuarios_clientes.JPanelGestionUsuarios);
        vista_gestion_usuarios_clientes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vista_gestion_usuarios_clientes.pack();
        //vista_gestion_usuarios_clientes.setMinimumSize(new Dimension(200,300));
        vista_gestion_usuarios_clientes.setLocationRelativeTo(null);
        vista_gestion_usuarios_clientes.setVisible(true);

    }
}
