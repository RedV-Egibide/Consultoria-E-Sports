package com.redv.com.ESports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioBD {

    public UsuarioBD() {
    }

    private ConexionBD conexionBD = new ConexionBD();
    private Connection conexion = conexionBD.conectar();

    public boolean comprobar_credenciales(String usuario, String pass) {

        String contraseña = null;

        if (conexion != null) {
            try {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT PASS " +
                        "FROM USUARIO" +
                        "WHERE NOMBRE = " + usuario);

                while (rs.next()) {
                    contraseña = rs.getString(1);
                }

                if (pass != null && pass.equalsIgnoreCase(contraseña)) {
                    return true;
                }
                st.close();
                rs.close();
                conexionBD.desconectar(conexion);

            } catch (SQLException e) {
                System.out.println(UsuarioBD.class.getName() + " .comprobarCredenciales() " + e);
            }

        }
        return false;
    }

    public String obtener_rol() {
        return " ";
    }
}
