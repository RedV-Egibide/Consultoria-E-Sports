package com.redv.com.ESports.modelo;

import java.util.List;
import java.util.Objects;

public class Equipo {

    private String nombre_equipo;
    private List<Jugador> jugadores;
    private Dueño dueñoEquipo;
    private List<Calendario> partidos;
    private int puntuación;//ESTA VARIABLE ALMACENARÁ LA POSICIÓN DEL EQUIPO EN LAS TABLAS DE CLASIFICACIÓN

    public Equipo() {
    }

    public Equipo(String nombre_equipo, int puntuación) {
        this.nombre_equipo = nombre_equipo;
        this.puntuación = puntuación;
    }

    public Equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }

    public Equipo(String nombre_equipo, Dueño dueñoEquipo) {
        this.nombre_equipo = nombre_equipo;
        this.dueñoEquipo = dueñoEquipo;
    }

    public Equipo(String nombre_equipo, List<Jugador> jugadores, Dueño dueñoEquipo) {
        this.nombre_equipo = nombre_equipo;
        this.jugadores = jugadores;
        this.dueñoEquipo = dueñoEquipo;
    }

    public int getPuntuación() {
        return puntuación;
    }

    public void setPuntuación(int puntuación) {
        this.puntuación = puntuación;
    }

    public String getNombre_equipo() {
        return nombre_equipo;
    }

    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }

    public Dueño getDueñoEquipo() {
        return dueñoEquipo;
    }

    public void setDueñoEquipo(Dueño dueñoEquipo) {
        this.dueñoEquipo = dueñoEquipo;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public List<Calendario> getPartidos() {
        return partidos;
    }

    @Override
    public String toString() {
        return nombre_equipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return Objects.equals(nombre_equipo, equipo.nombre_equipo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nombre_equipo);
    }
}
