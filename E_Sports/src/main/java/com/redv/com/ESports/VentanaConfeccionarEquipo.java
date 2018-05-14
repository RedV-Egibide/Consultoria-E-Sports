package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VentanaConfeccionarEquipo {
    private JButton cancelarButton;
    private JButton confeccionarButton;
    private JComboBox Jugador1;
    private JComboBox Jugador3;
    private JComboBox Jugador2;
    private JComboBox Jugador4;
    private JComboBox Jugador5;
    private JComboBox Jugador6;
    private JLabel TextoNombreEquipo;
    private JPanel VentanaConfeccionarEquipo;

    private EquipoBD equipoBD = new EquipoBD();

    public VentanaConfeccionarEquipo() {
        JFrame frame = new JFrame("VentanaConfeccionarEquipo");
        frame.setContentPane(VentanaConfeccionarEquipo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //cargar lista jugadores aquí

        confeccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                equipoBD.confeccionarEquipo((Jugador) Jugador1.getSelectedItem());

            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public void cargarCombos(List<Jugador> jugadores) {
        DefaultComboBoxModel<Jugador> jug = new DefaultComboBoxModel<>();

        for (Jugador j : jugadores) {

        }
    }
}
