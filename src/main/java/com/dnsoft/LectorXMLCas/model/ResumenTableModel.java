/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.LectorXMLCas.model;

import com.dnsoft.LectorXMLCas.beans.Resumen;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Diego Noble
 */
public class ResumenTableModel extends AbstractTableModel {

    //nome da coluna da table
    private final String[] colunas = new String[]{"Sala", "Fecha", "Cantidad Videos", "Cantidad Ruletas", "Coin in video",
        "Coin in ruleta", "Coin in Total", "Win video", "Win ruleta", "Ganancia Total",
        "Win/maquina videos", "Win/maquina ruletas", "Win/maquina total", "Coin in Promo Video","Coin in Promo Ruleta" };
    //lista para a manipulacao do objeto
    private List<Resumen> list;

    public ResumenTableModel() {
        list = new LinkedList<>();
    }

    public ResumenTableModel(List<Resumen> list) {
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
        Resumen c = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return c.getSala();
            case 1:
                return c.getFecha();
            case 2:
                return c.getCantidad_Videos();
            case 3:
                return c.getCantidad_Ruletas();
            case 4:
                return c.getCoin_in_video();
            case 5:
                return c.getCoin_in_ruleta();
            case 6:
                return c.getCoin_in_Total();
            case 7:
                return c.getWin_video();
            case 8:
                return c.getWin_ruleta();
            case 9:
                return c.getGanancia_Total();
            case 10:
                return c.getWin_maquina_videos();
            case 11:
                return c.getWin_maquina_ruletas();
            case 12:
                return c.getWin_maquina_total();
            case 13:
                return c.getImporte_Coinin_Promo_Video();
            case 14:
                return c.getImporte_Coinin_Promo_Ruleta();
         
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
                return Integer.class;
            case 3:
                return Integer.class;
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
            case 9:
                return Double.class;
            case 10:
                return Double.class;
            case 11:
                return Double.class;
            case 12:
                return Double.class;
            case 13:
                return Double.class;
            case 14:
                return Double.class;
            case 15:
                return Double.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return true;

    }

    public void agregar(Resumen ciudad) {
        list.add(ciudad);

        this.fireTableRowsInserted(list.size() - 1, list.size() - 1);
    }

    public void eliminar(int row) {
        list.remove(row);
        this.fireTableRowsDeleted(row, row);
    }

    public void atualizar(int row, Resumen ciudad) {
        list.set(row, ciudad);
        this.fireTableRowsUpdated(row, row);
    }

    public Resumen getCliente(int row) {
        return list.get(row);
    }

}
