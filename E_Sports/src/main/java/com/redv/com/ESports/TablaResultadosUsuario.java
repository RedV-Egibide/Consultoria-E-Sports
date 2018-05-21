package com.redv.com.ESports;

import com.redv.com.ESports.modelo.Calendario;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TablaResultadosUsuario extends AbstractTableModel {

    private String[] columnas = {"Equipo 1", "Equipo 2", "Resultado"};

    public TablaResultadosUsuario(ArrayList<Calendario> partidosTemporada) {
        this.partidosTemporada = partidosTemporada;
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
        Calendario calendario = partidosTemporada.get(rowIndex);
        String resultado = "";

        switch (columnIndex) {

            case 0:
                return calendario.getEquipo1();

            case 1:
                return calendario.getEquipo2();
            case 2:
                switch (calendario.getResultado()) {
                    case 0:
                        resultado = "Empate";
                        break;
                    case 1:
                        resultado = "V - D";
                        break;
                    case 2:
                        resultado = "D - V";
                        break;
                    default:
                        resultado = "No jugado";
                        break;

                }
                return resultado;

        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
