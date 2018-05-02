package com.redv.com;

public class Partido {

    /**
     * ESTA CLASE SIMPLEMENTE ALMACENA A LOS DOS CONTRINCANTES DE UN PARTIDO.
     * MUCHOS PARTIDOS FORMARÁN CALENDARIO
     */

    Equipo equipoA;
    Equipo equipoB;

    public Partido(Equipo equipoA, Equipo equipoB) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
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

    @Override
    public String toString() {
        return "equipo A = " + equipoA +
                ", equipo B = " + equipoB +
                "} \n";
    }
}
