package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCrearUsuario {
    private JPanel VentanaCrearUsuario;
    private JButton modificarDatosButton;
    private JButton eliminarButton;
    private JTextField TextoUsuario;
    private JPasswordField TextoContraseña;
    private JPasswordField TextoConfirmarContraseña;
    private JLabel textoInformativo;
    private JButton cancelarButton;
    private JButton crearButton;
    private JButton BotonBuscar;

    private UsuarioBD usuarioBD;
    String usuarioBuscado;

    public VentanaCrearUsuario() {
        JFrame frame = new JFrame("VentanaCrearUsuario");
        frame.setContentPane(VentanaCrearUsuario);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        usuarioBD = new UsuarioBD();

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        //LOS NOMBRES DE LOS JTEXT SON LOS SIGUIENTES
        //TextoUsuario (PK: LA BUSQUEDA DE DATOS SE HARÁ EN BASE A ESTE TEXTO)
        //TextoContraseña
        //TextoConfirmarContraseña

        //AQUI ESTÁN LAS TRES POSIBILIDADES

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //SOLO SI LA CONTRASEÑA Y LA CONFIRMACIÓN DE LA CONTRASEÑA SON IGUALES PROSEGUIRÁ LA APLICACIÓN.
                if (TextoContraseña.getText().trim().equals(TextoConfirmarContraseña.getText().trim())) {

                    String nombreUsuario = TextoUsuario.getText().toUpperCase().trim();
                    String contraseña = TextoContraseña.getText().trim();
                    boolean usuarioValido;

                    usuarioValido = usuarioBD.resgistrarUsuario(nombreUsuario, contraseña);

                    if (usuarioValido) {     //ESTE IF SE ENCARGA DE AVISAR DEL PROBLEMA O REGISTRO DEL USUARIO
                        textoInformativo.setText("Usuario registrado correctamente");

                        TextoUsuario.setText("");
                        TextoContraseña.setText("");
                        TextoConfirmarContraseña.setText("");
                    } else {
                        textoInformativo.setText("El nombre de usuario ya está en uso");
                        TextoUsuario.setText("");
                    }

                } else {
                    textoInformativo.setText("Contraseñas no coinciden ");
                    TextoContraseña.setText("");
                    TextoConfirmarContraseña.setText("");
                }
            }
        });

        modificarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nueva_pass = TextoContraseña.getText().trim();
                String nuevo_usuario = TextoUsuario.getText().toUpperCase().trim();
                boolean actualizado = false;

                if (nueva_pass.equals(TextoConfirmarContraseña.getText().trim()) && !(nueva_pass.equals(""))) {
                    if (usuarioBuscado.equalsIgnoreCase(nuevo_usuario)) {
                        actualizado = usuarioBD.actualizarUsuario(usuarioBuscado, usuarioBuscado, nueva_pass);
                        if (actualizado) {
                            textoInformativo.setText("Usuario Actualizado");
                        } else {
                            textoInformativo.setText("Error: Usuario no actualizado");
                        }
                    } else {
                        actualizado = usuarioBD.actualizarUsuario(usuarioBuscado, nuevo_usuario, nueva_pass);
                        if (actualizado) {
                            textoInformativo.setText("Usuario Actualizado");
                        } else {
                            textoInformativo.setText("Error: Usuario no actualizado");
                        }
                    }

                } else {
                    textoInformativo.setText("¡Contraseñas no coinciden o no válida!");
                }

            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean eliminado = usuarioBD.eliminarUsuario(TextoUsuario.getText().toUpperCase().trim());
                if (eliminado) {
                    textoInformativo.setText("Usuario eliminado: " + TextoUsuario.getText().toUpperCase().trim());
                } else {
                    textoInformativo.setText("Error: usuario no eliminado");
                }

            }
        });

        BotonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario u = usuarioBD.buscarUsuario(TextoUsuario.getText().toUpperCase().trim(), textoInformativo);
                TextoContraseña.setText(u.getContraseña());
                TextoConfirmarContraseña.setText(u.getContraseña());
                textoInformativo.setText("ROL: " + u.getRol().name());
                usuarioBuscado = u.getUsuario();
            }
        });
    }

    public JLabel getTextoInformativo() {
        return textoInformativo;
    }

    public void setTextoInformativo(JLabel textoInformativo) {
        this.textoInformativo = textoInformativo;
    }

    public String getUsuarioBuscado() {
        return usuarioBuscado;
    }

    public void setUsuarioBuscado(String usuarioBuscado) {
        this.usuarioBuscado = usuarioBuscado;
    }
}
