/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.LectorXMLCas.beans;

/**
 *
 * @author Diego
 */
public class ResumenPorMes {

    private Integer mes;
    private Double suma;
    private String nombreMes;

    public ResumenPorMes(Integer mes, Double suma) {
        this.mes = mes;
        this.suma = suma;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Double getSuma() {
        return suma;
    }

    public void setSuma(Double suma) {
        this.suma = suma;
    }

    public String getNombreMes() {
        return nombreMes;
    }

    public void setNombreMes(String nombreMes) {
        this.nombreMes = nombreMes;
    }

}
