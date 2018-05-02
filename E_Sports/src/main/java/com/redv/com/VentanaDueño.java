package com.redv.com;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaDueño {
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JPanel VentanaDueño;
    private JButton confeccionarEquipoButton;
    private JButton salirButton;

    public VentanaDueño() {
        JFrame frame = new JFrame("VentanaDueño");
        frame.setContentPane(VentanaDueño);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        confeccionarEquipoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VentanaConfeccionEquipo ventanaConfeccionEquipo = new VentanaConfeccionEquipo();

            }
        });
    }

}
