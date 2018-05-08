package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VentanaCrearDueño {
    private JPanel VentanaCrearDueño;
    private JTextField TextoContraseña;
    private JTextField TextoNombre;
    private JButton cancelarButton;
    private JButton crearButton;
    private JComboBox comboBox1;
    private JTextField TextoApellido;
    private JTextField TextoUsuario;
    private JLabel TextoInformativo;


    public boolean RegistrarDueño(String nombre, String Apellido, String contraseña, String Usuario){
        boolean registroCorrecto = false;

        //AQUI ESTARÁ LA LLAMADA A LA BASE DE DATOS, SI ALGÚN DATO NO ESTUVIERA EN REGLA (USUARIO YA COGIDO O ALGO ASÍ) SE DEVOLVERÍA UN FALSE, PERO DE IR CORRECTO UN TRUE.



        return registroCorrecto;
    }



    public VentanaCrearDueño() {
        JFrame frame = new JFrame("VentanaCrearDueño");
        frame.setContentPane(VentanaCrearDueño);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);




        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = TextoNombre.getText();
                String apellido = TextoApellido.getText();
                String usuario = TextoUsuario.getText();
                String contraseña = TextoContraseña.getText();

                if((RegistrarDueño(nombre, apellido, contraseña, usuario) == true)){
                    TextoInformativo.setText("Dueño registrado correctamente");//DE SALIR ESTA OPCIÓN LA FUNCION "REGISTRAR USUARIO" YA HABRÍA GUARDADO LOS DATOS CORRECTAMENTE
                    TextoNombre.setText("");
                    TextoApellido.setText("");
                    TextoUsuario.setText("");
                    TextoContraseña.setText("");

                }else{
                    TextoInformativo.setText("Datos no válidos");
                }





            }
        });
    }


}
