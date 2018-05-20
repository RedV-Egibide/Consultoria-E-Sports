package com.redv.com.ESports;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipoBD {

    private Connection connection;

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
                    System.out.println(nick + " añadido a lista disponibles");
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

    public boolean asignarDueño_a_Eq(String equipo, String dueño) {

        connection = ConexionBD.conectar();

        if (connection != null) {
            //actualizar equipo en tabla Dueño(update)
            try {
                PreparedStatement st = connection.prepareStatement("UPDATE DUEÑO SET EQUIPO = (?) WHERE USUARIO = (?)");
                st.setString(1, equipo);
                st.setString(2, dueño);
                st.executeUpdate();

                return true;

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println(EquipoBD.class.getName() + " Sin conexión a BD en asignarDueño_a_Eq()");
        }
        return false;
    }

    public boolean crearEquipo(String equipo, JLabel textoInfo) {

        connection = ConexionBD.conectar();

        if (connection != null) {
            //crear equipo en tabla Equipo (insert)
            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_EQUIPO.INSERTAR_EQUIPO(?) }";
                CallableStatement csa1 = connection.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_NOMBRE", equipo);

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento INSERTAR_EQUIPO ejecutado");

                csa1.close();
                ConexionBD.desconectar(connection);

                return true;

            } catch (SQLException e) {
                if (e.getErrorCode() == 20000) {
                    textoInfo.setText("Error: Nombre de equipo no disponible");
                    return false;
                } else {
                    textoInfo.setText("Error: Equipo no creado");
                    System.out.println("ERROR: " + e.getMessage());
                    return false;
                }

            }
        } else {
            System.out.println(EquipoBD.class.getName() + " Sin conexión a BD en crearEquipo()");
        }
        return false;
    }

    public boolean confeccionarEquipo(Jugador jugador, String equipo, boolean equipoCreado) {

        connection = ConexionBD.conectar();

        if (connection != null) {
            if (!equipoCreado) {
                //crear equipo en tabla Equipo (insert)
                try {
                    PreparedStatement st = connection.prepareStatement("INSERT INTO EQUIPO VALUES (?)");

                    st.setString(1, equipo);

                    st.executeUpdate();

                } catch (SQLException e) {
                    System.out.println("ERROR: " + e.getMessage());
                    return false;
                }
            }
            //llamada a metodo "ASIGNAR_EQUIPO en paquete "CRUD_JUGADOR"
            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_JUGADOR.ASIGNAR_EQUIPO(?,?) }";
                CallableStatement csa1 = connection.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_NICK", jugador.getNickname());
                csa1.setString("P_EQU", equipo);

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento CRUD_JUGADOR.ASIGNAR_EQUIPO ejecutado");
                csa1.close();
                ConexionBD.desconectar(connection);
                return true;

            } catch (SQLException e) { // Controlamos los errores que queremos sacar
                System.out.println("ERROR: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println(EquipoBD.class.getName() + " Sin conexión a BD en confeccionarEquipo()");
        }
        return false;
    }

    public void rollBack(Jugador jugador, String equipo) {

        connection = ConexionBD.conectar();

        //ROLLBACK DEL INSERT EN EQUIPO

        if (connection != null) {

            //rollback creación equipo
            //crear equipo en tabla Equipo (insert)
            try {
                PreparedStatement st = connection.prepareStatement("DELETE FROM EQUIPO WHERE NOMBRE = (?)");

                st.setString(1, equipo);

                st.executeUpdate();

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
            //llamada a metodo "ACTUALIZAR_JUGADOR en paquete "CRUD_JUGADOR"
            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_JUGADOR.ASIGNAR_EQUIPO(?,?) }";
                CallableStatement csa1 = connection.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_NICK", jugador.getNickname());
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
            System.out.println(EquipoBD.class.getName() + " Sin conexión con BD en rollback()");
        }
    }

    public Dueño buscarDueñoEquipo(String equipo) {

        Dueño dueño = null;
        connection = ConexionBD.conectar();
        //select

        if (connection != null) {
            // Consulta simple
            try {
                PreparedStatement stmt = connection.prepareStatement("SELECT USUARIO, NOMBRE, APELLIDO FROM DUEÑO WHERE EQUIPO = (?)");
                stmt.setString(1, equipo);

                ResultSet rset = stmt.executeQuery();

                while (rset.next()) {
                    String usuario = rset.getString("USUARIO");
                    String nombre = rset.getString("NOMBRE");
                    String apellido = rset.getString("APELLIDO");

                    dueño = new Dueño(usuario, nombre, apellido);
                    System.out.println(nombre + " " + apellido + " añadido a lista dueños disponibles");
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

        return dueño;
    }

    public List<Jugador> buscarJugadoresEquipo(String equipo) {
        List<Jugador> jugsEq = new ArrayList<>();

        connection = ConexionBD.conectar();

        if (connection != null) {
            try {
                PreparedStatement stmt = connection.prepareStatement("SELECT NICKNAME, SALARIO FROM JUGADOR WHERE EQUIPO = (?)");
                stmt.setString(1, equipo);

                ResultSet rset = stmt.executeQuery();

                while (rset.next()) {
                    String nick = rset.getString("NICKNAME");
                    double salario = rset.getDouble("SALARIO");

                    jugsEq.add(new Jugador(nick, salario));
                    System.out.println(nick + " añadido a lista jugadores equipo");
                }

                rset.close();
                stmt.close();
                ConexionBD.desconectar(connection);

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } else {
            System.out.println(EquipoBD.class.getName() + " Sin conexión con BD en buscarJugadoresEquipo()");
        }

        return jugsEq;
    }

    public boolean eliminarEquipo(String equipo, JLabel textoInfo) {
        connection = ConexionBD.conectar();

        if (connection != null) {

            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_EQUIPO.BORRAR_EQUIPO(?) }";
                CallableStatement csa1 = connection.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_NOMBRE", equipo);

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento BORRAR_EQUIPO ejecutado");

                csa1.close();
                ConexionBD.desconectar(connection);

                return true;

            } catch (SQLException e) {
                if (e.getErrorCode() == 20000) {
                    textoInfo.setText("Error: Equipo no existe");
                    return false;
                }
                textoInfo.setText("Error: Equipo NO eliminado");
                System.out.println(e.getMessage());
                return false;
            }

        } else {
            System.out.println(JugadorBD.class.getName() + " sin conexión a BD en .eliminarEquipo()");
        }
        return false;
    }
}
