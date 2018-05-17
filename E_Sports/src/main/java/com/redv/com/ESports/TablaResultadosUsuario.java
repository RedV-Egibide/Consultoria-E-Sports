package com.redv.com.ESports;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TablaResultadosUsuario extends AbstractTableModel {

    private String[] columnas = {"Equipo 1", "Equipo 2", "Resultado"};

    private ArrayList<Partido> partidos;//HAY QUE CONECTAR ESTE ARRAY CON EL ARRAY QUE GENERA LA TEMPORADA.


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

        Partido partido = partidos.get(rowIndex);


        switch (columnIndex){
            case 0:
                return partido.getEquipoA();
            case 1:
                return partido.getEquipoB();
            case 2:
                switch (partido.getResultado()){
                    case 0:
                        return "Empate";
                    case 1:
                        return "V - D";
                    case 2:
                        return "D - V";
                    default:
                        return "No jugado";

                }


        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
