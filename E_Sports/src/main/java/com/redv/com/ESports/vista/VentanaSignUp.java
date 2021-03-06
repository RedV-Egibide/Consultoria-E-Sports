package com.redv.com.ESports.vista;

import com.redv.com.ESports.modelo.UsuarioBD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaSignUp {
    private JPanel VentanaSignUp;
    private JButton cancelarButton;
    private JButton registrarseButton;
    private JTextField TextoUsuario;
    private JPasswordField TextoContraseña;
    private JPasswordField TextoConfirmarContraseña;
    private JLabel textoInformativo;

    private UsuarioBD usuarioBD = new UsuarioBD();

    public VentanaSignUp() {
        JFrame frame = new JFrame("VentanaSignUp");
        frame.setContentPane(VentanaSignUp);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //SOLO SI LA CONTRASEÑA Y LA CONFIRMACIÓN DE LA CONTRASEÑA SON IGUALES PROSEGUIRÁ LA APLICACIÓN.
                if (TextoContraseña.getText().equalsIgnoreCase(TextoConfirmarContraseña.getText())) {

                    String nombreUsuario = TextoUsuario.getText().toUpperCase().trim();
                    String contraseña = TextoContraseña.getText().trim();
                    boolean usuarioValido;

                    usuarioValido = usuarioBD.resgistrarUsuario(nombreUsuario, contraseña);

                    if (usuarioValido) {     //ESTE IF SE ENCARGA DE AVISAR DEL PROBLEMA O REGISTRO DEL USUARIO
                        textoInformativo.setText("Usuario registrado correctamente");

                        TextoUsuario.setText("");
                        TextoContraseña.setText("");
                        TextoConfirmarContraseña.setText("");
                    } else {
                        textoInformativo.setText("El nombre de usuario ya está en uso");
                        TextoUsuario.setText("");
                    }

                } else {
                    textoInformativo.setText("Contraseñas no coinciden ");
                    TextoContraseña.setText("");
                    TextoConfirmarContraseña.setText("");
                }
            }
        });
    }

}

