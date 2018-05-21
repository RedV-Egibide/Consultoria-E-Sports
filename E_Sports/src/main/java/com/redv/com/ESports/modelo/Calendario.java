package com.redv.com.ESports.modelo;

import java.util.Objects;

public class Calendario {

    private int jornada;
    private java.sql.Date fecha;
    private Equipo equipo1;
    private Equipo equipo2;
    private int resultado;//ESTE RESULTADO LO DEJASTE UNIDO A LA CLASE "RESULTADO", LO CAMBIÉ POR LAS FUNCIONES DEL NÚCLEO.

    public Calendario() {
    }

    public Calendario(Equipo equipo1, Equipo equipo2) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
    }

    public Calendario(int jornada, java.sql.Date fecha, Equipo equipo1, Equipo equipo2, int resultado) {
        this.jornada = jornada;
        this.fecha = fecha;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Calendario{" +
                "jornada=" + jornada +
                ", fecha=" + fecha +
                ", equipo1=" + equipo1 +
                ", equipo2=" + equipo2 +
                ", resultado=" + resultado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendario that = (Calendario) o;
        return jornada == that.jornada;
    }

    @Override
    public int hashCode() {

        return Objects.hash(jornada);
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }
}
