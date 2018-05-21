package com.redv.com.ESports;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TablaResultadosUsuario extends AbstractTableModel {

    private String[] columnas = {"Equipo 1", "Equipo 2", "Resultado"};

    public TablaResultadosUsuario(ArrayList<Calendario> partidosTemporada) {
        this.partidosTemporada = partidosTemporada;
        System.out.println("ME EJECUTO: CONSTRUCTOR");
    }

    private ArrayList<Calendario> partidosTemporada;

    @Override
    public int getRowCount() {
        return partidosTemporada.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        System.out.println("ME EJECUTO: MOSTRAR LINEAS");

        Calendario calendario = partidosTemporada.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return calendario.getEquipo1();

            case 1:
                return calendario.getEquipo2();
            case 2:
                switch (calendario.getResultado()) {
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
