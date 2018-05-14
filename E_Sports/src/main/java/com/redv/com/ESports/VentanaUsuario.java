package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaUsuario {
    private JPanel VentanaUsuario;
    private JTable TablaResultadosTemporada;
    private JButton salirButton;
    private JTable TablaClasificacion;
    private JScrollPane JScrollPane2;


    public VentanaUsuario() {
        JFrame frame = new JFrame("VentanaUsuario");
        frame.setContentPane(VentanaUsuario);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);



        TablaResultadosTemporada.setModel(new TablaResultadosUsuario());

        TablaClasificacion.setModel(new TablaClasificacionUsuario());



        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

}
