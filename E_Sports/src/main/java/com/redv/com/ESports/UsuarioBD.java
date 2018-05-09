package com.redv.com.ESports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioBD {

    public UsuarioBD(ConexionBD conexionBD, Connection conexion) {
        this.conexionBD = conexionBD;
        this.conexion = conexion;
    }

    private ConexionBD conexionBD = new ConexionBD();
    private Connection conexion = conexionBD.conectar();

    public void comprobar_credenciales(String usuario, String pass) {
        if (conexion != null) {
            try {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT PASS " +
                        "FROM USUARIO" +
                        "WHERE NOMBRE = " + usuario);

                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
                st.close();
                rs.close();
                conexionBD.desconectar(conexion);

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }
}
