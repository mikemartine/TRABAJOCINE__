package controlador;

import modelo.*;
import vista.Vista_Gestion_Cartelera;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controlador_Gestion_Cartelera implements ActionListener {
    Vista_Gestion_Cartelera vista_gestion_cartelera;
    DefaultTableModel modeloTablaCartelera;
    Cartelera cartelera;

    public Controlador_Gestion_Cartelera() {
        vista_gestion_cartelera = new Vista_Gestion_Cartelera();
        inicializarVentana();

        modeloTablaCartelera = new DefaultTableModel(new String[]{"ID", "Título", "Género", "Duración", "Clasificación", "Horario", "Sala"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vista_gestion_cartelera.table1.setModel(modeloTablaCartelera);

        vista_gestion_cartelera.btnAgregarPelicula.addActionListener(this);
        vista_gestion_cartelera.btnEditar.addActionListener(this);
        vista_gestion_cartelera.btnAsignar.addActionListener(this);

        // Cargar la cartelera al iniciar la vista
        cargarCartelera();

        // Mostrar las funciones en la tabla
        mostrarFuncionesEnTabla();
    }

    public void mostrarFuncionesEnTabla() {
        modeloTablaCartelera.setRowCount(0); // Limpiar la tabla

        for (Funcion f : cartelera.getFunciones()) {
            Pelicula p = f.getPelicula(); // Obtener la película asociada a la función
            Object[] fila = {
                    p.getId(),
                    p.getTitulo(),
                    p.getGenero(),
                    p.getDuracion() + " min",
                    p.getClasificacion(),
                    f.getHora() + " (" + f.getDia() + ")",
                    f.getSala() != null ? "Sala " + f.getSala().getNumero() : "N/A"
            };
            modeloTablaCartelera.addRow(fila); // Agregar la fila a la tabla
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista_gestion_cartelera.btnAgregarPelicula) {
            try {
                // ID
                String idStr = JOptionPane.showInputDialog(null, "Ingrese ID de la película:");
                if (idStr == null) return; // Cancelado
                int id = Integer.parseInt(idStr);

                // Título
                String titulo = JOptionPane.showInputDialog(null, "Ingrese título de la película:");
                if (titulo == null || titulo.trim().isEmpty()) return;

                // Género
                String genero = JOptionPane.showInputDialog(null, "Ingrese género de la película:");
                if (genero == null || genero.trim().isEmpty()) return;

                // Idioma
                String idioma = JOptionPane.showInputDialog(null, "Ingrese idioma de la película:");
                if (idioma == null || idioma.trim().isEmpty()) return;

                // Duración
                String duracionStr = JOptionPane.showInputDialog(null, "Ingrese duración en minutos:");
                if (duracionStr == null) return;
                int duracion = Integer.parseInt(duracionStr);

                // Clasificación
                String clasificacion = JOptionPane.showInputDialog(null, "Ingrese clasificación (ej: PG-13):");
                if (clasificacion == null || clasificacion.trim().isEmpty()) return;

                // Sinopsis
                String sinopsis = JOptionPane.showInputDialog(null, "Ingrese sinopsis:");
                if (sinopsis == null || sinopsis.trim().isEmpty()) return;

                // Crear película
                Pelicula nuevaPelicula = new Pelicula(id, titulo, genero, idioma, duracion, clasificacion, sinopsis);

                JOptionPane.showMessageDialog(null, "Película creada con éxito:\n" + nuevaPelicula.getTitulo());

                // Crear una función (se requiere una sala y un horario/día)
                String hora = "00:00"; // Simulado
                String dia = "-"; // Simulado
                //Sala salaSimulada = new Sala(1, 5, 6); // Sala simulada
                Sala salaSimulada = null; // Sala simulada

                // Crear una función para la película
                Funcion nuevaFuncion = new Funcion(nuevaPelicula, salaSimulada, hora, dia);

                // Agregar la función a la cartelera
                cartelera.agregarFuncion(nuevaFuncion);

                mostrarFuncionesEnTabla();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error en los datos numéricos (ID o duración).", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vista_gestion_cartelera.btnEditar) {
            try {
                String idStr = JOptionPane.showInputDialog(null, "Ingrese ID de la película:");
                if (idStr == null) return; // Cancelado
                int id = Integer.parseInt(idStr);

                Pelicula pelicula = obtenerPeliculaPorId(id);
                if (pelicula == null) {
                    JOptionPane.showMessageDialog(null, "Película no encontrada.");
                    return;
                }

                String[] opciones = {"Modificar", "Eliminar", "Cancelar"};
                int opcion = JOptionPane.showOptionDialog(null,
                        "¿Qué desea hacer con la película ID: " + id + "?",
                        "Editar Película",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);

                if (opcion == 0) { // Modificar
                    // Código de modificación de la película (similar al que ya tienes)
                    // Actualización de datos...

                    // Modificar el título
                    String titulo = JOptionPane.showInputDialog(null, "Nuevo título:", pelicula.getTitulo());
                    if (titulo == null || titulo.trim().isEmpty()) return;

                    // Modificar el género
                    String genero = JOptionPane.showInputDialog(null, "Nuevo género:", pelicula.getGenero());
                    if (genero == null || genero.trim().isEmpty()) return;

                    // Modificar el idioma
                    String idioma = JOptionPane.showInputDialog(null, "Nuevo idioma:", pelicula.getIdioma());
                    if (idioma == null || idioma.trim().isEmpty()) return;

                    // Modificar la duración
                    String duracionStr = JOptionPane.showInputDialog(null, "Nueva duración (minutos):", pelicula.getDuracion());
                    if (duracionStr == null) return;
                    int duracion = Integer.parseInt(duracionStr);

                    // Modificar la clasificación
                    String clasificacion = JOptionPane.showInputDialog(null, "Nueva clasificación:", pelicula.getClasificacion());
                    if (clasificacion == null || clasificacion.trim().isEmpty()) return;

                    // Modificar la sinopsis
                    String sinopsis = JOptionPane.showInputDialog(null, "Nueva sinopsis:", pelicula.getSinopsis());
                    if (sinopsis == null || sinopsis.trim().isEmpty()) return;

                    // Actualizar los datos de la película
                    pelicula.setTitulo(titulo);
                    pelicula.setGenero(genero);
                    pelicula.setIdioma(idioma);
                    pelicula.setDuracion(duracion);
                    pelicula.setClasificacion(clasificacion);
                    pelicula.setSinopsis(sinopsis);

                    JOptionPane.showMessageDialog(null, "Película modificada con éxito.");


                } else if (opcion == 1) { // Eliminar
                    // Eliminar la película de la cartelera
                    cartelera.getFunciones().removeIf(f -> f.getPelicula().getId() == id);
                    JOptionPane.showMessageDialog(null, "Película eliminada con éxito.");
                }

                mostrarFuncionesEnTabla();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == vista_gestion_cartelera.btnAsignar) {
            // Asignar película a una función en una sala (similar a lo que ya tienes)
            try {
                // Solicitar ID de la película
                String idStr = JOptionPane.showInputDialog(null, "Ingrese ID de la película a asignar:");
                if (idStr == null) return;
                int id = Integer.parseInt(idStr);

                // Buscar la película por ID
                Pelicula pelicula = GestorCinesCarteleras.getInstancia().obtenerPeliculaPorId(id);
                if (pelicula == null) {
                    JOptionPane.showMessageDialog(null, "Película no encontrada.");
                    return;
                }

                // Solicitar la hora de la función
                String hora = JOptionPane.showInputDialog(null, "Ingrese hora (formato HH:mm):");
                if (hora == null || hora.trim().isEmpty()) return;

                // Solicitar el día de la función
                String dia = JOptionPane.showInputDialog(null, "Ingrese día:");
                if (dia == null || dia.trim().isEmpty()) return;

                // Obtener lista de cines disponibles
                GestorCinesCarteleras gestor = GestorCinesCarteleras.getInstancia();
                ArrayList<Cine> listaCines = gestor.getListaCines();
                if (listaCines.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay cines disponibles.");
                    return;
                }

                // Seleccionar el cine en el cual se asignará la película
                String[] nombresCines = listaCines.stream()
                        .map(Cine::getNombre)
                        .toArray(String[]::new);
                String cineSeleccionadoNombre = (String) JOptionPane.showInputDialog(null,
                        "Seleccione un cine:",
                        "Cine",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        nombresCines,
                        nombresCines[0]);

                if (cineSeleccionadoNombre == null) return;

                // Obtener el cine seleccionado
                Cine cineSeleccionado = listaCines.stream()
                        .filter(c -> c.getNombre().equals(cineSeleccionadoNombre))
                        .findFirst()
                        .orElse(null);

                if (cineSeleccionado == null || cineSeleccionado.getListaSalas().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Cine no válido o sin salas.");
                    return;
                }

                // Seleccionar la sala dentro del cine
                String[] opcionesSalas = cineSeleccionado.getListaSalas().stream()
                        .map(sala -> "Sala " + sala.getNumero())
                        .toArray(String[]::new);

                String salaSeleccionadaStr = (String) JOptionPane.showInputDialog(null,
                        "Seleccione una sala:",
                        "Sala",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcionesSalas,
                        opcionesSalas[0]);

                if (salaSeleccionadaStr == null) return;

                // Obtener el número de la sala seleccionada
                int numeroSala = Integer.parseInt(salaSeleccionadaStr.replace("Sala ", ""));
                Sala salaSeleccionada = cineSeleccionado.getListaSalas().stream()
                        .filter(s -> s.getNumero() == numeroSala)
                        .findFirst()
                        .orElse(null);

                if (salaSeleccionada == null) {
                    JOptionPane.showMessageDialog(null, "Sala no encontrada.");
                    return;
                }

                Funcion funcionExistente = cartelera.buscarFuncionPorPeliculaId(pelicula.getId());

                if (funcionExistente != null) {
                    // Actualizar hora, día y sala
                    funcionExistente.setHora(hora);
                    funcionExistente.setDia(dia);
                    funcionExistente.setSala(salaSeleccionada);
                    JOptionPane.showMessageDialog(null, "Función actualizada exitosamente.");
                } else {
                    Funcion nuevaFuncion = new Funcion(pelicula, salaSeleccionada, hora, dia);
                    cartelera.agregarFuncion(nuevaFuncion);
                    JOptionPane.showMessageDialog(null, "Función asignada exitosamente.");
                }


                // Crear la nueva función
                //Funcion funcion = new Funcion(pelicula, salaSeleccionada, hora, dia);

                // Asignar la función a la cartelera
                //cartelera.agregarFuncion(funcion);

                // Asignar la película a la sala visualmente (opcional)
                //salaSeleccionada.asignarPelicula(pelicula);

                // Mostrar mensaje de éxito
                //JOptionPane.showMessageDialog(null, "Función asignada exitosamente.");

                // Actualizar la tabla para mostrar la nueva función
                mostrarFuncionesEnTabla();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID inválido o número de sala incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Pelicula obtenerPeliculaPorId(int id) {
        for (Funcion f : cartelera.getFunciones()) {
            if (f.getPelicula().getId() == id) {
                return f.getPelicula();
            }
        }
        return null;
    }

    private void cargarCartelera() {
        // Obtener la instancia del GestorCinesCarteleras y obtener la cartelera
        GestorCinesCarteleras gestor = GestorCinesCarteleras.getInstancia();
        cartelera = gestor.getCartelera1(); // Obtener la única cartelera

        // Si la cartelera es null (no existe), se crea una nueva
        if (cartelera == null) {
            cartelera = new Cartelera(); // Horario y día por defecto
            gestor.setCartelera1(cartelera); // Establecer la cartelera en el gestor
        }
    }

    private void inicializarVentana() {
        vista_gestion_cartelera.setContentPane(vista_gestion_cartelera.JPanelGestionCartelera);
        vista_gestion_cartelera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista_gestion_cartelera.pack();
        vista_gestion_cartelera.setTitle("Gestion Cartelera");
        vista_gestion_cartelera.setMinimumSize(new Dimension(600, 400));
        vista_gestion_cartelera.setLocationRelativeTo(null);
        vista_gestion_cartelera.setVisible(true);
    }
}
