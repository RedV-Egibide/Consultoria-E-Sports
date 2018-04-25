package com.redv.com.imejpul;

import java.util.List;
import java.util.Objects;

public class Calendario {

    private int jornada;
    private List<Equipo> equipos;
    private String fecha;
    private Equipo ganador;

    public Calendario() {
    }

    public Calendario(int jornada, List<Equipo> equipos, String fecha, Equipo ganador) {
        this.jornada = jornada;
        this.equipos = equipos;
        this.fecha = fecha;
        this.ganador = ganador;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Equipo getGanador() {
        return ganador;
    }

    public void setGanador(Equipo ganador) {
        this.ganador = ganador;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    @Override
    public String toString() {
        return "Calendario{" +
                "jornada=" + jornada +
                ", equipos=" + equipos +
                ", fecha='" + fecha + '\'' +
                ", ganador=" + ganador +
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
}
