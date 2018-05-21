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

    private CalendarioBD calendarioBD;

    public VentanaUsuario() {
        JFrame frame = new JFrame("VentanaUsuario");
        frame.setContentPane(VentanaUsuario);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        //BASE DE DATOS
        calendarioBD = new CalendarioBD();
        ArrayList<Calendario> calendarioTemporada = calendarioBD.cargarTemporada();//ÚLTIMA TEMPORADA GENERADA POR EL NÚCLEO
        ArrayList<Equipo> equiposParticipantes = calendarioBD.cargarEquiposTemporada(); //EQUIPOS QUE PARTICIPAN EN LA TEMPORADA
        //BASE DE DATOS
      
        TablaResultadosTemporada.setModel(new TablaResultadosUsuario(calendarioTemporada));//CREACION DE LA TABLA
     
        TablaClasificacion.setModel(new TablaClasificacionUsuario(calendarioTemporada, equiposParticipantes));//CREACION DE LA TABLA

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
