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


    public VentanaCrearUsuario() {
        JFrame frame = new JFrame("VentanaCrearUsuario");
        frame.setContentPane(VentanaCrearUsuario);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



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
                //ESTE BLOQUE DE JTEXT ESTÁ DIRECTAMENTE EXTRAIDO DE LA VENTANA "SIGN IN", POR LO QUE PODRÁS REUTILIZAR EL CÓDIGO PARA EL MISMO.

            }
        });





        modificarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //MODIFICAR (ACTUALIZAR LOS CAMPOS CON LA NUEVA INFO DE LOS JTEXT)

            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ELIMINAR (ELIMINAR EL USUARIO SELECCIONADO ("TextoUsuario"))

            }
        });



        BotonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RELLENAR LOS CAMPOS DEl "USUARIO" ESCRITO EN EL JTEXT "TextoUsuario"
            }
        });
    }




}
