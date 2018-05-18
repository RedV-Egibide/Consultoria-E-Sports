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
                double salario = Double.parseDouble(TextoSalario.getText());

                boolean creado = jugadorBD.registrarJugador(nickname, nombre, apellido, salario, TextoInformativo);

                if (creado) {
                    TextoInformativo.setText("Jugador registrado correctamente");
                    TextoNombre.setText("");
                    TextoApellido.setText("");
                    TextoNickname.setText("");
                    TextoSalario.setText("");
                } else {
                    TextoNickname.setText("");
                }

            }
        });

        BotonBuscar.addActionListener(new ActionListener() {//BOTON PARA BUSCAR DATOS DE JUGADOR YA REGISTRADO
            @Override
            public void actionPerformed(ActionEvent e) {
                String nickname = TextoNickname.getText();//NICKNAME DE JUGADOR A MOSTRAR DATOS

                jugador = jugadorBD.buscarJugador(nickname, TextoInformativo);

                //DATOS A RELLENAR DESDE LA BASE DE DATOS
                TextoNombre.setText(jugador.getNombre_jugador());
                TextoApellido.setText(jugador.getApellido_jugador());
                TextoSalario.setText(String.valueOf(jugador.getSalario()));
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
                double salario;

                if (TextoSalario.getText().trim().equalsIgnoreCase("")) {
                    salario = 1.0;
                } else {
                    salario = Double.parseDouble(TextoSalario.getText().trim());
                }

                if (nuevoNick.equalsIgnoreCase(jugador.getNickname()) || nuevoNick.equalsIgnoreCase("")) {
                    TextoInformativo.setText("Error: Campo Nickname vacío");
                } else if (nombre.equalsIgnoreCase(jugador.getNombre_jugador()) && apellido.equalsIgnoreCase(jugador.getApellido_jugador()) && salario == jugador.getSalario()) {
                    TextoInformativo.setText("Error: Nada que modificar");
                } else if (nombre.equalsIgnoreCase("") && apellido.equalsIgnoreCase("") && TextoSalario.getText().trim().equalsIgnoreCase("")) {
                    TextoInformativo.setText("Error: Campos vacíos");
                } else if (salario < 10302.60) {
                    TextoInformativo.setText("Error: Salario menor a SMI");
                } else {
                    boolean modificado = jugadorBD.actualizarJugador(jugador.getNickname(), nuevoNick, nombre, apellido, salario);

                    if (modificado) {
                        TextoInformativo.setText("Jugador modificado correctamente");
                    } else {
                        TextoInformativo.setText("Error: Jugador no modificado");
                    }
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


