package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAdmin {
    private JPanel VentanaAdmin;
    private JList list1;
    private JRadioButton victoriaRadioButton;
    private JRadioButton derrotaRadioButton;
    private JRadioButton empateRadioButton;
    private JButton generarButton;
    private JComboBox comboBox1;
    private JButton crearButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton salirButton;
    private JList list2;
    private JButton verButton;

    public VentanaAdmin() {
        JFrame frame = new JFrame("VentanaAdmin");
        frame.setContentPane(VentanaAdmin);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }




}
