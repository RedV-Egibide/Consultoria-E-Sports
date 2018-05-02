package com.redv.com.Eric;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin {
    private JTextField textoUsuario;
    private JPasswordField textoContraseña;
    private JButton registrarseButton;
    private JButton iniciarSesionButton;
    private JPanel VentanaLogin;


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
                if (textoUsuario.getText().equalsIgnoreCase("dueño") && textoContraseña.getText().equalsIgnoreCase("1234")){
                    VentanaDueño ventanaDueño = new VentanaDueño();
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaLogin");
        frame.setContentPane(new VentanaLogin().VentanaLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
