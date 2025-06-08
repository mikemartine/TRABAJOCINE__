package controlador;

import modelo.Funciones_Modelo;
import modelo.Usuario;
import vista.Vista_Login;
import vista.Vista_RegistrarUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador_RegistrarUsuario implements ActionListener {

    Vista_RegistrarUsuario vistaRegistrarUsuario;
    Funciones_Modelo modelo;


    public Controlador_RegistrarUsuario() {

        vistaRegistrarUsuario = new Vista_RegistrarUsuario();
        modelo = new Funciones_Modelo();

        //inicializar
        inicializarVentana();
        inicializarCombo();

        //actions listeneres
        vistaRegistrarUsuario.btnRegistrar.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == vistaRegistrarUsuario.btnRegistrar){
            String username = vistaRegistrarUsuario.txtUsername.getText().trim();
            String password = vistaRegistrarUsuario.txtPassword.getText().trim();
            String rol = vistaRegistrarUsuario.comboRol.getSelectedItem().toString();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
                return;
            }

            Usuario nuevoUsuario = new Usuario(username, password, rol);

            if (modelo.registrarUsuario(nuevoUsuario)) {
                JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
                vistaRegistrarUsuario.dispose(); // Cierra la ventana
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar usuario.");
            }

        }

    }

    private void inicializarVentana(){
        /*vistaRegistrarUsuario.setContentPane(vistaRegistrarUsuario.JPanel_Registarusurario);
        vistaRegistrarUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vistaRegistrarUsuario.setSize(600, 400);
        vistaRegistrarUsuario.setLocationRelativeTo(null);*/
        vistaRegistrarUsuario.setVisible(true);
    }
    private void inicializarCombo(){
        vistaRegistrarUsuario.comboRol.addItem("Taquilla");
        vistaRegistrarUsuario.comboRol.addItem("Administrador");
        vistaRegistrarUsuario.comboRol.addItem("Operador");

    }
}


