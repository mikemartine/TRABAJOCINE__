package vista;

import javax.swing.*;
import java.awt.*;

public class Vista_RegistrarUsuario extends JFrame{
    public JTextField txtUsername;
    public JTextField txtPassword;
    public JComboBox comboRol;
    public JButton btnRegistrar;
    public JPanel JPanel_Registarusurario;
    public JLabel jlabelTitulo;
    public JLabel jlabelUsername;
    public JLabel jlabelPassword;
    public JLabel jlabelAsignarRol;

    public Vista_RegistrarUsuario() {
        // Configuración base de la ventana
        setTitle("Registrar Usuario");
        setContentPane(JPanel_Registarusurario);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);

        // Fondo del panel principal
        JPanel_Registarusurario.setBackground(new Color(255, 253, 251)); // Blanco hueso

        // Título
        if (jlabelTitulo != null)
            jlabelTitulo.setForeground(new Color(26, 29, 36)); // Gris oscuro

        // Campos de texto
        if (txtUsername != null) {
            txtUsername.setBackground(new Color(245, 246, 247));
            txtUsername.setForeground(new Color(26, 29, 36));
        }

        if (txtPassword != null) {
            txtPassword.setBackground(new Color(245, 246, 247));
            txtPassword.setForeground(new Color(26, 29, 36));
        }

        // Combo box
        if (comboRol != null) {
            comboRol.setBackground(new Color(245, 246, 247));
            comboRol.setForeground(new Color(26, 29, 36));
        }

        // Botón registrar
        if (btnRegistrar != null) {
            btnRegistrar.setBackground(new Color(39, 174, 96)); // Verde
            btnRegistrar.setForeground(Color.WHITE);
        }

        // Labels
        Color grisOscuro = new Color(44, 62, 80);
        if (jlabelUsername != null)
            jlabelUsername.setForeground(grisOscuro);

        if (jlabelPassword != null)
            jlabelPassword.setForeground(grisOscuro);

        if (jlabelAsignarRol != null)
            jlabelAsignarRol.setForeground(grisOscuro);

        aplicarFuentePersonalizada();
    }

    private void aplicarFuentePersonalizada() {
        Font fuenteGeneral = new Font("Segoe UI", Font.PLAIN, 14);
        Font fuenteTitulo = new Font("Segoe UI", Font.BOLD, 18);

        if (jlabelTitulo != null)
            jlabelTitulo.setFont(fuenteTitulo);

        if (txtUsername != null)
            txtUsername.setFont(fuenteGeneral);

        if (txtPassword != null)
            txtPassword.setFont(fuenteGeneral);

        if (comboRol != null)
            comboRol.setFont(fuenteGeneral);

        if (btnRegistrar != null)
            btnRegistrar.setFont(fuenteGeneral);

        if (jlabelUsername != null)
            jlabelUsername.setFont(fuenteGeneral);

        if (jlabelPassword != null)
            jlabelPassword.setFont(fuenteGeneral);

        if (jlabelAsignarRol != null)
            jlabelAsignarRol.setFont(fuenteGeneral);
    }


}
