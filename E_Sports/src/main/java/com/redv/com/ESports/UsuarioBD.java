package com.redv.com.ESports;

import java.sql.*;

public class UsuarioBD {

    public UsuarioBD() {
    }

    private Connection conexion = null;
    private String rol;

    public boolean comprobar_credenciales(String usuario, String pass) {

        conexion = ConexionBD.conectar();

        String contraseña = null;

        if (conexion != null) {
            try {
                Statement st = conexion.createStatement();
                ResultSet rs = st.executeQuery("SELECT PASS, ROL " +
                        "FROM USUARIO" +
                        "WHERE NOMBRE = " + usuario);

                while (rs.next()) {
                    contraseña = rs.getString(1);
                    rol = rs.getString(2);  //número columna correcto según query???
                }

                if (pass.equalsIgnoreCase(contraseña)) {
                    st.close();
                    rs.close();
                    ConexionBD.desconectar(conexion);
                    return true;
                }

            } catch (SQLException e) {
                System.out.println(UsuarioBD.class.getName() + " .comprobarCredenciales() " + e);
            }

        } else {
            System.out.println(UsuarioBD.class.getName() + " sin conexión a BD en .comprobarCredenciales()");
        }
        return false;
    }

    public boolean resgistrarUsuario(String usuario, String pass) {

        conexion = ConexionBD.conectar();

        if (conexion != null) {

            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_USUARIO.INSERTAR_USUARIO(?,?,?) }";
                CallableStatement csa1 = conexion.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_USUARIO", usuario);
                csa1.setString("P_PASS", pass);
                csa1.setInt("P_ROL", 1);

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento INSERTAR_USUARIO ejecutado");

                ConexionBD.desconectar(conexion);

                return true;

            } catch (SQLException e) {
                //gestion RAISE_APLICATION_ERROR
                System.out.println(UsuarioBD.class.getName() + " .registrarUsuario() " + e);
            }

        } else {
            System.out.println(UsuarioBD.class.getName() + " sin conexión a BD en .registrarUsuario()");
        }
        return false;
    }

    public String getRol() {
        return rol;
    }
}
