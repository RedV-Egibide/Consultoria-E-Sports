package com.redv.com.ESports;

import java.sql.Connection;

public class DueñoBD {

    private Connection conexion = null;

    public boolean crearDueño() {
        conexion = ConexionBD.conectar();
        return false;
    }

    public Dueño buscarDueño() {
        conexion = ConexionBD.conectar();
        return new Dueño();
    }

    public boolean eliminarDueño() {
        conexion = ConexionBD.conectar();
        return false;
    }

    public boolean actualizarDueño() {
        conexion = ConexionBD.conectar();
        return false;
    }
}
