package com.redv.com.ESports.vista;

import com.redv.com.ESports.modelo.EquipoBD;
import com.redv.com.ESports.modelo.Jugador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VentanaConfeccionarEquipo {
    private JButton atrasButton;
    private JButton confeccionarButton;
    private JComboBox jugador1;
    private JComboBox jugador3;
    private JComboBox jugador2;
    private JComboBox jugador4;
    private JComboBox jugador5;
    private JComboBox jugador6;
    private JLabel TextoNombreEquipo;
    private JPanel VentanaConfeccionarEquipo;
    private JTextField nomEquipo;
    private JLabel info;

    private EquipoBD equipoBD;
    private String nombreEquipo;
    private boolean confeccionarEjecutado;

    public VentanaConfeccionarEquipo() {
        JFrame frame = new JFrame("VentanaConfeccionarEquipo");
        frame.setContentPane(VentanaConfeccionarEquipo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        equipoBD = new EquipoBD();
        confeccionarEjecutado = false;

        //cargar lista jugadores aquí
        List<Jugador> jugadoresDisp = equipoBD.cargarJugadoresDisponibles();
        cargarCombos(jugadoresDisp);

        confeccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int contador = 0;
                boolean asignado;
                boolean equipoCreado = false;
                boolean repetido = false;
                double salTotal = 0;

                //Nombre Equipo aquí
                nombreEquipo = nomEquipo.getText().toUpperCase().trim();

                //gestionar nombre de equipo no introducido
                if (nombreEquipo.equalsIgnoreCase("")) {
                    System.out.println("Equipo no introducido");
                    info.setText("Equipo no introducido");

                } else {

                    List<Jugador> jugadoresEleg = new ArrayList<>();

                    Jugador jug_1 = (Jugador) jugador1.getSelectedItem();
                    Jugador jug_2 = (Jugador) jugador2.getSelectedItem();
                    Jugador jug_3 = (Jugador) jugador3.getSelectedItem();
                    Jugador jug_4 = (Jugador) jugador4.getSelectedItem();
                    Jugador jug_5 = (Jugador) jugador5.getSelectedItem();
                    Jugador jug_6 = (Jugador) jugador6.getSelectedItem();

                    if (jug_1 != null) {

                        if (!(jug_1.getNickname().equalsIgnoreCase(" "))) {
                            jugadoresEleg.add(jug_1);
                        } else {
                            System.out.println(jug_1 + " no añadido");
                        }
                    } else {
                        System.out.println(jug_1 + " null");
                    }
                    //----------------
                    if (jug_2 != null) {

                        if (!(jug_2.getNickname().equalsIgnoreCase(" "))) {
                            jugadoresEleg.add(jug_2);
                        } else {
                            System.out.println(jug_2 + " no añadido");
                        }
                    } else {
                        System.out.println(jug_2 + " null");
                    }
                    //-----------------
                    if (jug_3 != null) {

                        if (!(jug_3.getNickname().equalsIgnoreCase(" "))) {
                            jugadoresEleg.add(jug_3);
                        } else {
                            System.out.println(jug_3 + " no añadido");
                        }
                    } else {
                        System.out.println(jug_3 + " null");
                    }
                    //----------------
                    if (jug_4 != null) {

                        if (!(jug_4.getNickname().equalsIgnoreCase(" "))) {
                            jugadoresEleg.add(jug_4);
                        } else {
                            System.out.println(jug_4 + " no añadido");
                        }
                    } else {
                        System.out.println(jug_4 + " null");
                    }
                    //----------------
                    if (jug_5 != null) {

                        if (!(jug_5.getNickname().equalsIgnoreCase(" "))) {
                            jugadoresEleg.add(jug_5);
                        } else {
                            System.out.println(jug_5 + " no añadido");
                        }
                    } else {
                        System.out.println(jug_5 + " null");
                    }

                    if (jug_6 != null) {

                        if (!(jug_6.getNickname().equalsIgnoreCase(" "))) {
                            jugadoresEleg.add(jug_6);
                        } else {
                            System.out.println(jug_6 + " no añadido");
                        }
                    } else {
                        System.out.println(jug_6 + " null");
                    }

                    Set<Jugador> jugNoRep = new HashSet<>();

                    for (Jugador j : jugadoresEleg) {
                        if (jugNoRep.contains(j)) {
                            System.out.println("Error: Jugador repetido");
                            JOptionPane.showMessageDialog(VentanaConfeccionarEquipo, "No es posible elegir al mismo jugador más de una vez: " + j.getNickname(), "¡Jugador Repetido!", JOptionPane.ERROR_MESSAGE);
                            repetido = true;
                        } else {
                            jugNoRep.add(j);
                            salTotal += j.getSalario();
                            info.setText("Salario Total: " + String.valueOf(salTotal));
                        }
                    }

                    //gestión salario máximo

                    if (salTotal <= 200000) {
                        if (!repetido) {
                            //actualizar el campo equipo de los jugadoresEleg en BD
                            for (Jugador j : jugNoRep) {
                                asignado = equipoBD.confeccionarEquipo(j, nombreEquipo, equipoCreado);
                                if (asignado) {
                                    contador++;
                                }
                                if (contador == 1) {
                                    equipoCreado = true;
                                }
                            }
                            if (contador == jugNoRep.size()) {
                                if (contador == 0) {
                                    JOptionPane.showMessageDialog(VentanaConfeccionarEquipo, "¡Error: Debe elegir al menos un jugador!", "Resultado", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(VentanaConfeccionarEquipo, "¡Equipo creado!" + "\n" + nombreEquipo, "Resultado", JOptionPane.INFORMATION_MESSAGE);
                                    confeccionarEjecutado = true;
                                }
                            } else {
                                if (!confeccionarEjecutado) {
                                    for (Jugador j : jugNoRep) {
                                        equipoBD.rollBack(j, nombreEquipo);   //deshacemos los cambios si todos los jugadores elegidos no han sido actualizados correctamente en BD
                                    }
                                    JOptionPane.showMessageDialog(VentanaConfeccionarEquipo, "¡Equipo NO creado!" + "\n" + nombreEquipo, "Resultado", JOptionPane.ERROR_MESSAGE);

                                } else {
                                    JOptionPane.showMessageDialog(VentanaConfeccionarEquipo, "¡No es posible crear equipos más de una vez!" + "\n" + "Contacte con administrador", "Resultado", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(VentanaConfeccionarEquipo, "¡Presupuesto para salarios superado en: " + nombreEquipo + "!", "Resultado", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    public void cargarCombos(List<Jugador> jugadoresDisp) {
        DefaultComboBoxModel<Jugador> modelo1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Jugador> modelo2 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Jugador> modelo3 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Jugador> modelo4 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Jugador> modelo5 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Jugador> modelo6 = new DefaultComboBoxModel<>();

        for (Jugador j : jugadoresDisp) {
            modelo1.addElement(j);
            modelo2.addElement(j);
            modelo3.addElement(j);
            modelo4.addElement(j);
            modelo5.addElement(j);
            modelo6.addElement(j);
        }

        jugador1.setModel(modelo1); //TODO: PREGUNTAR A ION FALLO UNCHECKED CALL
        jugador2.setModel(modelo2);
        jugador3.setModel(modelo3);
        jugador4.setModel(modelo4);
        jugador5.setModel(modelo5);
        jugador6.setModel(modelo6);

    }
}
