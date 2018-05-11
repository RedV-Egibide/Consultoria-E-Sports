package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaCrearEquipo {
    private JPanel VentanaCrearEquipo;
    private JTextField TextoNombreEquipo;
    private JComboBox Jugador1;
    private JComboBox Jugador3;
    private JComboBox Jugador2;
    private JComboBox Jugador4;
    private JComboBox Jugador5;
    private JComboBox Jugador6;
    private JButton cancelarButton;
    private JButton crearButton;
    private JComboBox Dueño;
    private JLabel TextoInformativo;
    private JButton BotonBuscar;
    private JButton eliminarButton;
    private JButton modificarDatosButton;


    public boolean ComprobarDisponibilidad(String nombreEquipo){
        boolean datosCorrectos = false;//ESTA VARIABLE POR DEFECTO FALSE SERÁ LA RESPONSABLE DE ACTIVAR EL GUARDADO DE INFORMACION.
                                        //SI SE DEVUELVE FALSE SE LE COMUNICARÁ AL USUARIO, QUE EL NOMBRE NO ESTÁ DISPONIBLE
                                        //MIENTRAS QUE SI DEVUELVE TRUE, PROSEGUIRÁ EL CÓDIGO DE ACTIONLISENER "CREAREQUIPO" Y ALMACENARÁ LOS DEMÁS DATOS DE LA INTERFAZ



        //AQUÍ COMPROBAR QUE EL NOMBRE NO ESTÁ REPETIDO


        return  datosCorrectos;
    }



    public VentanaCrearEquipo() {
        JFrame frame = new JFrame("VentanaCrearEquipo");
        frame.setContentPane(VentanaCrearEquipo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



        //ESTOS SON LOS ARRAYS QUE DEBES DE CONECTAR A LA BASE DE DATOS PARA PODER CREAR EL EQUIPO. (BASE DE DATOS)

        ArrayList<Jugador> jugadoresDisponibles = new ArrayList<>();//ESTA ES LA LISTA DE JUGADORES DISPONIBLES PARA ELEGIR EN EL NUEVO EQUIPO. (CARGARÁ DESDE LA BASE DE DATOS)
        ArrayList<Dueño> dueñosDisponibles = new ArrayList<>();//DUEÑOS DISPONIBLES PARA SER PROPIETARIOS DEL EQUIPO. (CARGARÁ DESDE LA BASE DE DATOS)

        //ESTOS SON LOS NOMBRES DE LOS COMBOBOX
        //Jugador1
        //Jugador2
        //Jugador3
        //Jugador4
        //Jugador5
        //Jugador6

        for (int i = 0; i < jugadoresDisponibles.size(); i++) {//ESTE BUCLE SE ENCARGA DE MOSTRAR AL USUARIO LOS JUGADORES DISPONIBLES.
            Jugador1.addItem(jugadoresDisponibles.get(i).getNickname());
            Jugador2.addItem(jugadoresDisponibles.get(i).getNickname());
            Jugador3.addItem(jugadoresDisponibles.get(i).getNickname());
            Jugador4.addItem(jugadoresDisponibles.get(i).getNickname());
            Jugador5.addItem(jugadoresDisponibles.get(i).getNickname());
            Jugador6.addItem(jugadoresDisponibles.get(i).getNickname());
        }





        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //AQUI ALMACENAMOS EN VARIABLES TODOS LOS DATOS INTRODUCIDOS POR EL USUARIO EN LA INTERFAZ GRÁFICA.
                String nombreEquipo = TextoNombreEquipo.getText();
                int j1 = Jugador1.getSelectedIndex();
                int j2 = Jugador2.getSelectedIndex();
                int j3 = Jugador3.getSelectedIndex();
                int j4 = Jugador4.getSelectedIndex();
                int j5 = Jugador5.getSelectedIndex();
                int j6 = Jugador6.getSelectedIndex();
                int dueño = Dueño.getSelectedIndex();

                ArrayList<Jugador> jugadoreseSeleccionados = new ArrayList<>();//AQUI SE ALAMACENARÁN LOS JUGADORES SELECCIONADOS PARA EL EQUIPO.


                    if(ComprobarDisponibilidad(nombreEquipo)) {//YA QUE EL ÚNICO ERROR POSIBLE ES LA DISPONIBILIDAD DEL NOMBRE
                        // ESTA FUNCION (UBICADA ARRIBA) SE ENCARGARÁ DE COMPROBAR QUE ESTÁ CORRECTO

                   //GUARDAMOS LOS JUGADORES SELECCIONADOS DEL ARRAY JUGADORESDISPONIBLES EN UN NUEVO ARRAY QUE IRÁ DIRECTAMENTE AL CONSTRUCTOR DEL NUEVO EQUIPO.
                   jugadoreseSeleccionados.add(jugadoresDisponibles.get(j1));
                   jugadoreseSeleccionados.add(jugadoresDisponibles.get(j2));
                   jugadoreseSeleccionados.add(jugadoresDisponibles.get(j3));
                   jugadoreseSeleccionados.add(jugadoresDisponibles.get(j4));
                   jugadoreseSeleccionados.add(jugadoresDisponibles.get(j5));
                   jugadoreseSeleccionados.add(jugadoresDisponibles.get(j6));

                   Dueño dueñoSeleccionado = dueñosDisponibles.get(dueño);


                   Equipo nuevoEquipo = new Equipo(nombreEquipo, jugadoreseSeleccionados, dueñoSeleccionado);//ESTE ES EL OBJETO A ALMACENAR EN LA BASE DE DATOS
                        TextoInformativo.setText("Equipo registrado correctamente");
                        TextoNombreEquipo.setText("");




               }else{
                   TextoInformativo.setText("El nombre introducido ya está en uso");
                   TextoNombreEquipo.setText("");
               }




            }
        });



        BotonBuscar.addActionListener(new ActionListener() {//BOTON PARA BUSCAR DATOS DE EQUIPO YA REGISTRADO
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEquipo = TextoNombreEquipo.getText().toUpperCase().trim();//NOMBRE DEL EQUIPO A MOSTRAR DATOS





            }
        });



        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });




        //LOS NOMBRES DE LOS JTEXT y LOS COMBOBOX SON LOS SIGUIENTES
        //TextoNombreEquipo (PK: LA BUSQUEDA DE DATOS SE HARÁ EN BASE A ESTE TEXTO)
        //Jugador1
        //Jugador2
        //Jugador3
        //Jugador4
        //Jugador5
        //Jugador6

        modificarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MODIFICAR (ACTUALIZAR LOS CAMPOS CON LA NUEVA INFO DE LOS JTEXT)

            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ELIMINAR (ELIMINAR EL EQUIPO SELECCIONADO ("TextoNombreEquipo"))

            }
        });

        BotonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RELLENAR LOS CAMPOS DEl "EQUIPO" ESCRITO EN EL JTEXT ("TextoNombreEquipo")

            }
        });
    }





}
