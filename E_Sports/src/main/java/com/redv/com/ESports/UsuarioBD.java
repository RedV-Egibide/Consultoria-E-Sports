package com.redv.com.ESports;

import java.sql.*;

public class UsuarioBD {

    public UsuarioBD() {
    }

    private Connection conexion = null;
    private VentanaLogin ventanaLogin = null;
    private String rol;

    public boolean comprobar_credenciales(String usuario, String pass) {

        conexion = ConexionBD.conectar();
        ventanaLogin = new VentanaLogin();

        String contraseña = null;

        if (pass != null) {
            if (conexion != null) {
                try {
                    Statement st = conexion.createStatement();

                    System.out.println("SELECT PASS, ROL " +
                            "FROM USUARIO " +
                            "WHERE USUARIO = '" + usuario + "'");

                    ResultSet rs = st.executeQuery("SELECT PASS, ROL " +
                            "FROM USUARIO" +
                            "WHERE USUARIO = '" + usuario + "'");

                    while (rs.next()) {
                        contraseña = rs.getString(1);
                        rol = rs.getString(2);
                    }

                    if (pass.equalsIgnoreCase(contraseña)) {
                        st.close();
                        rs.close();
                        ConexionBD.desconectar(conexion);
                        return true;
                    }

                } catch (SQLException e) {
                    if (e.getErrorCode() == 01403) {
                        System.out.println(UsuarioBD.class.getName() + " .comprobarCredenciales() " + e);
                        ventanaLogin.setMensaje(true);
                    }
                    System.out.println(UsuarioBD.class.getName() + " .comprobarCredenciales() " + e);
                    return false;
                }

            } else {
                System.out.println(UsuarioBD.class.getName() + " sin conexión a BD en .comprobarCredenciales()");
            }
        } else {
            ventanaLogin.setMensaje(true);
            return false;
        }
        ventanaLogin.setMensaje(true);
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
                if (e.getErrorCode() == 20000) {
                    System.out.println(UsuarioBD.class.getName() + " .registrarUsuario() " + e.getMessage());
                } else if (e.getErrorCode() == 20001) {
                    System.out.println(UsuarioBD.class.getName() + " .registrarUsuario() " + e.getMessage());
                }

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
