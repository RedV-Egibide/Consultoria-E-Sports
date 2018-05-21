package com.redv.com.ESports;

import javax.swing.*;
import java.sql.*;

public class JugadorBD {

    private Connection conexion = null;
    private String equipo = null;

    public boolean registrarJugador(String nick, String nombre, String apellido, double salario, JLabel textoInfo) {
        conexion = ConexionBD.conectar();

        if (conexion != null) {

            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_JUGADOR.INSERTAR_JUGADOR(?,?,?,?) }";
                CallableStatement csa1 = conexion.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_NICK", nick);
                csa1.setString("P_NOM", nombre);
                csa1.setString("P_APE", apellido);
                csa1.setDouble("P_SAL", salario);

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento INSERTAR_JUGADOR ejecutado");

                csa1.close();
                ConexionBD.desconectar(conexion);

                return true;

            } catch (SQLException e) {
                //gestion RAISE_APLICATION_ERROR
                if (e.getErrorCode() == 20000) {
                    textoInfo.setText("Error: Nick no disponible");
                } else {
                    if (e.getErrorCode() == 2290) {
                        textoInfo.setText("Salario inferior a SMI");
                    } else {
                        textoInfo.setText("Error: Jugador NO registrado");
                        System.out.println(DueñoBD.class.getName() + " .registrarJugador() " + e.getMessage() + "\n");
                    }

                }

            }

        } else {
            System.out.println(JugadorBD.class.getName() + " sin conexión a BD en .registrarJugador()");
        }
        return false;
    }

    public Jugador buscarJugador(String nick, JLabel textoInfo) {
        conexion = ConexionBD.conectar();

        String nombre = null;
        String apellido = null;
        double salario = 0.0;

        if (conexion != null) {
            try {
                PreparedStatement st = conexion.prepareStatement("SELECT NOMBRE, APELLIDO, SALARIO, EQUIPO FROM JUGADOR WHERE NICKNAME = (?)");

                st.setString(1, nick);

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    nombre = rs.getString(1);
                    apellido = rs.getString(2);
                    salario = rs.getDouble(3);
                    equipo = rs.getString(4);

                }
                if (salario < 10302.60) {
                    textoInfo.setText("Usuario no existe");
                } else if (!(equipo == null)) {
                    textoInfo.setText("Equipo: " + equipo);
                } else {
                    textoInfo.setText("Equipo: N/D");
                }
                rs.close();
                st.close();
                ConexionBD.desconectar(conexion);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(JugadorBD.class.getName() + " sin conexión a BD en .buscarJugador()");
        }

        return new Jugador(nick, nombre, apellido, salario);
    }

    public boolean eliminarJugador(String nick) {
        conexion = ConexionBD.conectar();

        if (conexion != null) {

            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_JUGADOR.BORRAR_JUGADOR(?) }";
                CallableStatement csa1 = conexion.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_NICK", nick);

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento BORRAR_JUGADOR ejecutado");

                csa1.close();
                ConexionBD.desconectar(conexion);

                return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }

        } else {
            System.out.println(JugadorBD.class.getName() + " sin conexión a BD en .eliminarJugador()");
        }
        return false;
    }

    public boolean actualizarJugador(String nick, String nuevoNick, String nombre, String apellido, double salario) {
        conexion = ConexionBD.conectar();

        if (conexion != null) {

            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_JUGADOR.ACTUALIZAR_JUGADOR(?,?,?,?,?) }";
                CallableStatement csa1 = conexion.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_NICK", nick);
                csa1.setString("P_NUEVO_NICK", nuevoNick);
                csa1.setString("P_NOM", nombre);
                csa1.setString("P_APE", apellido);
                csa1.setDouble("P_SAL", salario);

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento ACTUALIZAR_DUEÑO ejecutado");

                csa1.close();
                ConexionBD.desconectar(conexion);

                return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }

        } else {
            System.out.println(DueñoBD.class.getName() + " sin conexión a BD en .actualizarDueño()");
        }
        return false;
    }
}
