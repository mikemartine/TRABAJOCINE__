package controlador;

import modelo.BaseDatosCine;
import modelo.Cine;
import modelo.Sala;
import vista.Vista_Control_Salas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controlador_Gestion_Salas implements ActionListener {

    private final Vista_Control_Salas vista;


    public Controlador_Gestion_Salas() {
        this.vista = new Vista_Control_Salas();

        inicializarVista();
        agregarEventos();

        //para tener una mejor vista de la ventana
        vista.pack();
        vista.setMinimumSize(new Dimension(600,400));
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    private void inicializarVista() {
        // Llenar combo de ciudades
        vista.cmbCiudad.addItem("Cuenca");
        vista.cmbCiudad.addItem("Quito");
        vista.cmbCiudad.addItem("Guayaquil");

        // Llenar combo de cines según ciudad seleccionada (inicial)
        actualizarComboCines("Cuenca");

        // Configurar tabla vacía
        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"ID", "Disponibilidad", "Capacidad"});
        vista.tablaSala.setModel(modelo);

        // Mostrar panel
        //se muestra la ventana centrada
        vista.setContentPane(vista.JPanelControlSalas);
    }

    private void agregarEventos() {
        vista.cmbCiudad.addActionListener(this);
        vista.cmbCine.addActionListener(this);
        vista.btnBuscar.addActionListener(this);
        vista.btnVolver.addActionListener(this);
    }

    /*
    private void agregarEventos() {
        // Evento al cambiar de ciudad
        vista.cmbCiudad.addActionListener(e -> {
            String ciudad = (String) vista.cmbCiudad.getSelectedItem();
            if (ciudad != null) {
                actualizarComboCines(ciudad);
            }
        });

        // Evento al hacer clic en "Mostrar Salas"
        vista.btnBuscar.addActionListener(e -> mostrarSalas());

        // Evento al hacer clic en "Volver"
        vista.btnVolver.addActionListener(e -> vista.dispose());
    }
    */
    private void actualizarComboCines(String ciudad) {
        vista.cmbCine.removeAllItems(); // Limpiar

        List<Cine> cines = BaseDatosCine.obtenerCinesPorCuidad(ciudad);
        for (Cine cine : cines) {
            vista.cmbCine.addItem(cine);
        }
    }

    private void mostrarSalas() {
        Cine cineSeleccionado = (Cine) vista.cmbCine.getSelectedItem();

        if (cineSeleccionado == null) {
            JOptionPane.showMessageDialog(vista, "Por favor, seleccione un cine.");
            return;
        }

        List<Sala> salas = cineSeleccionado.getListaSalas();

        DefaultTableModel modelo = new DefaultTableModel(null, new String[]{"ID", "Disponibilidad", "Capacidad"});

        for (Sala sala : salas) {
            String estado = sala.hayAsientosDisponibles() ? "Disponible" : "No disponible";
            modelo.addRow(new Object[]{
                    "Sala " + sala.getNumero(),
                    estado,
                    sala.getCapacidad()
            });
        }

        vista.tablaSala.setModel(modelo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == vista.btnBuscar) {
            mostrarSalas();
        } else if (source == vista.btnVolver) {
            vista.dispose();
        } else if (source == vista.cmbCiudad) {
            String ciudad = (String) vista.cmbCiudad.getSelectedItem();
            if (ciudad != null) {
                actualizarComboCines(ciudad);
            }
        }
    }

}
