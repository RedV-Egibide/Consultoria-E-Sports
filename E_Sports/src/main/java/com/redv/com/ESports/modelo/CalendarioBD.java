package com.redv.com.ESports.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalendarioBD {

    private Connection conexion;

    public ArrayList<Equipo> cargarEquiposTemporada() {
        ArrayList<Equipo> equipos = new ArrayList<>();

        conexion = ConexionBD.conectar();

        if (conexion != null) {
            // Consulta simple
            try {
                Statement stmt = conexion.createStatement();

                ResultSet rset = stmt.executeQuery("SELECT NOMBRE, PUNTUACION FROM EQUIPO");

                while (rset.next()) {
                    String nombre = rset.getString("NOMBRE");
                    int puntuacion = rset.getInt("PUNTUACION");
                    System.out.println(nombre + " " + puntuacion);

                    Equipo equipo = new Equipo(nombre, puntuacion);

                    equipos.add(equipo);
                }

                rset.close();
                stmt.close();
                ConexionBD.desconectar(conexion);

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } else {
            System.out.println(CalendarioBD.class.getName() + " Sin conexión con BD en cargarEquiposTemporada()");
        }
        return equipos;
    }

    public ArrayList cargarTemporada() {
        ArrayList temporada = new ArrayList();

        conexion = ConexionBD.conectar();

        //select

        if (conexion != null) {
            // Consulta simple
            try {
                Statement stmt = conexion.createStatement();

                ResultSet rset = stmt.executeQuery("SELECT JORNADA, FECHA, NOM_EQ1, NOM_EQ2, RESULTADO FROM CALENDARIO");

                while (rset.next()) {
                    int jornada = rset.getInt("JORNADA");
                    java.sql.Date fecha = rset.getDate("FECHA");
                    String eq1 = rset.getString("NOM_EQ1");
                    String eq2 = rset.getString("NOM_EQ2");
                    int res = rset.getInt("RESULTADO");

                    Equipo equipo1 = new Equipo(eq1);
                    Equipo equipo2 = new Equipo(eq2);

                    Calendario calendario = new Calendario(jornada, fecha, equipo1, equipo2, res);
                    temporada.add(calendario);
                }

                rset.close();
                stmt.close();
                ConexionBD.desconectar(conexion);

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } else {
            System.out.println(CalendarioBD.class.getName() + " Sin conexión con BD en cargarTemporada()");
        }
        return temporada;
    }

    public boolean almacenarTemporada(List<Calendario> temporada) {
        conexion = ConexionBD.conectar();

        //insert
        if (conexion != null) {

            for (Calendario c : temporada) {

                System.out.println(c);

                try {
                    PreparedStatement st = conexion.prepareStatement("INSERT INTO CALENDARIO VALUES (?,?,?,?,?)");

                    st.setInt(1, c.getJornada());
                    st.setDate(2, c.getFecha());
                    st.setString(3, c.getEquipo1().getNombre_equipo());
                    st.setString(4, c.getEquipo2().getNombre_equipo());
                    st.setInt(5, -1);

                    st.executeUpdate();

                } catch (SQLException e) {
                    System.out.println("ERROR: " + e.getMessage());
                    return false;
                }
            }

        } else {
            System.out.println(CalendarioBD.class.getName() + " Sin conexión con BD en almacenarTemporada()");
        }

        ConexionBD.desconectar(conexion);
        return false;
    }

    public boolean almacenarResultados(Calendario partido) {
        conexion = ConexionBD.conectar();
        //update
        if (conexion != null) {
            //actualizar equipo en tabla Dueño(update)
            try {
                PreparedStatement st = conexion.prepareStatement("UPDATE CALENDARIO SET RESULTADO = (?) WHERE JORNADA = (?)");
                st.setInt(1, partido.getResultado());
                st.setInt(2, partido.getJornada());
                st.executeUpdate();

                st.close();
                ConexionBD.desconectar(conexion);
                return true;

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println(CalendarioBD.class.getName() + " Sin conexión a BD en almacenarResultados()");
        }
        return false;
    }

    public boolean almacenarPuntuacion(Equipo equipo) {
        conexion = ConexionBD.conectar();

        //update
        if (conexion != null) {
            //actualizar equipo en tabla Dueño(update)
            try {
                PreparedStatement st = conexion.prepareStatement("UPDATE EQUIPO SET PUNTUACION = (?) WHERE NOMBRE = (?)");
                st.setInt(1, equipo.getPuntuación());
                st.setString(2, equipo.getNombre_equipo());
                st.executeUpdate();

                st.close();
                ConexionBD.desconectar(conexion);
                return true;

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println(CalendarioBD.class.getName() + " Sin conexión a BD en almacenarPuntuacion()");
        }
        return false;
    }
}
