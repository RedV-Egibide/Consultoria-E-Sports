package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearJugador {
    private JPanel VentanaCrearJugador;
    private JTextField TextoNombre;
    private JTextField TextoApellido;
    private JTextField TextoNickname;
    private JTextField TextoSalario;
    private JButton cancelarButton;
    private JButton crearButton;
    private JLabel TextoInformativo;
    private JButton BotonBuscar;
    private JButton modificarDatosButton;
    private JButton eliminarButton;

    private JugadorBD jugadorBD;
    private Jugador jugador;

    public VentanaCrearJugador() {
        JFrame frame = new JFrame("VentanaCrearJugador");
        frame.setContentPane(VentanaCrearJugador);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        jugadorBD = new JugadorBD();
        jugador = new Jugador();

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RECOLECTAMOS TODOS LOS DATOS DE LA INTERFAZ Y LOS GUARDAMOS EN VARIABLES.
                String nombre = TextoNombre.getText().toUpperCase().trim();
                String apellido = TextoApellido.getText().toUpperCase().trim();
                String nickname = TextoNickname.getText().toUpperCase().trim();

                if (TextoSalario.getText().trim().equalsIgnoreCase("")) {
                    TextoInformativo.setText("Error: Obligatorio salario, superior a SMI (10302.60)");
                } else {
                    double salario = Double.parseDouble(TextoSalario.getText());

                    if (nickname.equalsIgnoreCase("")) {
                        TextoInformativo.setText("Error: Obligatorio nick");
                    } else {
                        boolean creado = jugadorBD.registrarJugador(nickname, nombre, apellido, salario, TextoInformativo);

                        if (creado) {
                            TextoInformativo.setText("Jugador registrado correctamente");
                            TextoNombre.setText("");
                            TextoApellido.setText("");
                            TextoNickname.setText("");
                            TextoSalario.setText("");
                        }
                    }
                }

            }
        });

        BotonBuscar.addActionListener(new ActionListener() {//BOTON PARA BUSCAR DATOS DE JUGADOR YA REGISTRADO
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = TextoNickname.getText().toUpperCase().trim();//NICKNAME DE JUGADOR A MOSTRAR DATOS

                jugador = jugadorBD.buscarJugador(nickname, TextoInformativo);
                //DATOS A RELLENAR DESDE LA BASE DE DATOS

                if (!(jugador.getSalario() < 10302.60)) {
                    TextoSalario.setText(String.valueOf(jugador.getSalario()));
                    TextoNombre.setText(jugador.getNombre_jugador());
                    TextoApellido.setText(jugador.getApellido_jugador());
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        modificarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoNick = TextoNickname.getText().toUpperCase().trim();
                String nombre = TextoNombre.getText().toUpperCase().trim();
                String apellido = TextoApellido.getText().toUpperCase().trim();

                boolean nickModificado = false;

                if (!(TextoSalario.getText().trim().equalsIgnoreCase(""))) {
                    double salario = Double.parseDouble(TextoSalario.getText().trim());
                    if (salario < 10302.60) {
                        TextoInformativo.setText("Error: Obligatorio salario, superior a SMI (10302.60)");
                    } else if (nuevoNick.equalsIgnoreCase(jugador.getNickname()) &&
                            nombre.equalsIgnoreCase(jugador.getNombre_jugador()) &&
                            apellido.equalsIgnoreCase(jugador.getApellido_jugador()) &&
                            salario == jugador.getSalario()) {
                        TextoInformativo.setText("Nada que modificar");
                    } else {

                        if (nuevoNick.equalsIgnoreCase("") || nuevoNick.equalsIgnoreCase(jugador.getNickname())) {
                            nuevoNick = null;
                        } else if (!(nuevoNick.equalsIgnoreCase(jugador.getNickname()))) {
                            nickModificado = true;
                        }

                        if (nombre.equalsIgnoreCase("")) {
                            nombre = " ";
                        }

                        if (apellido.equalsIgnoreCase("")) {
                            apellido = " ";
                        }

                        boolean modificado = jugadorBD.actualizarJugador(jugador.getNickname(), nuevoNick, nombre, apellido, salario);

                        if (modificado) {
                            TextoInformativo.setText("Jugador modificado correctamente");
                            if (nickModificado) {
                                jugador.setNickname(nuevoNick);
                            }
                        } else {
                            TextoInformativo.setText("Error: Jugador no modificado");
                        }
                    }
                } else {
                    TextoInformativo.setText("Error: Obligatorio salario, superior a SMI (10302.60)");
                }

            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nick = TextoNickname.getText().toUpperCase().trim();

                boolean elminado = jugadorBD.eliminarJugador(nick);

                if (elminado) {
                    TextoInformativo.setText("Jugador eliminado correctamente");
                    TextoNickname.setText("");
                    TextoNombre.setText("");
                    TextoApellido.setText("");
                    TextoSalario.setText("");
                } else {
                    TextoInformativo.setText("Error: Jugador NO eliminado");
                }

            }
        });
    }

}


