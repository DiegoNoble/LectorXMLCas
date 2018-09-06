/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.LectorXMLCas.model;

import com.dnsoft.LectorXMLCas.beans.Datos;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class SlotTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Sala", "Dia", "Número", "Tipo Juego", "Coin in", "Drop", "Win", "Promoticket", "Win Neto"};
    //lista para a manipulacao do objeto
    private List<Datos> list;

    public SlotTableModel() {
        list = new LinkedList<>();
    }

    public SlotTableModel(List<Datos> list) {
        this.list = list;
    }

    //numero de linhas
    @Override
    public int getRowCount() {
        return list.size();
    }

    //numero de colunas
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    //define o que cada coluna conterï¿½ do objeto
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Datos c = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                if (c.getCentro_Coste() == 1) {
                    return "Casino";
                } else if (c.getCentro_Coste() == 2) {
                    return "Sarandi";
                }
            case 1:
                return c.getFecha();
            case 2:
                return c.getNumero_Maquina();
            case 3:
                return c.getTipo_Juego();
            case 4:
                return c.getImporte_Handle();
            case 5:
                return c.getImporte_Drop();
            case 6:
                return c.getImporte_Win();
            case 7:
                return c.getImporte_Coinin_Promo();

            case 8:
                return c.getImporte_Win() - c.getImporte_Coinin_Promo();
            default:
                return null;
        }
    }

    //determina o nome das colunas
    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    //determina que tipo de objeto cada coluna irï¿½ suportar
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Date.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Double.class;
            case 5:
                return Double.class;
            case 6:
                return Double.class;
                 case 7:
                return Double.class;
                 case 8:
                return Double.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return true;

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Datos c = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                c.setFecha((Date) aValue);
                break;
            case 1:
                c.setNumero_Maquina((Integer) aValue);
                break;
            case 2:
                c.setTipo_Juego((String) aValue);
                break;
            case 3:
                c.setImporte_Handle((Double) aValue);
                break;
            case 4:
                c.setImporte_Drop((Double) aValue);
                break;
            case 5:
                c.setImporte_Win((Double) aValue);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex); //To change body of generated methods, choose Tools | Templates.
    }

    public void agregar(Datos ciudad) {
        list.add(ciudad);

        this.fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }

    public void eliminar(int row) {
        list.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, Datos ciudad) {
        list.set(row, ciudad);
        this.fireTableRowsUpdated(row, row);
    }

    public Datos getCliente(int row) {
        return list.get(row);
    }

}
