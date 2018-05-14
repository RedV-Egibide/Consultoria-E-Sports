package com.redv.com.ESports;

import java.sql.*;

public class UsuarioBD {

    public UsuarioBD() {
    }

    private ConexionBD conexionBD = new ConexionBD();
    private Connection conexion = null;
    private String rol;

    public boolean comprobar_credenciales(String usuario, String pass) {

        String contraseña = null;
        conexion = conexionBD.conectar();

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
                    conexionBD.desconectar(conexion);
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

        if (conexion != null) {

            try {
                //Creamos el statement
                String sqla1 = "{ call INSERTAR_USUARIO(?,?) }";
                CallableStatement csa1 = conexion.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString(1, usuario);
                csa1.setString(2, pass);
                csa1.setInt(3, Rol.USUARIO.ordinal());

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento INSERTAR_USUARIO ejecutado");

                conexionBD.desconectar(conexion);

                // TODO: Pregunta

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
