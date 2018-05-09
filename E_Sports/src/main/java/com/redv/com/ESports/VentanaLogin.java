package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaLogin {
    private JTextField textoUsuario;
    private JPasswordField textoContraseña;
    private JButton registrarseButton;
    private JButton iniciarSesionButton;
    private JPanel VentanaLogin;
    private JLabel textoInformativo;
    //ROL DEL USUARIO QUE INTENTA INICIAR SESIÓN.
    private String rol;


    public boolean VerificarCredenciales(String usuario, String contraseña) {
        boolean datosCorrectos = false;

        //COMPROBAR CREDENCIALES (usuario, contraseña), DE SER CORRECTOS SE DEVOLVERÁ UN TRUE Y DE SER INCORRECTOS UN FALSE.

        //if(usuario == usuario){

        //datosCorrectos = true;
        //}else{
        //datosCorrectos = false;
        //}

        //EL ROL SE ALMACENARÁ EN LA VARIABLE "rol"

        return datosCorrectos;
    }





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
                }else if(textoUsuario.getText().equalsIgnoreCase("usuario") && textoContraseña.getText().equalsIgnoreCase("1234")) {
                    VentanaUsuario ventanaUsuario = new VentanaUsuario();
                }


                if((VerificarCredenciales(textoUsuario.getText(), textoContraseña.getSelectedText())) == false){
                    textoInformativo.setText("Datos incorrectos");
                }else{
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
