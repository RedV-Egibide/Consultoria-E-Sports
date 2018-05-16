package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaDueño {
    private JPanel VentanaDueño;
    private JButton confeccionarEquipoButton;
    private JButton salirButton;
    private JTable table1;
    private JTable table2;


    public VentanaDueño() {
        JFrame frame = new JFrame("VentanaDueño");
        frame.setContentPane(VentanaDueño);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



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
