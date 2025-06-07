package controlador;

import modelo.Cine;
import modelo.Funcion;
import modelo.GestorCinesCarteleras;
import modelo.Sala;
import vista.Vista_Ventas_y_Reservas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class Controlador_Ventas_y_Reservas implements ActionListener {

    Vista_Ventas_y_Reservas vistaVentasYReservas;

    public Controlador_Ventas_y_Reservas() {
        vistaVentasYReservas = new Vista_Ventas_y_Reservas();
        iniciarlizarComboCines();
        //iniciarlizarComboHoraDependiendoSala();
        inicializarVentana();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void inicializarVentana(){
        vistaVentasYReservas.setContentPane(vistaVentasYReservas.JPanelVentaryReservas);
        vistaVentasYReservas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // vistaVentasYReservas.pack();
        vistaVentasYReservas.setMinimumSize(new Dimension(600, 400));
        vistaVentasYReservas.setLocationRelativeTo(null);

        vistaVentasYReservas.setVisible(true);
    }

    public void inicializarComboHoraDependiendoSala(Sala sala) {
        vistaVentasYReservas.comboHora.removeAllItems();

        vistaVentasYReservas.comboHora.addItem("Seleccione Hora");

        GestorCinesCarteleras gestor = GestorCinesCarteleras.getInstancia();
        ArrayList<Funcion> funciones = gestor.getCartelera1().getFunciones();

        for (Funcion funcion : funciones) {
            if (funcion.getSala() != null && funcion.getSala().getNumero() == sala.getNumero()) {
                vistaVentasYReservas.comboHora.addItem(funcion.getHora() + " (" + funcion.getDia() + ")");
            }
        }

        // Si no hay funciones
        if (vistaVentasYReservas.comboHora.getItemCount() == 0) {
            vistaVentasYReservas.comboHora.addItem("No hay funciones");
        }
    }


    public void iniciarlizarComboCines() {

        // Obtener la instancia del GestorCinesCarteleras
        GestorCinesCarteleras gestorCinesCarteleras = GestorCinesCarteleras.getInstancia();

        // Obtener la lista de cines
        ArrayList<Cine> listaCines = gestorCinesCarteleras.getListaCines();

        // Limpiar el combo box por si ya tiene elementos
        vistaVentasYReservas.comboCines.removeAllItems();

        //
        vistaVentasYReservas.comboCines.addItem("Seleciona Cine");
        vistaVentasYReservas.comboSala.addItem("Selecione Sala");
        vistaVentasYReservas.comboHora.addItem("Selecione Hora");

        // Añadir los nombres de los cines al combo box
        for (Cine cine : listaCines) {
            vistaVentasYReservas.comboCines.addItem(cine.getNombre());  // Agregar solo el nombre del cine
        }

        // Añadir un listener para actualizar las salas cuando se seleccione un cine
        vistaVentasYReservas.comboCines.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                // Obtener el cine seleccionado
                String cineSeleccionado = (String) vistaVentasYReservas.comboCines.getSelectedItem();

                // Obtener la referencia del cine correspondiente
                Cine cine = obtenerCinePorNombre(cineSeleccionado);

                if (cine != null) {
                    // Llamar a la función para inicializar las salas del cine
                    inicializarComboSalas(cine);
                    //vistaVentasYReservas.comboHora.removeAllItems();
                }
            }
        });

    }
    private Cine obtenerCinePorNombre(String nombreCine) {
        GestorCinesCarteleras gestorCinesCarteleras = GestorCinesCarteleras.getInstancia();
        for (Cine cine : gestorCinesCarteleras.getListaCines()) {
            if (cine.getNombre().equals(nombreCine)) {
                return cine;
            }
        }
        return null; // En caso de no encontrar el cine
    }
    public void inicializarComboSalas(Cine cine) {
        // Obtener las salas del cine seleccionado
        ArrayList<Sala> salas = cine.getListaSalas();  // Supongo que la clase Cine tiene un método getSalas()

        // Limpiar el combo box de salas
        vistaVentasYReservas.comboSala.removeAllItems();
        //vistaVentasYReservas.comboHora.removeAllItems();

        vistaVentasYReservas.comboSala.addItem("Seleccione Sala");

        // Añadir las salas al combo box
        for (Sala sala : salas) {
            vistaVentasYReservas.comboSala.addItem("Sala " + sala.getNumero());  // Agregar nombre de la sala
        }

        // Agrega un listener para cargar las horas según la sala seleccionada
        vistaVentasYReservas.comboSala.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String salaSeleccionada = (String) vistaVentasYReservas.comboSala.getSelectedItem();
                if (salaSeleccionada != null && salaSeleccionada.startsWith("Sala ")) {
                    int numeroSala = Integer.parseInt(salaSeleccionada.replace("Sala ", ""));
                    Sala sala = cine.getListaSalas().stream()
                            .filter(s -> s.getNumero() == numeroSala)
                            .findFirst()
                            .orElse(null);

                    if (sala != null) {
                        inicializarComboHoraDependiendoSala(sala);
                    }
                } else {
                // Si selecciona "Seleccione Sala", limpiar comboHora
                vistaVentasYReservas.comboHora.removeAllItems();
                vistaVentasYReservas.comboHora.addItem("Seleccione Hora");
                }
            }
        });


    }

}
