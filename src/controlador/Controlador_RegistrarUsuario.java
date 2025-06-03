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
        //modelo = new Funciones_Modelo();

        //inicializar
        inicializarVentana();
        inicializarCombo();

        //actions listeneres
        vistaRegistrarUsuario.btnRegistrar.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == vistaRegistrarUsuario.btnRegistrar){

        }

    }

    private void inicializarVentana(){
        vistaRegistrarUsuario.setContentPane(vistaRegistrarUsuario.JPanel_Registarusurario);
        vistaRegistrarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vistaRegistrarUsuario.setSize(600, 400);
        vistaRegistrarUsuario.setLocationRelativeTo(null);
        vistaRegistrarUsuario.setVisible(true);
    }
    private void inicializarCombo(){
        vistaRegistrarUsuario.comboRol.addItem("Taquilla");
        vistaRegistrarUsuario.comboRol.addItem("Administrador");
        vistaRegistrarUsuario.comboRol.addItem("Operador");

    }
}


