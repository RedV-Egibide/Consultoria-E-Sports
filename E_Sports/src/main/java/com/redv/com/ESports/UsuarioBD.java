package com.redv.com.ESports;

import java.sql.*;

public class UsuarioBD {

    public UsuarioBD() {
    }

    private Connection conexion = null;
    private VentanaLogin ventanaLogin = null;
    private String rol;

    /**
     * Convierte el número de rol que viene de BD en un String con el correspondiente nombre.
     *
     * @param rolNumber parámetro a comparar para conversión
     * @return rol  variable clase para almacenar el tipo de usuario
     * @author imejpul
     */
    public String convertirRolNumber(int rolNumber) {

        switch (rolNumber) {
            case 1:
                rol = Rol.USUARIO.name();
            case 2:
                rol = Rol.ADMINISTRADOR.name();
                break;
            case 3:
                rol = Rol.DUEÑO.name();
                break;
        }

        return rol;
    }

    /**
     * Comprueba que el nombre de usuario y contraseña introducido por el usuario es correcto
     * y llama a la función convertirRolNumber() para determinar el tipo de usuario según el número de rol devuelto
     * por BD.
     *
     * @param usuario      nombre de usuario introducido por el usuario..
     * @param pass         contraseña introducida por el usuario.
     * @param ventanaLogin objeto que direcciona a la ventana en la que se está intentando hacer login.
     * @return
     * @author imejpul
     */
    public boolean comprobar_credenciales(String usuario, String pass, VentanaLogin ventanaLogin) {

        conexion = ConexionBD.conectar();

        String contraseña;

        if (conexion != null) {
            try {
                PreparedStatement st = conexion.prepareStatement("SELECT PASS, ROL FROM USUARIO WHERE USUARIO = (?)");

                st.setString(1, usuario);

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    contraseña = rs.getString(1);

                    if (pass.equalsIgnoreCase(contraseña)) {
                        convertirRolNumber(rs.getInt(2));
                        rs.close();
                        st.close();
                        ConexionBD.desconectar(conexion);
                        return true;
                    }
                    System.out.println("Contraseña erronea");
                    ventanaLogin.setMensaje(true);
                    rs.close();
                    st.close();
                    ConexionBD.desconectar(conexion);

                    return false;
                }
                System.out.println("Usuario no existe");
                ventanaLogin.setMensaje(true);
                rs.close();
                st.close();
                ConexionBD.desconectar(conexion);

                return false;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * @param usuario nombre de usuario que se quiere registrar.
     * @param pass    contraseña que se quiere establecer para el usuario a registrar.
     * @return devuelve true si el proceso de registro ha sido satisfactorio.
     */
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
