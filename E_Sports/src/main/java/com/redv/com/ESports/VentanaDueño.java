package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaDueño {
    private JPanel VentanaDueño;
    private JButton confeccionarEquipoButton;
    private JButton salirButton;
    private JTable ResultadosUltimaTemporada;
    private JTable Clasificacion;


    public VentanaDueño() {
        JFrame frame = new JFrame("VentanaDueño");
        frame.setContentPane(VentanaDueño);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        //BASE DE DATOS
        ArrayList<Calendario> calendarioTemporada = new ArrayList<>();//ÚLTIMA TEMPORADA GENERADA POR EL NÚCLEO
        ArrayList<Equipo> equiposParticipantes = new ArrayList<>();//EQUIPOS QUE PARTICIPAN EN LA TEMPORADA
        //BASE DE DATOS

        ResultadosUltimaTemporada.setModel(new TablaResultadosUsuario(calendarioTemporada));//CREACION DE LA TABLA
        Clasificacion.setModel(new TablaClasificacionUsuario(calendarioTemporada, equiposParticipantes));//CREACION DE LA TABLA


        confeccionarEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaConfeccionarEquipo ventanaConfeccionarEquipo = new VentanaConfeccionarEquipo();

            }
        });


        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });
    }




}
