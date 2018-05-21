package com.redv.com.ESports;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CalendarioBD {

    private Connection conexion;

    public ArrayList<Equipo> cargarEquiposTemporada() {
        conexion = ConexionBD.conectar();

        ConexionBD.desconectar(conexion);
        return new ArrayList<>();
    }

    public ArrayList cargarTemporada() {
        ArrayList temporada = new ArrayList();

        conexion = ConexionBD.conectar();

        ConexionBD.desconectar(conexion);

        return temporada;
    }

    public boolean almacenarTemporada(List<Calendario> temporada) {
        conexion = ConexionBD.conectar();

        ConexionBD.desconectar(conexion);
        return false;
    }

    public boolean almacenarResultados(Calendario partido) {
        conexion = ConexionBD.conectar();

        ConexionBD.desconectar(conexion);
        return false;
    }

    public boolean almacenarPuntuacion(Equipo equipo) {
        conexion = ConexionBD.conectar();

        ConexionBD.desconectar(conexion);
        return false;
    }
}
