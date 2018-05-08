package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaVisualizarJugador {
    private JPanel VentanaVIsualizarJugador;
    private JComboBox comboBox1;
    private JButton salirButton;


    public VentanaVisualizarJugador() {
        JFrame frame = new JFrame("VentanaVisualizarJugador");
        frame.setContentPane(VentanaVIsualizarJugador);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
            }
        });
    }

}
