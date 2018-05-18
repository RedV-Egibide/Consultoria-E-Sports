package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaUsuario {
    private JPanel VentanaUsuario;
    private JTable TablaResultadosTemporada;
    private JButton salirButton;
    private JTable TablaClasificacion;
    private JScrollPane JScrollPane2;


    public VentanaUsuario() {
        JFrame frame = new JFrame("VentanaUsuario");
        frame.setContentPane(VentanaUsuario);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        //BASE DE DATOS
       ArrayList<Calendario> calendarioTemporada = new ArrayList<>();//ÚLTIMA TEMPORADA GENERADA POR EL NÚCLEO
       ArrayList<Equipo> equiposParticipantes = new ArrayList<>(); //EQUIPOS QUE PARTICIPAN EN LA TEMPORADA
        //BASE DE DATOS


        //Esto hay que borrarlo hasta el próximo "BORRAR" (Provisional para el error de las JTable)
        ArrayList<Equipo> equipos = new ArrayList<>();

        equipos.add(new Equipo("A"));
        equipos.add(new Equipo("B"));
        equipos.add(new Equipo("C"));
        equipos.add(new Equipo("D"));
        equipos.add(new Equipo("E"));
        equipos.add(new Equipo("F"));
        equipos.add(new Equipo("G"));

        ArrayList<Calendario> TemporadaDePrueba;
        TemporadaDePrueba = Nucleo.OrdenarEnfrentamientos(equipos);

        TablaResultadosTemporada.setModel(new TablaResultadosUsuario(TemporadaDePrueba));
        //BORRAR







        //Esto hay que quitarlo de texto (Provisional para el error de las JTable)
        //TablaResultadosTemporada.setModel(new TablaResultadosUsuario(calendarioTemporada));//CREACION DE LA TABLA
        TablaClasificacion.setModel(new TablaClasificacionUsuario(calendarioTemporada, equiposParticipantes));//CREACION DE LA TABLA



        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
