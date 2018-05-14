package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaConfeccionarEquipo {
    private JButton cancelarButton;
    private JButton confeccionarButton;
    private JComboBox Jugador1;
    private JComboBox Jugador3;
    private JComboBox Jugador2;
    private JComboBox Jugador4;
    private JComboBox Jugador5;
    private JComboBox Jugador6;
    private JLabel TextoNombreEquipo;
    private JPanel VentanaConfeccionarEquipo;


    public VentanaConfeccionarEquipo() {
        JFrame frame = new JFrame("VentanaConfeccionarEquipo");
        frame.setContentPane(VentanaConfeccionarEquipo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



        confeccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
