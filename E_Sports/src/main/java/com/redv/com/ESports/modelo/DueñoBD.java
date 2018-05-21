package com.redv.com.ESports.modelo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DueñoBD {

    private Connection conexion = null;

    private String equipo = null;

    /**
     * Llama a BD y toma todos los dueños que encuentra sin equipo asignado
     *
     * @return devuelve un objeto Dueño con los parámetros USUARIO, NOMBRE, APELLIDO cargados
     */
    public List<Dueño> cargarDueñosDisponibles() {

        List<Dueño> dueñosDisp = new ArrayList<>();

        conexion = ConexionBD.conectar();

        if (conexion != null) {
            // Consulta simple
            try {
                Statement stmt = conexion.createStatement();

                ResultSet rset = stmt.executeQuery("SELECT USUARIO, NOMBRE, APELLIDO FROM DUEÑO WHERE EQUIPO IS NULL");

                while (rset.next()) {
                    String usuario = rset.getString("USUARIO");
                    String nombre = rset.getString("NOMBRE");
                    String apellido = rset.getString("APELLIDO");

                    Dueño dueño = new Dueño(usuario, nombre, apellido);
                    dueñosDisp.add(dueño);
                    System.out.println(nombre + " añadido a lista disponibles");
                }

                rset.close();
                stmt.close();
                ConexionBD.desconectar(conexion);

            } catch (SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } else {
            System.out.println(DueñoBD.class.getName() + " Sin conexión a BD en cargarDueñosDisponibles()");
        }
        return dueñosDisp;
    }

    /**
     * Llama a BD e inserta un Dueño en la respectiva tabla
     *
     * @param usuario   nombre de usuario
     * @param nombre    nombre del suejeto
     * @param apellido  apellido del sujeto
     * @param textoInfo recibe un Jlabel para informar de cualquier error producido en el proceso
     * @return devuelve true si no ha habido problemas la ejecutar la sentencia
     */
    public boolean crearDueño(String usuario, String nombre, String apellido, JLabel textoInfo) {
        conexion = ConexionBD.conectar();

        if (conexion != null) {

            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_DUEÑO.INSERTAR_DUEÑO(?,?,?) }";
                CallableStatement csa1 = conexion.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_USUARIO", usuario);
                csa1.setString("P_NOM", nombre);
                csa1.setString("P_APE", apellido);

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento INSERTAR_DUEÑO ejecutado");

                csa1.close();
                ConexionBD.desconectar(conexion);

                return true;

            } catch (SQLException e) {
                //gestion RAISE_APLICATION_ERROR
                if (e.getErrorCode() == 20000) {
                    System.out.println(DueñoBD.class.getName() + " .crearDueño() " + e.getMessage());
                    textoInfo.setText("Error: Nuevo Dueño no resgistrado como usuario");
                } else if (e.getErrorCode() == 20001) {
                    System.out.println(DueñoBD.class.getName() + " .crearDueño() " + e.getMessage());
                    textoInfo.setText("Error: Dueño ya existe ");
                }

            }

        } else {
            System.out.println(DueñoBD.class.getName() + " sin conexión a BD en .crearDueño()");
        }
        return false;
    }

    /**
     * Busca en BD un Dueño que se corresponda con el nombre de usuario pasado
     *
     * @param usuario   nombre de usuario que recibe
     * @param textoInfo Jlabel que recibe para informar en ventana de cualquier fallo o ejecución satisfactoria
     * @return devuelve un objeto Dueño
     */
    public Dueño buscarDueño(String usuario, JLabel textoInfo) {
        conexion = ConexionBD.conectar();

        String nombre = null;
        String apellido = null;

        if (conexion != null) {
            try {
                PreparedStatement st = conexion.prepareStatement("SELECT NOMBRE, APELLIDO, EQUIPO FROM DUEÑO WHERE USUARIO = (?)");

                st.setString(1, usuario);

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    nombre = rs.getString(1);
                    apellido = rs.getString(2);
                    equipo = rs.getString(3);

                }
                if (nombre == null && apellido == null) {
                    textoInfo.setText("Usuario no existe");
                }
                rs.close();
                st.close();
                ConexionBD.desconectar(conexion);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(DueñoBD.class.getName() + " sin conexión a BD en buscarDueño()");
        }
        if (!(equipo == null)) {
            textoInfo.setText("Equipo: " + equipo);
        } else {
            textoInfo.setText("Equipo: N/D");
        }
        return new Dueño(usuario, nombre, apellido);
    }

    /**
     * Elimina de BD un Dueño que coincida con el nombre de usuario pasado
     * y puede recibir el nombre de usuario de un dueño existente en BD para hacerse cargo del equipo del dueño a eliminar
     * si lo tiene.
     *
     * @param nombre      dueño a eliminar
     * @param nuevo_Dueño dueño que se haría cargo de equipo
     * @return devuelve true si se ha ejecutado la sentencia correctamente
     */
    public boolean eliminarDueño(String nombre, String nuevo_Dueño) {
        conexion = ConexionBD.conectar();

        if (conexion != null) {

            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_DUEÑO.BORRAR_DUEÑO(?,?) }";
                CallableStatement csa1 = conexion.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_USUARIO", nombre);
                csa1.setString("P_NUEVO_DUEÑO", nuevo_Dueño);

                // Ejecutamos la llamada
                csa1.execute();

                System.out.println("INFO: Procedimiento BORRAR_DUEÑO ejecutado");

                csa1.close();
                ConexionBD.desconectar(conexion);

                return true;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }

        } else {
            System.out.println(DueñoBD.class.getName() + " sin conexión a BD en .eliminarDueño()");
        }
        return false;
    }

    /**
     * Actualiza Dueño en BD.
     *
     * @param usuario  nombre de usuario de dueño a actualizar
     * @param nombre   nombre del sujeto
     * @param apellido apellido del suejto
     * @return devuelve true si ha realizado correctamente el proceso
     */
    public boolean actualizarDueño(String usuario, String nombre, String apellido) {
        conexion = ConexionBD.conectar();

        if (conexion != null) {

            try {
                //Creamos el statement
                String sqla1 = "{ call CRUD_DUEÑO.ACTUALIZAR_DUEÑO(?,?,?) }";
                CallableStatement csa1 = conexion.prepareCall(sqla1);

                // Cargamos los parametros de entrada IN
                csa1.setString("P_USUARIO", usuario);
                csa1.setString("P_NOM", nombre);
                csa1.setString("P_APE", apellido);

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

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
}
