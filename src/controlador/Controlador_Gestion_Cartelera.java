package controlador;

import vista.Vista_Gestion_Cartelera;
import vista.Vista_Principal;

import javax.swing.*;
import java.awt.*;

public class Controlador_Gestion_Cartelera {
    Vista_Gestion_Cartelera vista_gestion_cartelera;

    public Controlador_Gestion_Cartelera() {
        vista_gestion_cartelera = new Vista_Gestion_Cartelera();


        inicializarVentana();
    }
    private void inicializarVentana(){
        vista_gestion_cartelera.setContentPane(vista_gestion_cartelera.JPanelGestionCartelera);
        vista_gestion_cartelera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista_gestion_cartelera.pack();
        vista_gestion_cartelera.setMinimumSize(new Dimension(600, 400));
        vista_gestion_cartelera.setLocationRelativeTo(null);

        vista_gestion_cartelera.setVisible(true);
    }
}
