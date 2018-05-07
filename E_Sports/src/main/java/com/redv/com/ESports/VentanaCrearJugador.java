package com.redv.com.ESports;

import javax.swing.*;

public class VentanaCrearJugador {
    private JPanel VentanaCrearJugador;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JButton cancelarButton;
    private JButton crearButton;


    public VentanaCrearJugador() {
        JFrame frame = new JFrame("VentanaCrearJugador");
        frame.setContentPane(VentanaCrearJugador);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }




}


