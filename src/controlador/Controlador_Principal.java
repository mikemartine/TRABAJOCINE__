package controlador;

import vista.Vista_Principal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador_Principal implements ActionListener {

        Vista_Principal vistaPrincipal;

    public Controlador_Principal() {
        vistaPrincipal = new Vista_Principal();



        vistaPrincipal.btnGestionCartelera.addActionListener(this);
        vistaPrincipal.btnVentarReserva.addActionListener(this);
        vistaPrincipal.btnGestionUsuarios.addActionListener(this);
        vistaPrincipal.btnReportes.addActionListener(this);
        vistaPrincipal.btnControlSalas.addActionListener(this);
        vistaPrincipal.btnConfiguracionGeneral.addActionListener(this);

        inicializarVentana();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //CARTELERA
        if(e.getSource()==vistaPrincipal.btnGestionCartelera){
            new Controlador_Gestion_Cartelera();
        }
        //VENTAS RESERVA
        if(e.getSource()==vistaPrincipal.btnVentarReserva){
            new Controlador_Ventas_y_Reservas();
        }
        //GESTION USUARIOS
        if(e.getSource()==vistaPrincipal.btnGestionUsuarios){

        }
        //REPORTES
        if(e.getSource()==vistaPrincipal.btnReportes){

        }
        //CONTROL SALAS
        if(e.getSource()==vistaPrincipal.btnControlSalas){

        }
        //CONFIGURACION GENERAL
        if(e.getSource()==vistaPrincipal.btnConfiguracionGeneral){

        }
    }

    private void inicializarVentana(){
        vistaPrincipal.setContentPane(vistaPrincipal.JPanelPrincipal);
        vistaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vistaPrincipal.pack();
        vistaPrincipal.setMinimumSize(new Dimension(600, 400));
        vistaPrincipal.setLocationRelativeTo(null);

        vistaPrincipal.setVisible(true);
    }
}
