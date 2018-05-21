package com.redv.com.ESports;

import com.redv.com.ESports.modelo.Calendario;
import com.redv.com.ESports.modelo.Equipo;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TablaClasificacionUsuario extends AbstractTableModel {
    private String[] columnas = {"Equipo", "Posición"};
    private ArrayList<Equipo> equiposPuntuados;
    private ArrayList<Equipo> equiposEnOrden;
    private int puesto = 0;
    private int puntuacion = 0;

    public TablaClasificacionUsuario(ArrayList<Calendario> partidosTemporada, ArrayList<Equipo> equiposParticipantes) {
        this.partidosTemporada = partidosTemporada;
        this.equiposParticipantes = equiposParticipantes;
    }

    //BASE DE DATOS
    ArrayList<Calendario> partidosTemporada; //Este ArrayList debe de contener el Array de partidos de la última temporada. (El generado automáticamente)
    ArrayList<Equipo> equiposParticipantes; //Aqui el array con todos los equipos que participarán en la temporada.
    //BASE DE DATOS

    @Override
    public int getRowCount() {
        return equiposPuntuados.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        equiposPuntuados = Nucleo.PuntuarEquipos(partidosTemporada, equiposParticipantes);//Aqui los dos arrays de la base de datos; Los equipos que juegan y la temporada
        Equipo equipo = equiposEnOrden.get(rowIndex);

        //TODOS ESTOS FORs SON PARA QUE LOS DATOS SE MUESTREN ORDENADOS EN BASE A SU PUNTUACIÓN.
        //SIMPLEMENTE BUSCA CUAL ES LA PUNTUACION MÁS ALTA Y LA GUARDA EN OTRO ARRAY AL MISMO TIEMPO QUE BORRA ESA INFO DEL PRIMER ARRAY.
        //DE ESTA FORMA CADA VEZ QUEDAN MENOS EQUIPOS Y LOS VA REORDENANDO EN UN NUEVO ARRAY.
        for (int i = 0; i < equiposPuntuados.size(); i++) {
            if (puntuacion < equiposPuntuados.get(i).getPuntuación()) {
                puntuacion = equiposPuntuados.get(i).getPuntuación();
            }

            for (int j = 0; j < equiposPuntuados.size(); j++) {
                if (puntuacion == equiposPuntuados.get(i).getPuntuación()) {
                    equiposEnOrden.add(equiposPuntuados.get(i));
                    equiposPuntuados.remove(i);
                }
            }

            puntuacion = 0;

        }

        //ESTO ES LO QUE LO MUESTRA EN PANTALLA.
        switch (columnIndex) {
            case 0:
                return equipo.getNombre_equipo();
            case 1:
                return equipo.getPuntuación();
        }

        puesto++;

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}


