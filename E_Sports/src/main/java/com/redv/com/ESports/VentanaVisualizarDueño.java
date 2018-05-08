package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaVisualizarDueño {
    private JComboBox comboBox1;
    private JButton salirButton;
    private JPanel VentanaVisualizarDueño;


    public VentanaVisualizarDueño() {
        JFrame frame = new JFrame("VentanaVisualizarDueño");
        frame.setContentPane(VentanaVisualizarDueño);
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


