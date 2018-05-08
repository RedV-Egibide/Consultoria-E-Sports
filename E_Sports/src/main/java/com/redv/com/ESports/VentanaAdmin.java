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
    private JButton crearButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton salirButton;
    private JButton verButton;
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
                frame.dispose();
            }
        });



        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(comboBoxModificar.getSelectedIndex() == 0){
                    VentanaCrearJugador ventanaCrearJugador = new VentanaCrearJugador();
                }else if(comboBoxModificar.getSelectedIndex() == 1){
                    VentanaCrearEquipo ventanaCrearEquipo = new VentanaCrearEquipo();
                }else if(comboBoxModificar.getSelectedIndex() == 2){
                    VentanaCrearDueño ventanaCrearDueño = new VentanaCrearDueño();
                }else if(comboBoxModificar.getSelectedIndex() == 3){
                       VentanaSignUp ventanaSignUp = new VentanaSignUp();
                }


            }
        });

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(comboBoxModificar.getSelectedIndex() == 0){
                   VentanaVisualizarJugador ventanaVisualizarJugador = new VentanaVisualizarJugador();
                }else if(comboBoxModificar.getSelectedIndex() == 1){
                    VentanaVisualizarEquipo ventanaVisualizarEquipo = new VentanaVisualizarEquipo();
                }else if(comboBoxModificar.getSelectedIndex() == 2){
                    VentanaVisualizarDueño ventanaVisualizarDueño = new VentanaVisualizarDueño();
                }else if(comboBoxModificar.getSelectedIndex() == 3){
                    //usuario
                }


            }
        });
    }




}
