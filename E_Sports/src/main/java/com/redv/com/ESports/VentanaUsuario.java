package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaUsuario {
    private JPanel VentanaUsuario;
    private JTable table1;
    private JTable table2;
    private JButton salirButton;


    public VentanaUsuario() {
        JFrame frame = new JFrame("VentanaUsuario");
        frame.setContentPane(VentanaUsuario);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);


        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
