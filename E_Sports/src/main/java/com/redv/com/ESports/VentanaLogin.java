package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin {
    private JTextField textoUsuario;
    private JPasswordField textoContraseña;
    private JButton registrarseButton;
    private JButton iniciarSesionButton;
    private JPanel VentanaLogin;
    private JLabel textoInformativo;

    private String rol;
    private UsuarioBD usuarioBD = new UsuarioBD();

    public VentanaLogin() {

        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VentanaSignUp ventanaSignUp = new VentanaSignUp();

            }
        });

        iniciarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //PROVISIONAL!!!

                if (textoUsuario.getText().equalsIgnoreCase("dueño") && textoContraseña.getText().equalsIgnoreCase("1234")) {
                    VentanaDueño ventanaDueño = new VentanaDueño();

                } else if (textoUsuario.getText().equalsIgnoreCase("admin") && textoContraseña.getText().equalsIgnoreCase("1234")) {
                    VentanaAdmin ventanaAdmin = new VentanaAdmin();
                }

                if (!(usuarioBD.comprobar_credenciales(textoUsuario.getText(), textoContraseña.getSelectedText()))) {
                    textoInformativo.setText("Datos incorrectos");
                } else {
                    textoInformativo.setText("Datos válidos");
                }

            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaLogin");
        frame.setContentPane(new VentanaLogin().VentanaLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
