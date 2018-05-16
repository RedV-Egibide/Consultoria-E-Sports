package com.redv.com.ESports;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TablaClasificacionUsuario extends AbstractTableModel {
    private String[] columnas = {"Equipo", "Posición"};
    private ArrayList<Equipo> equiposPuntuados;
    private int puesto=0;

    //BASE DE DATOS
    ArrayList<Partido> partidosTemporada; //Este ArrayList debe de contener el Array de partidos de la última temporada. (El generado automáticamente)
    ArrayList<Equipo> equiposParticipantes; //Aqui el array con todos los equipos que participarán en la temporada.
    //BASE DE DATOS

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       equiposPuntuados = Nucleo.PuntuarEquipos(partidosTemporada, equiposParticipantes);//Aqui los dos arrays de la base de datos; Los equipos que juegan y la temporada
        Equipo equipo = equiposPuntuados.get(rowIndex);

        switch (columnIndex){
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


