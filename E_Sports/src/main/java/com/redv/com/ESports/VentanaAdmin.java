package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAdmin {
    private JPanel VentanaAdmin;
    private JRadioButton victoriaRadioButton;
    private JRadioButton derrotaRadioButton;
    private JRadioButton empateRadioButton;
    private JButton generarButton;
    private JComboBox comboBoxModificar;
    private JButton BotonModificar;
    private JButton salirButton;
    private JTable table1;
    private JTable table2;

    public VentanaAdmin() {
        JFrame frame = new JFrame("VentanaAdmin");
        frame.setContentPane(VentanaAdmin);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        BotonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBoxModificar.getSelectedIndex()){
                    case 0:
                        VentanaCrearJugador ventanaCrearJugador = new VentanaCrearJugador();
                        break;

                    case 1:
                        VentanaCrearEquipo ventanaCrearEquipo = new VentanaCrearEquipo();
                        break;

                    case 2:
                        VentanaCrearDueño ventanaCrearDueño = new VentanaCrearDueño();
                        break;

                    case 3:
                        break;


                }
            }
        });
    }




}
