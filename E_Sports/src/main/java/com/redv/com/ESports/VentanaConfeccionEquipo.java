package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaConfeccionEquipo {
    private JPanel VentanaConfeccionEquipo;
    private JButton cancelarButton;
    private JButton crearButton;
    private JTable table1;


    public VentanaConfeccionEquipo() {
        JFrame frame = new JFrame("VentanaConfeccionEquipo");
        frame.setContentPane(VentanaConfeccionEquipo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();

            }
        });
    }

}
