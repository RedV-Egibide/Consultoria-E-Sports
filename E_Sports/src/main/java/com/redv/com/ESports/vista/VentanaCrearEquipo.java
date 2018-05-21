package com.redv.com.ESports.vista;

import com.redv.com.ESports.modelo.Dueño;
import com.redv.com.ESports.modelo.DueñoBD;
import com.redv.com.ESports.modelo.EquipoBD;
import com.redv.com.ESports.modelo.Jugador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VentanaCrearEquipo {
    private JPanel VentanaCrearEquipo;
    private JTextField TextoNombreEquipo;
    private JComboBox Jugador1;
    private JComboBox Jugador3;
    private JComboBox Jugador2;
    private JComboBox Jugador4;
    private JComboBox Jugador5;
    private JComboBox Jugador6;
    private JButton cancelarButton;
    private JButton crearButton;
    private JComboBox dueño;
    private JLabel TextoInformativo;
    private JButton BotonBuscar;
    private JButton eliminarButton;
    private JButton modificarDatosButton;

    private EquipoBD equipoBD;
    private DueñoBD dueñoBD;
    private String nombreEquipo;
    private Set<Jugador> jugNoRep;
    private Dueño seleccionado;
    private Dueño propietario;
    private List<Jugador> jugadoresDisponibles;
    private List<Dueño> dueñosDisponibles;
    private List<Jugador> jugsEquipo;
    private boolean buscado;

    public VentanaCrearEquipo() {
        JFrame frame = new JFrame("VentanaCrearEquipo");
        frame.setContentPane(VentanaCrearEquipo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        equipoBD = new EquipoBD();
        dueñoBD = new DueñoBD();
        jugNoRep = new HashSet<>();
        jugadoresDisponibles = new ArrayList<>();
        dueñosDisponibles = new ArrayList<>();
        buscado = false;

        jugadoresDisponibles.add(new Jugador(" "));
        dueñosDisponibles.add(new Dueño(" ", " "));

        //ESTOS SON LOS ARRAYS QUE DEBES DE CONECTAR A LA BASE DE DATOS PARA PODER CREAR EL EQUIPO. (BASE DE DATOS)

        jugadoresDisponibles.addAll(equipoBD.cargarJugadoresDisponibles());//ESTA ES LA LISTA DE JUGADORES DISPONIBLES PARA ELEGIR EN EL NUEVO EQUIPO. (CARGARÁ DESDE LA BASE DE DATOS)
        dueñosDisponibles.addAll(dueñoBD.cargarDueñosDisponibles());//DUEÑOS DISPONIBLES PARA SER PROPIETARIOS DEL EQUIPO. (CARGARÁ DESDE LA BASE DE DATOS)

        visualizarCombosJugadorAlInicio();

        for (int i = 0; i < dueñosDisponibles.size(); i++) {
            dueño.addItem(dueñosDisponibles.get(i).getNombre_dueño() + " " + dueñosDisponibles.get(i).getApellido_dueño());
        }

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean equipoOk = false;
                boolean jugadoresOk;
                boolean dueñoOk;
                int ok = 0;
                boolean creado = false;

                //Nombre Equipo aquí
                nombreEquipo = TextoNombreEquipo.getText().toUpperCase().trim();

                //gestionar nombre de equipo no introducido
                if (nombreEquipo.equalsIgnoreCase("")) {
                    TextoInformativo.setText("Equipo no introducido");
                } else {  //solo se puede crear equipos si no se ha buscado nada antes.

                    Set<Jugador> jnr = jugNoRep;

                    jugadoresOk = comprobarCombosJugadores(jugadoresDisponibles);
                    System.out.println("Jugadores OK: " + jugadoresOk);

                    for (Jugador j : jnr) {

                        if (jugadoresOk) {
                            dueñoOk = comprobarComboDueño(dueño, dueñosDisponibles);
                            System.out.println("Dueño OK: " + dueñoOk);
                            if (dueñoOk) {
                                if (!creado) {
                                    equipoOk = equipoBD.crearEquipo(nombreEquipo, TextoInformativo);
                                    creado = true;
                                }
                                if (equipoOk) {
                                    equipoBD.confeccionarEquipo(j, nombreEquipo, true);
                                    equipoBD.asignarDueño_a_Eq(nombreEquipo, seleccionado.getUsuario());
                                    ok++;
                                } else {
                                    jugNoRep.clear();
                                }
                            } else {
                                //reinicia el set
                                jugNoRep.clear();
                            }
                        } else {
                            jugNoRep.clear();
                        }
                    }
                    if (ok == jnr.size() && ok != 0) {
                        JOptionPane.showMessageDialog(VentanaCrearEquipo, "¡Equipo creado correctamente!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                    }

                }
            }
        });

        BotonBuscar.addActionListener(new ActionListener() {//BOTON PARA BUSCAR DATOS DE EQUIPO YA REGISTRADO
            @Override
            public void actionPerformed(ActionEvent e) {
                crearButton.setEnabled(false);
                TextoInformativo.setText(" ");
                nombreEquipo = TextoNombreEquipo.getText().toUpperCase().trim();//NOMBRE DEL EQUIPO A MOSTRAR DATOS

                boolean sinJugs = false;

                if (nombreEquipo.equalsIgnoreCase("")) {
                    TextoInformativo.setText("Equipo no introducido");
                } else {
                    //CARGAMOS LA LISTA DE JUGADORES DEL EQUIPO SI TIENE
                    jugsEquipo = equipoBD.buscarJugadoresEquipo(nombreEquipo);

                    if (jugsEquipo.size() > 0) {

                        for (int i = 0; i < jugsEquipo.size(); i++) {

                            if (i == 0) {
                                Jugador1.insertItemAt(jugsEquipo.get(0), 0);
                                Jugador1.setSelectedItem(jugsEquipo.get(0));
                            }
                            if (i == 1) {
                                Jugador2.insertItemAt(jugsEquipo.get(1), 0);
                                Jugador2.setSelectedItem(jugsEquipo.get(1));
                            }
                            if (i == 2) {
                                Jugador3.insertItemAt(jugsEquipo.get(2), 0);
                                Jugador3.setSelectedItem(jugsEquipo.get(2));
                            }
                            if (i == 3) {
                                Jugador4.insertItemAt(jugsEquipo.get(3), 0);
                                Jugador4.setSelectedItem(jugsEquipo.get(3));
                            }
                            if (i == 4) {
                                Jugador5.insertItemAt(jugsEquipo.get(4), 0);
                                Jugador5.setSelectedItem(jugsEquipo.get(4));
                            }
                            if (i == 5) {
                                Jugador6.insertItemAt(jugsEquipo.get(5), 0);
                                Jugador6.setSelectedItem(jugsEquipo.get(5));
                            }
                        }

                    } else {
                        sinJugs = true;
                    }

                    //CARGAMOS EL DUEÑO DEL EQUIPO SI TIENE
                    propietario = equipoBD.buscarDueñoEquipo(nombreEquipo);

                    if (propietario != null) {
                        //añadir dueño a la lista dueñosDisponibles (poniendolo el primero)
                        dueño.insertItemAt(propietario, 0);
                        dueño.setSelectedItem(propietario);

                    } else {
                        if (sinJugs) {
                            TextoInformativo.setText("Equipo no existe / sin jugadores ni Dueño");
                        } else {
                            TextoInformativo.setText("Equipo sin dueño");

                        }
                    }
                    //SI OK ->
                    buscado = true;
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
                Set<Jugador> jugsParaActualizar = null;
                Dueño d_para_actualizar;
                //SE DEBE CONTROLAR QUE SE BUSQUE UN EQUIPO PRIMERO
                if (buscado) {
                    //EQUIPO
                    if (!(nombreEquipo.equalsIgnoreCase(TextoNombreEquipo.getText().trim()))) {
                        TextoInformativo.setText("Modificar nombre equipo NO PERMITIDO");
                    } else {
                        //DUEÑO
                        d_para_actualizar = dueñoParaActualizar();

                        if (d_para_actualizar != null) {
                            //JUGADORES
                            if (jugsEquipo.size() < 6) {
                                jugsParaActualizar = jugadoresParaActualizar();

                                if (jugsParaActualizar != null) {
                                    //llamada a BD para actualizar jugadores nuevos
                                    for (Jugador j : jugsParaActualizar) {
                                        equipoBD.confeccionarEquipo(j, nombreEquipo, true);
                                    }
                                }

                            } else {
                                TextoInformativo.setText("Lista Jugadores cerrada para esta temporada");
                            }
                            //llamada a BD para actualizar dueño nuevo
                            if (!(d_para_actualizar == propietario)) {
                                equipoBD.asignarDueño_a_Eq(null, propietario.getUsuario());
                                equipoBD.asignarDueño_a_Eq(nombreEquipo, d_para_actualizar.getUsuario());
                            }
                            JOptionPane.showMessageDialog(VentanaCrearEquipo, "¡Equipo actualizado correctamente!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                            frame.dispose();

                        } else {
                            TextoInformativo.setText("Debe especificar un dueño");
                        }
                    }

                } else {
                    TextoInformativo.setText("¡Obligatorio buscar 1º un equipo a modificar!");
                }

            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEquipo = TextoNombreEquipo.getText().toUpperCase().trim();
                boolean eliminado = equipoBD.eliminarEquipo(nombreEquipo, TextoInformativo);

                if (eliminado) {
                    TextoInformativo.setText("¡Equipo eliminado correctamente!");
                }

            }
        });
    }

    public void visualizarCombosJugadorAlInicio() {

        for (int i = 0; i < jugadoresDisponibles.size(); i++) {//ESTE BUCLE SE ENCARGA DE MOSTRAR AL USUARIO LOS JUGADORES DISPONIBLES.
            Jugador1.addItem(jugadoresDisponibles.get(i).getNickname());
            Jugador2.addItem(jugadoresDisponibles.get(i).getNickname());
            Jugador3.addItem(jugadoresDisponibles.get(i).getNickname());
            Jugador4.addItem(jugadoresDisponibles.get(i).getNickname());
            Jugador5.addItem(jugadoresDisponibles.get(i).getNickname());
            Jugador6.addItem(jugadoresDisponibles.get(i).getNickname());
        }
    }

    public boolean comprobarCombosJugadores(List<Jugador> jugadoresDisponibles) {
        boolean noRepetido = false;
        List<Jugador> jugadoresEleg = new ArrayList<>();

        int jug_1 = Jugador1.getSelectedIndex();
        int jug_2 = Jugador2.getSelectedIndex();
        int jug_3 = Jugador3.getSelectedIndex();
        int jug_4 = Jugador4.getSelectedIndex();
        int jug_5 = Jugador5.getSelectedIndex();
        int jug_6 = Jugador6.getSelectedIndex();

        //almacenamos en list los combos no vacios

        if (jug_1 > 0) {
            Jugador jug1 = jugadoresDisponibles.get(jug_1);
            jugadoresEleg.add(jug1);
        } else {
            System.out.println(jug_1 + "1 no añadido");
        }
        //----------------
        if (jug_2 > 0) {
            Jugador jug2 = jugadoresDisponibles.get(jug_2);
            jugadoresEleg.add(jug2);
        } else {
            System.out.println(jug_2 + "2 no añadido");
        }
        //-----------------
        if (jug_3 > 0) {
            Jugador jug3 = jugadoresDisponibles.get(jug_3);
            jugadoresEleg.add(jug3);
        } else {
            System.out.println(jug_3 + "3 no añadido");
        }
        //----------------
        if (jug_4 > 0) {
            Jugador jug4 = jugadoresDisponibles.get(jug_4);
            jugadoresEleg.add(jug4);
        } else {
            System.out.println(jug_4 + "4 no añadido");
        }
        //----------------
        if (jug_5 > 0) {
            Jugador jug5 = jugadoresDisponibles.get(jug_5);
            jugadoresEleg.add(jug5);
        } else {
            System.out.println(jug_5 + "5 no añadido");
        }
        //----------------
        if (jug_6 > 0) {
            Jugador jug6 = jugadoresDisponibles.get(jug_6);
            jugadoresEleg.add(jug6);
        } else {
            System.out.println(jug_6 + "6 no añadido");
        }

        //almacenamos en set: si no hay algún repetido devolvemos true

        for (Jugador j : jugadoresEleg) {
            noRepetido = jugNoRep.add(j);

            if (!noRepetido) {
                TextoInformativo.setText("Error: Jugador repetido: " + j.getNickname());
                return false;
            }
        }

        if (!(jugNoRep.size() > 0)) {
            TextoInformativo.setText("Error: Mínimo un jugador");
            return false;
        }

        return noRepetido;

    }

    public boolean comprobarComboDueño(JComboBox dueño, List<Dueño> disponibles) {
        if (dueño.getSelectedIndex() > 0) {
            seleccionado = disponibles.get(dueño.getSelectedIndex());
            return true;
        }
        TextoInformativo.setText("Obligatorio elegir un dueño disponible");
        return false;
    }

    public Dueño dueñoParaActualizar() {

        if (dueño.getSelectedIndex() > 1) {
            int d = dueño.getSelectedIndex() - 1;

            return dueñosDisponibles.get(d);

        } else {
            if (dueño.getSelectedIndex() == 0) {
                return propietario;
            }
            if (dueño.getSelectedIndex() == 1) {
                TextoInformativo.setText("Debe elegir un dueño");
            }
            return null;
        }
    }

    public Set<Jugador> jugadoresParaActualizar() {

        Set<Jugador> actualizarNoRep = new HashSet<>();
        boolean noRep = true;

        if (Jugador1.getSelectedIndex() > 0) {
            int j1 = Jugador1.getSelectedIndex();
            Jugador jug1 = jugadoresDisponibles.get(j1);
            if (!(jugsEquipo.contains(jug1))) {
                noRep = actualizarNoRep.add(jug1);
            }
        }
        if (Jugador2.getSelectedIndex() > 0) {
            int j2 = Jugador2.getSelectedIndex();
            Jugador jug2 = jugadoresDisponibles.get(j2);
            if (!(jugsEquipo.contains(jug2))) {
                noRep = actualizarNoRep.add(jug2);
            }
        }
        if (Jugador3.getSelectedIndex() > 0) {
            int j3 = Jugador3.getSelectedIndex();
            Jugador jug3 = jugadoresDisponibles.get(j3);
            if (!(jugsEquipo.contains(jug3))) {
                noRep = actualizarNoRep.add(jug3);
            }
        }
        if (Jugador4.getSelectedIndex() > 0) {
            int j4 = Jugador4.getSelectedIndex();
            Jugador jug4 = jugadoresDisponibles.get(j4);
            if (!(jugsEquipo.contains(jug4))) {
                noRep = actualizarNoRep.add(jug4);
            }
        }
        if (Jugador5.getSelectedIndex() > 0) {
            int j5 = Jugador5.getSelectedIndex();
            Jugador jug5 = jugadoresDisponibles.get(j5);
            if (!(jugsEquipo.contains(jug5))) {
                noRep = actualizarNoRep.add(jug5);
            }
        }
        if (Jugador6.getSelectedIndex() > 0) {
            int j6 = Jugador6.getSelectedIndex();
            Jugador jug6 = jugadoresDisponibles.get(j6);
            if (!(jugsEquipo.contains(jug6))) {
                noRep = actualizarNoRep.add(jug6);
            }
        }

        if (noRep) {
            if (actualizarNoRep.size() > 0) {
                return actualizarNoRep;
            } else {
                System.out.println("Ninún jugador añadido a plantilla actual");
                return null;
            }

        } else {
            TextoInformativo.setText("Jugador Repetido");
            return null;

        }
    }
}
