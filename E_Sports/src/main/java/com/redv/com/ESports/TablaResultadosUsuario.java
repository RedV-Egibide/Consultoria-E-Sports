package com.redv.com.ESports;

import javax.swing.table.AbstractTableModel;

public class TablaResultadosUsuario extends AbstractTableModel {
    private String[] columnas = {"Equipo 1", "Equipo 2", "Resultado"};

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
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
