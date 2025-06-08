package vista;

import modelo.Cine;
import modelo.Pelicula;

import javax.swing.*;

public class Vista_Reporte extends JFrame {
    public JPanel JPanelRepote;
    public JComboBox<Cine> cmbCine;
    public JComboBox<Pelicula> cmbPelicula;
    public JTextField txtFieldFechaInicio;
    public JTextField txtFieldFechaFinal;
    public JButton btnGenerarReporte;
    public JTable tablaReporte;
    public JLabel JLabelResultadosResumidos;
    public JButton btnExportarExcel;
    public JButton btnExportarPDF;
    public JButton btnVolver;
}
