package com.redv.com.ESports.vista;

import com.redv.com.ESports.modelo.Dueño;
import com.redv.com.ESports.modelo.DueñoBD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearDueño {
    private JPanel VentanaCrearDueño;
    private JTextField TextoNombre;
    private JButton cancelarButton;
    private JButton crearButton;
    private JTextField TextoApellido;
    private JTextField TextoUsuario;
    private JLabel TextoInformativo;
    private JButton BotonBuscar;
    private JButton modificarDatosButton;
    private JButton eliminarButton;
    private JTextField textoNuevoDueño;

    private DueñoBD dueñoBD;
    private Dueño dueño;

    public VentanaCrearDueño() {
        JFrame frame = new JFrame("VentanaCrearDueño");
        frame.setContentPane(VentanaCrearDueño);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        dueñoBD = new DueñoBD();

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = TextoNombre.getText().toUpperCase().trim();
                String apellido = TextoApellido.getText().toUpperCase().trim();
                String usuario = TextoUsuario.getText().toUpperCase().trim();

                boolean creado = dueñoBD.crearDueño(usuario, nombre, apellido, TextoInformativo);

                if (creado) {

                    TextoInformativo.setText("Dueño registrado correctamente");
                    TextoNombre.setText("");
                    TextoApellido.setText("");
                    TextoUsuario.setText("");

                } else {
                    System.out.println("Error: Dueño no creado");
                }

            }
        });

        BotonBuscar.addActionListener(new ActionListener() {//BOTON PARA BUSCAR DATOS DE DUEÑO YA REGISTRADO
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = TextoUsuario.getText().toUpperCase().trim();//USUARIO DEL DUEÑO A MOSTRAR DATOS

                dueño = dueñoBD.buscarDueño(usuario, TextoInformativo);

                //DATOS A RELLENAR DESDE LA BASE DE DATOS
                TextoNombre.setText(dueño.getNombre_dueño());
                TextoApellido.setText(dueño.getApellido_dueño());

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
                String nombre = TextoNombre.getText().toUpperCase().trim();
                String apellido = TextoApellido.getText().toUpperCase().trim();

                String usuario = TextoUsuario.getText().toUpperCase().trim();

                if (usuario.equalsIgnoreCase(dueño.getUsuario())) {
                    if (!(nombre.equalsIgnoreCase(dueño.getNombre_dueño()) && apellido.equalsIgnoreCase(dueño.getApellido_dueño()))) {

                        if (!(nombre.equalsIgnoreCase("") && apellido.equalsIgnoreCase(""))) {
                            boolean modificado = dueñoBD.actualizarDueño(dueño.getUsuario(), nombre, apellido);
                            if (modificado) {
                                TextoInformativo.setText("Dueño modificado correctamente: " + dueño.getUsuario());
                            } else {
                                TextoInformativo.setText("Error: dueño no modificado: " + dueño.getUsuario());
                            }
                        } else {
                            TextoInformativo.setText("Error: Campo/s vacío/s");
                        }
                    } else {
                        TextoInformativo.setText("Debe cambiar algún campo para modificar");
                    }
                } else {
                    TextoInformativo.setText("Cambio usuario sólo en Crear/Modificar Usuario");
                }
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nuevoDueño = textoNuevoDueño.getText().trim().toUpperCase();
                if (nuevoDueño.equalsIgnoreCase("")) {
                    nuevoDueño = null;
                }

                boolean eliminado = dueñoBD.eliminarDueño(dueño.getUsuario(), nuevoDueño);

                if (eliminado) {
                    TextoInformativo.setText("Dueño eliminado correctamente: " + dueño.getUsuario());
                    TextoUsuario.setText("");
                    TextoNombre.setText("");
                    textoNuevoDueño.setText("");
                    TextoApellido.setText("");
                } else {
                    TextoInformativo.setText("Error: dueño no eliminado: " + dueño.getUsuario());
                }

            }
        });

    }

    public JLabel getTextoInformativo() {
        return TextoInformativo;
    }

    public void setTextoInformativo(JLabel textoInformativo) {
        TextoInformativo = textoInformativo;
    }
}
