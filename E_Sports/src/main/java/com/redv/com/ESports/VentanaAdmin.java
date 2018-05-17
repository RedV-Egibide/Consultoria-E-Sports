package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAdmin {
    private JPanel VentanaAdmin;
    private JRadioButton victoriaDerrotaRadioButton;
    private JRadioButton derrotaVictoriaRadioButton;
    private JRadioButton empateRadioButton;
    private JButton generarButton;
    private JComboBox comboBoxModificar;
    private JButton BotonModificar;
    private JButton salirButton;
    private JTable ResultadosTemporada;
    private JTable table2;
    private JButton insertarButton;

    public VentanaAdmin() {
        JFrame frame = new JFrame("VentanaAdmin");
        frame.setContentPane(VentanaAdmin);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ResultadosTemporada.setModel(new TablaResultadosUsuario());

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        BotonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (comboBoxModificar.getSelectedIndex()) {
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
                        VentanaUsuario ventanaUsuario = new VentanaUsuario();
                        break;

                }
            }
        });


        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada;
                filaSeleccionada = ResultadosTemporada.getSelectedRow();


                if(victoriaDerrotaRadioButton.isSelected()){
                    //CAMBIAR EN EL ARRAY TEMPORADA (El generado por el nucleo) EL VALOR RESULTADO DEL PARTIDO "filaSeleccionada" a 1 (Victoria / Derrota)
                }else if(derrotaVictoriaRadioButton.isSelected()){
                    //CAMBIAR EN EL ARRAY TEMPORADA (El generado por el nucleo) EL VALOR RESULTADO DEL PARTIDO "filaSeleccionada" a 2 (Derrota / Victoria)
                }else{
                    //CAMBIAR EN EL ARRAY TEMPORADA (El generado por el nucleo) EL VALOR RESULTADO DEL PARTIDO "filaSeleccionada" a 0 (Empate)
                }
            }
        });
    }

}
