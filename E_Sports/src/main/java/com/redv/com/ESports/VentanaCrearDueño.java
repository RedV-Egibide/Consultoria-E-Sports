package com.redv.com.ESports;

import javax.swing.*;

public class VentanaCrearDueño {
    private JPanel VentanaCrearDueño;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton cancelarButton;
    private JButton crearButton;
    private JComboBox comboBox1;
    private JTextField textField4;


    public VentanaCrearDueño() {
        JFrame frame = new JFrame("VentanaCrearDueño");
        frame.setContentPane(VentanaCrearDueño);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


}
