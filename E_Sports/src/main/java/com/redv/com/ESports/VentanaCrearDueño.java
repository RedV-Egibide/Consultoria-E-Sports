package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class VentanaCrearDueño {
    private JPanel VentanaCrearDueño;
    private JTextField TextoContraseña;
    private JTextField TextoNombre;
    private JButton cancelarButton;
    private JButton crearButton;
    private JTextField TextoApellido;
    private JTextField TextoUsuario;
    private JLabel TextoInformativo;
    private JButton BotonBuscar;
    private JButton modificarDatosButton;
    private JButton eliminarButton;


    public boolean ComprobarDisponibilidad(String nombreUsuario){
        boolean datosCorrectos = false;//ESTA VARIABLE POR DEFECTO FALSE SERÁ LA RESPONSABLE DE ACTIVAR EL GUARDADO DE INFORMACION.
        //SI SE DEVUELVE FALSE SE LE COMUNICARÁ AL USUARIO, QUE EL USUARIO NO ESTÁ DISPONIBLE
        //MIENTRAS QUE SI DEVUELVE TRUE, PROSEGUIRÁ EL CÓDIGO DE ACTIONLISENER "CREAREQUIPO" Y ALMACENARÁ LOS DEMÁS DATOS DE LA INTERFAZ


        //AQUÍ COMPROBAR QUE EL NOMBRE NO ESTÁ REPETIDO


        return  datosCorrectos;
    }





    public VentanaCrearDueño() {
        JFrame frame = new JFrame("VentanaCrearDueño");
        frame.setContentPane(VentanaCrearDueño);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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



                if(ComprobarDisponibilidad(usuario)){//YA QUE EL ÚNICO ERROR POSIBLE ES LA DISPONIBILIDAD DEL NOMBRE
                                  // ESTA FUNCION (UBICADA ARRIBA) SE ENCARGARÁ DE COMPROBAR QUE ESTÁ CORRECTO

                    Dueño dueño = new Dueño(usuario, contraseña, nombre, apellido);//ESTE ES EL OBJETO A ALMACENAR EN LA BASE DE DATOS



                    TextoInformativo.setText("Dueño registrado correctamente");
                    TextoNombre.setText("");
                    TextoApellido.setText("");
                    TextoUsuario.setText("");
                    TextoContraseña.setText("");

                }else{
                    TextoInformativo.setText("El nickname introducido ya está en uso");//LOS DATOS NO HAN PODIDO SER GUARDADOS EN LA BASE DE DATOS
                }





            }
        });






        BotonBuscar.addActionListener(new ActionListener() {//BOTON PARA BUSCAR DATOS DE DUEÑO YA REGISTRADO
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = TextoUsuario.getText();//USUARIO DEL DUEÑO A MOSTRAR DATOS

               /* DATOS A RELLENAR DESDE LA BASE DE DATOS
                TextoNombre.setText();
                TextoApellido.setText();
                TextoContraseña.setText();
                */
            }
        });



        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }


}
