package vista;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Vista_Principal extends JFrame {
    public JButton btnGestionCartelera;
    public JButton btnVentarReserva;
    public JButton btnGestionUsuarios;
    public JButton btnReportes;
    public JButton btnControlSalas;
    public JButton btnConfiguracionGeneral;
    public JPanel JPanelPrincipal;
    public JLabel jlabeltitulo;

    public Vista_Principal() {
        URL url = getClass().getResource("/recursos/camera.png");
        System.out.println("URL del icono: " + url);


        // Crear componentes
        jlabeltitulo = new JLabel("Panel Principal", SwingConstants.CENTER);
        btnGestionCartelera = new JButton("Gestión Cartelera");
        btnVentarReserva = new JButton("Ventas / Reservas");
        btnGestionUsuarios = new JButton("Gestión Usuarios");
        btnReportes = new JButton("Reportes");
        btnControlSalas = new JButton("Control de Salas");
        btnConfiguracionGeneral = new JButton("Configuración General");

        // Configuración base de la ventana
        setTitle("Panel Principal - Sistema de Gestión para Cines");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Crear panel principal y aplicar layout
        JPanelPrincipal = new JPanel(new BorderLayout());
        JPanelPrincipal.setBackground(new Color(255, 253, 251)); // blanco hueso
        setContentPane(JPanelPrincipal);

        // Título
        jlabeltitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        jlabeltitulo.setForeground(new Color(26, 29, 36));
        jlabeltitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        JPanelPrincipal.add(jlabeltitulo, BorderLayout.NORTH);

        // Panel de botones con grid layout 3x2
        JPanel panelBotones = new JPanel(new GridLayout(3, 2, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panelBotones.setBackground(new Color(255, 253, 251)); // mismo fondo

        // Estilo común
        Color colorBoton = new Color(236, 240, 241); // Gris claro (fondo)
        Color colorTexto = new Color(44, 62, 80);    // Gris oscuro (texto)
        Font fuenteBoton = new Font("Segoe UI", Font.PLAIN, 16);

        // Agregar botones con estilo
        agregarBotonEstilizado(panelBotones, btnGestionCartelera, colorBoton, colorTexto, fuenteBoton);
        agregarBotonEstilizado(panelBotones, btnVentarReserva, colorBoton, colorTexto, fuenteBoton);
        agregarBotonEstilizado(panelBotones, btnGestionUsuarios, colorBoton, colorTexto, fuenteBoton);
        agregarBotonEstilizado(panelBotones, btnReportes, colorBoton, colorTexto, fuenteBoton);
        agregarBotonEstilizado(panelBotones, btnControlSalas, colorBoton, colorTexto, fuenteBoton);
        agregarBotonEstilizado(panelBotones, btnConfiguracionGeneral, colorBoton, colorTexto, fuenteBoton);

        JPanelPrincipal.add(panelBotones, BorderLayout.CENTER);

        // Cargar imagen desde recursos
        ImageIcon iconoCartelera = new ImageIcon(getClass().getResource("/recursos/camera.png"));
        ImageIcon iconVenta = new ImageIcon(getClass().getResource("/recursos/ticket.png"));
        ImageIcon iconoUsers = new ImageIcon(getClass().getResource("/recursos/user.png"));
        ImageIcon iconoReport = new ImageIcon(getClass().getResource("/recursos/report.png"));
        ImageIcon iconoButacas = new ImageIcon(getClass().getResource("/recursos/swivel-chair.png"));
        ImageIcon iconoConfig = new ImageIcon(getClass().getResource("/recursos/gear.png"));


        // Método para redimensionar íconos a 24x24
        iconoCartelera = new ImageIcon(iconoCartelera.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        iconVenta = new ImageIcon(iconVenta.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        iconoUsers = new ImageIcon(iconoUsers.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        iconoReport = new ImageIcon(iconoReport.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        iconoButacas = new ImageIcon(iconoButacas.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));
        iconoConfig = new ImageIcon(iconoConfig.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH));

        // Asignar iconos a botones
        btnGestionCartelera.setIcon(iconoCartelera);
        btnVentarReserva.setIcon(iconVenta);
        btnGestionUsuarios.setIcon(iconoUsers);
        btnReportes.setIcon(iconoReport);
        btnControlSalas.setIcon(iconoButacas);
        btnConfiguracionGeneral.setIcon(iconoConfig);

// Ajustes de texto e ícono en botones
        JButton[] botones = {
                btnGestionCartelera, btnVentarReserva, btnGestionUsuarios,
                btnReportes, btnControlSalas, btnConfiguracionGeneral
        };

        for (JButton boton : botones) {
            boton.setHorizontalTextPosition(SwingConstants.RIGHT);
            boton.setIconTextGap(10);
        }

    }

    private void agregarBotonEstilizado(JPanel panel, JButton boton, Color fondo, Color texto, Font fuente) {
        boton.setBackground(fondo);
        boton.setForeground(texto);
        boton.setFont(fuente);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(189, 195, 199))); // borde sutil
        panel.add(boton);
    }
}
