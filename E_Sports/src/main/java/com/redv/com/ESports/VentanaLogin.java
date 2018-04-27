package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaLogin {
    private JTextField textoUsuario;
    private JPasswordField textoContraseña;
    private JButton registrarseButton;
    private JButton iniciarSesionButton;
    private JPanel VentanaLogin;

    //Usuarios registrados
    private ArrayList<Usuario> usuariosRegistrados = new ArrayList<>();
    public ArrayList<Usuario> getUsuariosRegistrados() {
        return usuariosRegistrados;
    }

    //REFERENCIA
    VentanaSignUp ventanaSignUp;




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
                }else if(textoUsuario.getText().equalsIgnoreCase("admin") && textoContraseña.getText().equalsIgnoreCase("1234")){
                    VentanaAdmin ventanaAdmin = new VentanaAdmin();
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
