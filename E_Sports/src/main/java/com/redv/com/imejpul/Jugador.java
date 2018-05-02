package com.redv.com.imejpul;

import java.util.Objects;

public class Jugador {

    private String nickname;
    private String nombre_jugador;
    private String apellido_jugador;
    private double salario;
    private Equipo equipoJugador;

    public Jugador() {
    }

    public Jugador(String nickname, String nombre_jugador, String apellido_jugador, double salario) {
        this.nickname = nickname;
        this.nombre_jugador = nombre_jugador;
        this.apellido_jugador = apellido_jugador;
        this.salario = salario;
    }

    public Jugador(String nickname, String nombre_jugador, String apellido_jugador, double salario, Equipo equipoJugador) {
        this.nickname = nickname;
        this.nombre_jugador = nombre_jugador;
        this.apellido_jugador = apellido_jugador;
        this.salario = salario;
        this.equipoJugador = equipoJugador;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNombre_jugador() {
        return nombre_jugador;
    }

    public void setNombre_jugador(String nombre_jugador) {
        this.nombre_jugador = nombre_jugador;
    }

    public String getApellido_jugador() {
        return apellido_jugador;
    }

    public void setApellido_jugador(String apellido_jugador) {
        this.apellido_jugador = apellido_jugador;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Equipo getEquipoJugador() {
        return equipoJugador;
    }

    public void setEquipoJugador(Equipo equipoJugador) {
        this.equipoJugador = equipoJugador;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nickname='" + nickname + '\'' +
                ", nombre_jugador='" + nombre_jugador + '\'' +
                ", apellido_jugador='" + apellido_jugador + '\'' +
                ", salario=" + salario +
                ", equipoJugador=" + equipoJugador +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(nickname, jugador.nickname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nickname);
    }
}
