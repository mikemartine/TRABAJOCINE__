package controlador;

import modelo.Funciones_Modelo;
import modelo.Usuario;
import vista.Vista_Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador_Login implements ActionListener {

    Vista_Login vistaLogin;
    Funciones_Modelo modelo;


    public Controlador_Login() {

        vistaLogin = new Vista_Login();
        modelo = new Funciones_Modelo();

        //inicializar
        inicializarVentana();

        //Cargamos un Administrador
        modelo.cargar_Usuario_Administrador();

        //actions listeneres
        vistaLogin.btnIniciarSesion.addActionListener(this);
        vistaLogin.btnRegistrarse.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == vistaLogin.btnIniciarSesion){
            String username = vistaLogin.txtUsuario.getText();
            String password = vistaLogin.txtPassword.getText();

            Usuario usuario = modelo.login(username,password);

            if (usuario == null) {
                JOptionPane.showMessageDialog(vistaLogin, "Usuario o contrasenia Incorrectos", "Error al Iniciar", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vistaLogin, "Usuario Autenticado", null, JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Ingreso");
                new Controlador_Principal();

            }

        }

        if(e.getSource() == vistaLogin.btnRegistrarse){
            new Controlador_RegistrarUsuario();
        }

    }

    private void inicializarVentana(){
        vistaLogin.setContentPane(vistaLogin.JPanelLogin);
        vistaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vistaLogin.setSize(600, 400);
        vistaLogin.setLocationRelativeTo(null);
        vistaLogin.setVisible(true);
    }
}


