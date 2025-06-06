package controlador;

import modelo.*;
import vista.Vista_Gestion_Cartelera;
import vista.Vista_Principal;

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


        modeloTablaCartelera = new DefaultTableModel(new String[]{"ID", "Título", "Género", "Duración", "Clasificación", "Horario", "Sala"},0)
        {
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

        // Mostrar las películas en la tabla
        mostrarPeliculasEnTabla(cartelera);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista_gestion_cartelera.btnAgregarPelicula){

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

                cartelera.agregarPelicula(nuevaPelicula);
                mostrarPeliculasEnTabla(cartelera);

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

                GestorCinesCarteleras gestor = GestorCinesCarteleras.getInstancia();
                Cartelera cartelera = gestor.getCartelera1(); // Obtener la cartelera única
                Pelicula pelicula = gestor.obtenerPeliculaPorId(id);
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
                    String titulo = JOptionPane.showInputDialog(null, "Nuevo título:", pelicula.getTitulo());
                    if (titulo == null || titulo.trim().isEmpty()) return;

                    String genero = JOptionPane.showInputDialog(null, "Nuevo género:", pelicula.getGenero());
                    if (genero == null || genero.trim().isEmpty()) return;

                    String idioma = JOptionPane.showInputDialog(null, "Nuevo idioma:", pelicula.getIdioma());
                    if (idioma == null || idioma.trim().isEmpty()) return;

                    String duracionStr = JOptionPane.showInputDialog(null, "Nueva duración (minutos):", pelicula.getDuracion());
                    if (duracionStr == null) return;
                    int duracion = Integer.parseInt(duracionStr);

                    String clasificacion = JOptionPane.showInputDialog(null, "Nueva clasificación:", pelicula.getClasificacion());
                    if (clasificacion == null || clasificacion.trim().isEmpty()) return;

                    String sinopsis = JOptionPane.showInputDialog(null, "Nueva sinopsis:", pelicula.getSinopsis());
                    if (sinopsis == null || sinopsis.trim().isEmpty()) return;

                    // Actualizar datos
                    pelicula.setTitulo(titulo);
                    pelicula.setGenero(genero);
                    pelicula.setIdioma(idioma);
                    pelicula.setDuracion(duracion);
                    pelicula.setClasificacion(clasificacion);
                    pelicula.setSinopsis(sinopsis);

                    JOptionPane.showMessageDialog(null, "Película modificada con éxito.");
                } else if (opcion == 1) { // Eliminar
                    // Eliminar la película de la cartelera
                    cartelera.getListaPeliculas().removeIf(p -> p.getId() == id);
                    JOptionPane.showMessageDialog(null, "Película eliminada con éxito.");
                }

                // Actualizar tabla
                mostrarPeliculasEnTabla(cartelera);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID o duración inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void cargarCartelera() {
        // Obtener la instancia del GestorCinesCarteleras y obtener la cartelera
        GestorCinesCarteleras gestor = GestorCinesCarteleras.getInstancia();
        cartelera = gestor.getCartelera1(); // Obtener la única cartelera

        // Si la cartelera es null (no existe), se crea una nueva
        if (cartelera == null) {
            cartelera = new Cartelera("18:00", "Lunes"); // Horario y día por defecto
            gestor.setCartelera1(cartelera); // Establecer la cartelera en el gestor
        }
    }

    public void mostrarPeliculasEnTabla(Cartelera cartelera) {
        modeloTablaCartelera.setRowCount(0);

        for (Pelicula p : cartelera.getListaPeliculas()) {
            Object[] fila = {
                    p.getId(),
                    p.getTitulo(),
                    p.getGenero(),
                    p.getDuracion() + " min",
                    p.getClasificacion(),
                    (cartelera.getHora() != null ? cartelera.getHora() : "Sin hora") +
                            " (" + (cartelera.getDia() != null ? cartelera.getDia() : "Sin día") + ")",
                    p.getSalaAsignada() != null ? "Sala " + p.getSalaAsignada().getNumero() : "N/A"
            };
            modeloTablaCartelera.addRow(fila);

            }
        }
    private void inicializarVentana(){
        vista_gestion_cartelera.setContentPane(vista_gestion_cartelera.JPanelGestionCartelera);
        vista_gestion_cartelera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista_gestion_cartelera.pack();
        vista_gestion_cartelera.setTitle("Gestion Cartelera");
        vista_gestion_cartelera.setMinimumSize(new Dimension(600, 400));
        vista_gestion_cartelera.setLocationRelativeTo(null);

        vista_gestion_cartelera.setVisible(true);
    }


    public void cargarDatosPrueba(){
        ArrayList<Cine> listaCines = new ArrayList<>();
        ArrayList<Cartelera> listaFunciones = new ArrayList<>();
        Cine cine1 = new Cine(1,"Multicines","Cuenca","Don Bosco");

        // Crear salas
        Sala sala1 = new Sala(1, 5, 6);//30 asientos
        Sala sala2 = new Sala(2, 4, 4);//16 asientos

        cine1.agregarSala(sala1);
        cine1.agregarSala(sala2);

        Pelicula peli1 = new Pelicula(1,"Oppenheimer","Drama","Ingles",180,"PG-13","Historia del creador de la bomba atómica.");
        Pelicula peli2 = new Pelicula(2,"Toy Story","Infantil","Español",150,"PG-13","Historia de un mundo de juguetes");

        //este ya seria el otro btn
        sala1.asignarPelicula(peli1);
        sala2.asignarPelicula(peli2);

        //asignar cartelera
        cartelera = new Cartelera(null, null); // sin hora ni día

        cartelera.agregarPelicula(peli1);
        cartelera.agregarPelicula(peli2);
        listaFunciones.add(cartelera);
// Mostrar cartelera
        /*for (Pelicula p : cartelera.getListaPeliculas()) {
            System.out.println("Película: " + p.getTitulo() +
                    " | Sala: " + p.getSalaAsignada().getNumero() +
                    " | Hora: " + cartelera.getHora() +
                    " | Día: " + cartelera.getDia());
        }

        Sala sala11 = new Sala(1, 5, 8); // 5 filas, 8 columnas
        sala11.getAsientos()[0][0].ocupar("Carlos");
        sala11.getAsientos()[2][3].ocupar("Ana");
        sala11.mostrarPlanoAsientos();*/


        //poner en la tabla
        mostrarPeliculasEnTabla(cartelera);

    }
}
