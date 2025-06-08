package controlador;

import vista.Vista_Reporte;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador_Reportes implements ActionListener {

    private final Vista_Reporte vistaReporte;
    DefaultTableModel modeloTablaReporte;

    public Controlador_Reportes(){
        this.vistaReporte = new Vista_Reporte();

        modeloTablaReporte = new DefaultTableModel(new String[]{"Asistencia", "Ventas de Tickets","Ocupacion Salas"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //asociar modelo a la tabla
        vistaReporte.tablaReporte.setModel(modeloTablaReporte);

        //Configuramos la venta para que se muestre como queremos
        vistaReporte.setContentPane(vistaReporte.JPanelRepote);
        vistaReporte.pack();
        vistaReporte.setMinimumSize(new Dimension(600, 400));
        vistaReporte.setLocationRelativeTo(null);
        vistaReporte.setVisible(true);

        //Metodos para iniciar el contenido de la vista
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

    }
}
