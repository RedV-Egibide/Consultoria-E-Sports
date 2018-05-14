package com.redv.com.ESports;

import java.util.Objects;

public class Partido {

    /**
     * ESTA CLASE SIMPLEMENTE ALMACENA A LOS DOS CONTRINCANTES DE UN PARTIDO.
     * MUCHOS PARTIDOS FORMARÁN CALENDARIO
     */

    private int jornada;
    private Equipo equipoA;
    private Equipo equipoB;
    private int resultado;

    public Partido(int jornada, Equipo equipoA, Equipo equipoB, int resultado) {
        this.jornada = jornada;
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.resultado = resultado;
    }

    public Partido() {
    }

    public Partido(Equipo equipo, Equipo equipo1) {
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public Equipo getEquipoA() {
        return equipoA;
    }

    public void setEquipoA(Equipo equipoA) {
        this.equipoA = equipoA;
    }

    public Equipo getEquipoB() {
        return equipoB;
    }

    public void setEquipoB(Equipo equipoB) {
        this.equipoB = equipoB;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "jornada=" + jornada +
                ", equipoA=" + equipoA +
                ", equipoB=" + equipoB +
                ", resultado=" + resultado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partido partido = (Partido) o;
        return jornada == partido.jornada;
    }

    @Override
    public int hashCode() {

        return Objects.hash(jornada);
    }
}
