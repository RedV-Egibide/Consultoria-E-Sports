package com.redv.com.ESports;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoBD {

    private Connection connection;
    private VentanaConfeccionarEquipo ventanaConfeccionarEquipo;

    public List<Jugador> cargarJugadoresDisponibles() {
        connection = ConexionBD.conectar();
        List<Jugador> disponibles = new ArrayList<>();
        //select

        if (connection != null) {
            // Consulta simple
            try {
                Statement stmt = connection.createStatement();

                ResultSet rset = stmt.executeQuery("SELECT NICKNAME, NOMBRE, APELLIDO, SALARIO FROM JUGADOR WHERE EQUIPO IS NULL");

                while (rset.next()) {
                    String nick = rset.getString("NICKNAME");
                    String nombre = rset.getString("NOMBRE");
                    String apellido = rset.getString("APELLIDO");
                    double salario = rset.getDouble("SALARIO");

                    Jugador jugador = new Jugador(nick, nombre, apellido, salario);
                    disponibles.add(jugador);
                    System.out.println(nick + "añadido a lista disponibles");
                }

                rset.close();
                stmt.close();
                ConexionBD.desconectar(connection);

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } else {
            System.out.println("Sin conexión con BD");
        }

        return disponibles;
    }

    public boolean confeccionarEquipo(Jugador jugador, String equipo) {

        connection = ConexionBD.conectar();

        if (connection != null) {
            //crear equipo en tabla Equipo (insert)
            try {
                PreparedStatement st = connection.prepareStatement("INSERT INTO EQUIPO VALUES (?)");

                st.setString(1, equipo);

                st.executeUpdate();

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
                return false;
            }
            //llamada a metodo "ACTUALIZAR_JUGADOR en paquete "CRUD_JUGADOR"
            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_JUGADOR.ACTUALIZAR_JUGADOR(?,?,?,?,?,?) }";
                CallableStatement csa1 = connection.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_NICK", jugador.getNickname());
                csa1.setString("P_NUEVO_NICK", null);
                csa1.setString("P_NOM", null);
                csa1.setString("P_APE", null);
                //csa1.setDouble("P_SAL", Double.parseDouble("null")); //TODO: PREGUNTAR COMO PASAR A BD
                csa1.setString("P_EQU", equipo);

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento CRUD_JUGADOR.ACTUALIZAR_JUGADOR ejecutado");
                csa1.close();
                ConexionBD.desconectar(connection);
                return true;

            } catch (SQLException e) { // Controlamos los errores que queremos sacar
                System.out.println("ERROR: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Sin conexión a BD");
        }
        return false;
    }

    public void rollBack(Jugador jugador) {

        connection = ConexionBD.conectar();

        //ROLLBACK DEL INSERT EN EQUIPO

        if (connection != null) {
            //llamada a metodo "ACTUALIZAR_JUGADOR en paquete "CRUD_JUGADOR"
            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_JUGADOR.ACTUALIZAR_JUGADOR(?,?,?,?,?,?) }";
                CallableStatement csa1 = connection.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_NICK", jugador.getNickname());
                csa1.setString("P_NUEVO_NICK", null);
                csa1.setString("P_NOM", null);
                csa1.setString("P_APE", null);
                csa1.setDouble("P_SAL", Double.parseDouble(null)); //TODO: PREGUNTAR A ION SI VALIDO
                csa1.setString("P_EQU", null);

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento CRUD_JUGADOR.ACTUALIZAR_JUGADOR ejecutado");
                csa1.close();
                ConexionBD.desconectar(connection);

            } catch (SQLException e) { // Controlamos los errores que queremos sacar
                System.out.println("ERROR: " + e.getMessage());
            }
        } else {
            System.out.println("Sin conexión con BD");
        }
    }
}
