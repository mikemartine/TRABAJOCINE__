package controlador;

import vista.Vista_Configuracion_General;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador_Configuracion_General implements ActionListener {

    Vista_Configuracion_General vistaConfiguracionGeneral;

    public Controlador_Configuracion_General(){
        this.vistaConfiguracionGeneral = new Vista_Configuracion_General();

        vistaConfiguracionGeneral.setContentPane(vistaConfiguracionGeneral.JPanelConfiguracionPrincipal);
        vistaConfiguracionGeneral.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        vistaConfiguracionGeneral.pack();
        vistaConfiguracionGeneral.setMinimumSize(new Dimension(600,400));
        vistaConfiguracionGeneral.setLocationRelativeTo(null);
        vistaConfiguracionGeneral.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
