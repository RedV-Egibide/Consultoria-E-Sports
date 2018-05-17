package com.redv.com.ESports;

import java.util.Objects;

public class Dueño extends Usuario {

    private String nombre_dueño;
    private String apellido_dueño;
    private Equipo equipoDueño;

    public Dueño() {
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

    public String getNombre_dueño() {
        return nombre_dueño;
    }

    public void setNombre_dueño(String nombre_dueño) {
        this.nombre_dueño = nombre_dueño;
    }

    public String getApellido_dueño() {
        return apellido_dueño;
    }

    public void setApellido_dueño(String apellido_dueño) {
        this.apellido_dueño = apellido_dueño;
    }

    public Equipo getEquipoDueño() {
        return equipoDueño;
    }

    public void setEquipoDueño(Equipo equipoDueño) {
        this.equipoDueño = equipoDueño;
    }

    @Override
    public String toString() {
        return "Dueño{" +
                "nombre_dueño='" + nombre_dueño + '\'' +
                ", apellido_dueño='" + apellido_dueño + '\'' +
                ", equipoDueño=" + equipoDueño +
                '}';
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
