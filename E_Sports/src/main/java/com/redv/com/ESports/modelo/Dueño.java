package com.redv.com.ESports.modelo;

import java.util.Objects;

public class Dueño extends Usuario {

    private String nombre_dueño;
    private String apellido_dueño;
    private Equipo equipoDueño;

    public Dueño() {
    }

    public Dueño(String nombre_dueño, String apellido_dueño) {
        this.nombre_dueño = nombre_dueño;
        this.apellido_dueño = apellido_dueño;
    }

    public Dueño(String usuario, String contraseña, Rol rol, String nombre_dueño, String apellido_dueño, Equipo equipoDueño) {
        super(usuario, contraseña, rol);
        this.nombre_dueño = nombre_dueño;
        this.apellido_dueño = apellido_dueño;
        this.equipoDueño = equipoDueño;
    }

    public Dueño(String usuario, String contraseña, String nombre_dueño, String apellido_dueño) {
        super(usuario, contraseña);
        this.nombre_dueño = nombre_dueño;
        this.apellido_dueño = apellido_dueño;
    }

    public Dueño(String usuario, String nombre_dueño, String apellido_dueño) {
        super(usuario);
        this.nombre_dueño = nombre_dueño;
        this.apellido_dueño = apellido_dueño;
    }

    public String getNombre_dueño() {
        return nombre_dueño;
    }

    public String getApellido_dueño() {
        return apellido_dueño;
    }

    @Override
    public String toString() {
        return nombre_dueño + " " + apellido_dueño;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dueño dueño = (Dueño) o;
        return Objects.equals(nombre_dueño, dueño.nombre_dueño) &&
                Objects.equals(apellido_dueño, dueño.apellido_dueño) &&
                Objects.equals(equipoDueño, dueño.equipoDueño);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), nombre_dueño, apellido_dueño, equipoDueño);
    }
}
