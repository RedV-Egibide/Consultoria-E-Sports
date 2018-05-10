package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearJugador {
    private JPanel VentanaCrearJugador;
    private JTextField TextoNombre;
    private JTextField TextoApellido;
    private JTextField TextoNickname;
    private JTextField TextoSalario;
    private JButton cancelarButton;
    private JButton crearButton;
    private JLabel TextoInformativo;
    private JButton BotonBuscar;
    private JButton modificarDatosButton;
    private JButton eliminarButton;


    public boolean ComprobarDisponibilidad(String nickname){
        boolean datosCorrectos = false;//ESTA VARIABLE POR DEFECTO FALSE SERÁ LA RESPONSABLE DE ACTIVAR EL GUARDADO DE INFORMACION.
        //SI SE DEVUELVE FALSE SE LE COMUNICARÁ AL USUARIO, QUE EL NICKNAME NO ESTÁ DISPONIBLE
        //MIENTRAS QUE SI DEVUELVE TRUE, PROSEGUIRÁ EL CÓDIGO DE ACTIONLISENER "CREAREQUIPO" Y ALMACENARÁ LOS DEMÁS DATOS DE LA INTERFAZ


        //AQUÍ COMPROBAR QUE EL NOMBRE NO ESTÁ REPETIDO




        return  datosCorrectos;
    }


    public VentanaCrearJugador() {
        JFrame frame = new JFrame("VentanaCrearJugador");
        frame.setContentPane(VentanaCrearJugador);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);




        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RECOLECTAMOS TODOS LOS DATOS DE LA INTERFAZ Y LOS GUARDAMOS EN VARIABLES.
                String nombre = TextoNombre.getText().toUpperCase().trim();
                String apellido = TextoApellido.getText().toUpperCase().trim();
                String nickname = TextoNickname.getText().toUpperCase().trim();
                int salario = Integer.parseInt(TextoSalario.getText());

                if(ComprobarDisponibilidad(nickname)){//YA QUE EL ÚNICO ERROR POSIBLE ES LA DISPONIBILIDAD DEL NOMBRE
                                                            // ESTA FUNCION (UBICADA ARRIBA) SE ENCARGARÁ DE COMPROBAR QUE ESTÁ CORRECTO

                    Jugador nuevoJugador = new Jugador(nickname, nombre, apellido, salario);//ESTE ES EL OBJETO A GUARDAR
                    TextoInformativo.setText("Jugador registrado correctamente");
                }else{
                    TextoInformativo.setText("El nickname introducido ya está en uso");
                    TextoNickname.setText("");
                }


            }
        });





        BotonBuscar.addActionListener(new ActionListener() {//BOTON PARA BUSCAR DATOS DE JUGADOR YA REGISTRADO
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = TextoNickname.getText();//NICKNAME DE JUGADOR A MOSTRAR DATOS


                /*DATOS A RELLENAR DESDE LA BASE DE DATOS
                TextoNombre.setText();
                TextoApellido.setText();
                TextoSalario.setText();
                */

            }
        });


        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });



        //LOS NOMBRES DE LOS JTEXT SON LOS SIGUIENTES
        //TextoNombre
        //TextoApellido
        //TextoNickname (PK: LA BUSQUEDA DE DATOS SE HARÁ EN BASE A ESTE TEXTO)
        //TextoSalario

        //AQUI ESTÁN LAS TRES POSIBILIDADES

        modificarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MODIFICAR (ACTUALIZAR LOS CAMPOS CON LA NUEVA INFO DE LOS JTEXT)

            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ELIMINAR (ELIMINAR EL JUGADOR SELECCIONADO ("TextoNickname"))

            }
        });



        BotonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RELLENAR LOS CAMPOS DEl "JUGADOR" ESCRITO EN EL JTEXT "TextoNickname"

            }
        });
    }




}


