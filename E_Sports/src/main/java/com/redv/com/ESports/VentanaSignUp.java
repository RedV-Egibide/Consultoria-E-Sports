package com.redv.com.ESports;

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

    private VentanaLogin ventanaLogin;





    public VentanaSignUp() {
        JFrame frame = new JFrame("VentanaSignUp");
        frame.setContentPane(VentanaSignUp);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
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

                String a, b;
                //SOLO SI LA CONTRASEÑA Y LA CONFIRMACIÓN DE LA CONTRASEÑA SON IGUALES PROSEGUIRÁ LA APLICACIÓN.
                if (TextoContraseña.getText().equalsIgnoreCase(TextoConfirmarContraseña.getText())) {
                    String nombreUsuario = TextoUsuario.getText();
                    String contraseña = TextoContraseña.getText();

                    Usuario usuario = new Usuario(nombreUsuario, contraseña);
                    //FALTA CONECTAR CON LA PÁGINA DE LOGIN

                    TextoUsuario.setText("");
                    TextoContraseña.setText("");
                    TextoConfirmarContraseña.setText("");
                }

            }
        });
    }






}
