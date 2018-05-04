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
    private JLabel textoInformativo;


    public boolean RegistrarUsuario(String usuario, String contraseña){
        boolean nombreVálido=false;
        //Si el nombre está disponible devolverá TRUE si no es el caso FALSE

        //if(usuario != nombre){
        //nombreValido = true;

        //AQUI GUARDAR DATOS

        //}else{
        //nombreValido = false;
        //}




        return nombreVálido;
    }



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

                //SOLO SI LA CONTRASEÑA Y LA CONFIRMACIÓN DE LA CONTRASEÑA SON IGUALES PROSEGUIRÁ LA APLICACIÓN.
                if (TextoContraseña.getText().equalsIgnoreCase(TextoConfirmarContraseña.getText())) {
                    String nombreUsuario = TextoUsuario.getText();
                    String contraseña = TextoContraseña.getText();
                    boolean usuarioValido;

                    usuarioValido = RegistrarUsuario(nombreUsuario, contraseña);

                    if (usuarioValido == true){     //ESTE IF SE ENCARGA DE AVISAR DEL PROBLEMA O REGISTRO DEL USUARIO
                        textoInformativo.setText("Usuario registrado correctamente");
                        TextoUsuario.setText("");
                        TextoContraseña.setText("");
                        TextoConfirmarContraseña.setText("");
                    }else{
                        textoInformativo.setText("El nombre de usuario ya está en uso");
                    }


                }

            }
        });
    }






}