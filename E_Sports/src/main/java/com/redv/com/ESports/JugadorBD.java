package com.redv.com.ESports;

import java.sql.Connection;

public class JugadorBD {

    private Connection conexion = null;

    public boolean registrarJugador() {
        conexion = ConexionBD.conectar();
        return false;
    }

    public Jugador buscarJugador() {
        conexion = ConexionBD.conectar();
        return new Jugador();
    }

    public boolean eliminarJugador() {
        conexion = ConexionBD.conectar();
        return false;
    }

    public boolean actualizarJugador() {
        conexion = ConexionBD.conectar();
        return false;
    }
}
