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


    public VentanaCrearEquipo() {
        JFrame frame = new JFrame("VentanaCrearEquipo");
        frame.setContentPane(VentanaCrearEquipo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);




        ArrayList<Jugador> jugadoresDisponibles = new ArrayList<>();//ESTA ES LA LISTA DE JUGADORES DISPONIBLES PARA AÑADIR AL EQUIPO CREADO. (CARGARÁ DESDE LA BASE DE DATOS)
        ArrayList<Dueño> dueñosDisponibles = new ArrayList<>();//DUEÑOS DISPONIBLES PARA SER PROPIETARIOS DEL EQUIPO



        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEquipo = TextoNombreEquipo.getText();
                int j1 = Jugador1.getSelectedIndex();
                int j2 = Jugador2.getSelectedIndex();
                int j3 = Jugador3.getSelectedIndex();
                int j4 = Jugador4.getSelectedIndex();
                int j5 = Jugador5.getSelectedIndex();
                int j6 = Jugador6.getSelectedIndex();
                int dueño = Dueño.getSelectedIndex();

                ArrayList<Jugador> jugadoreseSeleccionados = new ArrayList<>();//AQUI SE ALAMACENARÁN LOS JUGADORES SELECCIONADOS PARA EL EQUIPO

                jugadoreseSeleccionados.add(jugadoresDisponibles.get(j1));
                jugadoreseSeleccionados.add(jugadoresDisponibles.get(j2));
                jugadoreseSeleccionados.add(jugadoresDisponibles.get(j3));
                jugadoreseSeleccionados.add(jugadoresDisponibles.get(j4));
                jugadoreseSeleccionados.add(jugadoresDisponibles.get(j5));
                jugadoreseSeleccionados.add(jugadoresDisponibles.get(j6));

                Dueño dueñoSeleccionado = dueñosDisponibles.get(dueño);

                Equipo nuevoEquipo = new Equipo(nombreEquipo, jugadoreseSeleccionados, dueñoSeleccionado);





            }
        });
    }





}
