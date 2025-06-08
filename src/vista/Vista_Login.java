package vista;

import javax.swing.*;
import java.awt.*;

public class Vista_Login extends JFrame{
    public JTextField txtUsuario;
    public JButton btnIniciarSesion;
    public JButton btnRegistrarse;
    public JTextField txtPassword;
    public JPanel JPanelLogin;
    public JLabel labelTitulo;
    public JLabel jlabelUsuario;
    public JLabel jlabelPassword;

    public Vista_Login(){
        // Configuración base de la ventana
        setTitle("Sistema de Gestión para Cines");
        setContentPane(JPanelLogin);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);

        // Fondo del panel principal
        JPanelLogin.setBackground(new Color(255, 253, 251)); // Blanco hueso

        // Título
        if (labelTitulo != null)
            labelTitulo.setForeground(new Color(26, 29, 36)); // Gris oscuro
// Campos de texto
        if (txtUsuario != null) {
            txtUsuario.setBackground(new Color(245, 246, 247)); // Fondo input
            txtUsuario.setForeground(new Color(26, 29, 36));     // Texto input
        }
        // Botón Iniciar Sesión
        if (btnIniciarSesion != null) {
            btnIniciarSesion.setBackground(new Color(231, 76, 60)); // Rojo
            btnIniciarSesion.setForeground(Color.WHITE);            // Texto blanco
        }

        // Botón Registrarse
        if (btnRegistrarse != null) {
            btnRegistrarse.setBackground(new Color(236, 240, 241)); // Gris claro
            btnRegistrarse.setForeground(new Color(44, 62, 80));     // Gris oscuro
        }
        // Label de Username
        if (jlabelUsuario != null)
            jlabelUsuario.setForeground(new Color(44, 62, 80)); // Gris oscuro

// Label de Password
        if (jlabelPassword != null)
            jlabelPassword.setForeground(new Color(44, 62, 80)); // Gris oscuro


        aplicarFuentePersonalizada();

    }
    private void aplicarFuentePersonalizada() {
        Font fuenteGeneral = new Font("Segoe UI", Font.PLAIN, 14);
        Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 18);

        if (labelTitulo != null)
            labelTitulo.setFont(fuenteTitulo);

        if (txtUsuario != null)
            txtUsuario.setFont(fuenteGeneral);

        if (txtPassword != null)
            txtPassword.setFont(fuenteGeneral);

        if (btnIniciarSesion != null)
            btnIniciarSesion.setFont(fuenteGeneral);

        if (btnRegistrarse != null)
            btnRegistrarse.setFont(fuenteGeneral);
        if (jlabelUsuario != null)
            jlabelUsuario.setFont(fuenteGeneral);

        if (jlabelPassword != null)
            jlabelPassword.setFont(fuenteGeneral);
    }



}
