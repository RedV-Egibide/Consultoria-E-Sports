package com.redv.com.ESports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JButton insertarButton;

    private CalendarioBD calendarioBD;
    private TablaResultadosUsuario tablaResultadosUsuario;
    private ArrayList calendario;
    private ArrayList<Equipo> equiposTemporada;

    public VentanaAdmin() {
        JFrame frame = new JFrame("VentanaAdmin");
        frame.setContentPane(VentanaAdmin);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        calendarioBD = new CalendarioBD();
        calendario = calendarioBD.cargarTemporada();
        equiposTemporada = calendarioBD.cargarEquiposTemporada();

        //comprobamos si se ha jugado algún partido de la temporada para deshabilitar el boton de generarTemporada
        for (int i = 0; i < calendario.size(); i++) {
            Calendario cal = (Calendario) calendario.get(i);
            if (cal.getResultado() == 0 || cal.getResultado() == 1 || cal.getResultado() == 2) {
                generarButton.setEnabled(false);
            }
        }

        ResultadosTemporada.setModel(new TablaResultadosUsuario(calendario));//CREACION DE LA TABLA

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
                        VentanaCrearUsuario ventanaCrearUsuario = new VentanaCrearUsuario();
                        break;

                }
            }
        });

        insertarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada;
                filaSeleccionada = ResultadosTemporada.getSelectedRow();//Esta es la línea del array calendario mostrado que ha seleccionado el usuario para escribir los resultados.
                Calendario partidoUpdateRes = null;

                if (victoriaDerrotaRadioButton.isSelected()) {
                    //CAMBIAR EN EL ARRAY DEL CALENDARIO (El generado por el nucleo) EL VALOR RESULTADO DEL PARTIDO "filaSeleccionada" a 1 (Victoria / Derrota)

                    //almacenamos el partido para actualizar posteriormente el valor en BD
                    partidoUpdateRes = (Calendario) calendario.get(filaSeleccionada);
                    partidoUpdateRes.setResultado(1);

                    //actualizamos el array de calendario
                    ((Calendario) calendario.get(filaSeleccionada)).setResultado(1);

                } else if (derrotaVictoriaRadioButton.isSelected()) {
                    //CAMBIAR EN EL ARRAY DEL CALENDARIO (El generado por el nucleo) EL VALOR RESULTADO DEL PARTIDO "filaSeleccionada" a 2 (Derrota / Victoria)

                    //almacenamos el partido para actualizar posteriormente el valor en BD
                    partidoUpdateRes = (Calendario) calendario.get(filaSeleccionada);
                    partidoUpdateRes.setResultado(2);

                    //actualizamos el array de calendario
                    ((Calendario) calendario.get(filaSeleccionada)).setResultado(2);

                } else {
                    //CAMBIAR EN EL ARRAY DEL CALENDARIO (El generado por el nucleo) EL VALOR RESULTADO DEL PARTIDO "filaSeleccionada" a 0 (Empate)

                    //almacenamos el partido para actualizar posteriormente el valor en BD
                    partidoUpdateRes = (Calendario) calendario.get(filaSeleccionada);
                    partidoUpdateRes.setResultado(0);

                    //actualizamos el array de calendario
                    ((Calendario) calendario.get(filaSeleccionada)).setResultado(0);
                }

                //actualiamos puntuación equipos en BD

                //1º actualizamos el array con las puntuaciones
                Nucleo.PuntuarEquipos(calendario, equiposTemporada);

                //llamada a BD para almacenar puntuación
                //EQ1
                boolean punt1_Ok = calendarioBD.almacenarPuntuacion(((Calendario) calendario.get(filaSeleccionada)).getEquipo1());
                //EQ2
                boolean punt2_Ok = calendarioBD.almacenarPuntuacion(((Calendario) calendario.get(filaSeleccionada)).getEquipo2());

                //POR ÚLTIMO ALMACENAMOS LOS RESULTADOS EN BD (LLAMADA A MÉTODO almacenarResultados() en CalendarioBD)
                boolean res = calendarioBD.almacenarResultados(partidoUpdateRes);

            }
        });
        generarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //llamamos a método en CalendarioBD cargarEquiposTemporada y
                equiposTemporada = calendarioBD.cargarEquiposTemporada();

                //llamada a nucleo (ordenar enfrentamientos) pasando el array de equipos, devuelve arrayList partidos (temporada)
                calendario = Nucleo.OrdenarEnfrentamientos(equiposTemporada);

                //llamamos a método almacenarTemporada en CalendarioBD y pasamos arraylist partidos (temporada)
                calendarioBD.almacenarTemporada(calendario);

                //cargamos array temporada en TablaResultadosUsuario(temporada) (le pasamos el array de partidos -temporada- para que se visualice)
                tablaResultadosUsuario = new TablaResultadosUsuario(calendario);
                ResultadosTemporada.setModel(tablaResultadosUsuario);
            }
        });
    }

}
