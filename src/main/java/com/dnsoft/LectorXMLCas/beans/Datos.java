/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.LectorXMLCas.beans;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.Date;
import javax.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author dnoble
 */
@XStreamAlias("Table")
@Entity
@Table(name = "Datos")
public class Datos extends AbstractPersistable<Long> {

   // @XStreamConverter(DateConverter.class)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date Fecha;
    private Integer Centro_Coste;
    private Integer Numero_Maquina;
    private Integer Ubicacion;
    private String Tipo_Sala;
    private String Tipo_Juego;
    private String Tipo_Moneda;
    private Double Importe_Handle;
    private Double Importe_Drop;
    private Double Importe_Win;
    private Integer Cantidad_Maquinas;
    private String Time_Teorico;
    private String Time_Total;
    private Integer Monedas_Partida;
    private Integer Numero_Transacciones;
    private Integer Handle_Maq_Hora;
    private Integer Win_Maq_Hora;
    private Integer Importe_Partida;
    private String Nombre_Archivo;
    private Double Importe_Coinin_Promo;

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Integer getCentro_Coste() {
        return Centro_Coste;
    }

    public void setCentro_Coste(Integer Centro_Coste) {
        this.Centro_Coste = Centro_Coste;
    }

    public Integer getNumero_Maquina() {
        return Numero_Maquina;
    }

    public void setNumero_Maquina(Integer Numero_Maquina) {
        this.Numero_Maquina = Numero_Maquina;
    }

    public Integer getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(Integer Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public String getTipo_Sala() {
        return Tipo_Sala;
    }

    public void setTipo_Sala(String Tipo_Sala) {
        this.Tipo_Sala = Tipo_Sala;
    }

    public String getTipo_Juego() {
        return Tipo_Juego;
    }

    public void setTipo_Juego(String Tipo_Juego) {
        this.Tipo_Juego = Tipo_Juego;
    }

    public String getTipo_Moneda() {
        return Tipo_Moneda;
    }

    public void setTipo_Moneda(String Tipo_Moneda) {
        this.Tipo_Moneda = Tipo_Moneda;
    }

    public Double getImporte_Handle() {
        return Importe_Handle;
    }

    public void setImporte_Handle(Double Importe_Handle) {
        this.Importe_Handle = Importe_Handle;
    }

    public Double getImporte_Drop() {
        return Importe_Drop;
    }

    public void setImporte_Drop(Double Importe_Drop) {
        this.Importe_Drop = Importe_Drop;
    }

    public Double getImporte_Win() {
        return Importe_Win;
    }

    public void setImporte_Win(Double Importe_Win) {
        this.Importe_Win = Importe_Win;
    }

    public Integer getCantidad_Maquinas() {
        return Cantidad_Maquinas;
    }

    public void setCantidad_Maquinas(Integer Cantidad_Maquinas) {
        this.Cantidad_Maquinas = Cantidad_Maquinas;
    }

    public String getTime_Teorico() {
        return Time_Teorico;
    }

    public void setTime_Teorico(String Time_Teorico) {
        this.Time_Teorico = Time_Teorico;
    }

    public String getTime_Total() {
        return Time_Total;
    }

    public void setTime_Total(String Time_Total) {
        this.Time_Total = Time_Total;
    }

    public Integer getMonedas_Partida() {
        return Monedas_Partida;
    }

    public void setMonedas_Partida(Integer Monedas_Partida) {
        this.Monedas_Partida = Monedas_Partida;
    }

    public Integer getNumero_Transacciones() {
        return Numero_Transacciones;
    }

    public void setNumero_Transacciones(Integer Numero_Transacciones) {
        this.Numero_Transacciones = Numero_Transacciones;
    }

    public Integer getHandle_Maq_Hora() {
        return Handle_Maq_Hora;
    }

    public void setHandle_Maq_Hora(Integer Handle_Maq_Hora) {
        this.Handle_Maq_Hora = Handle_Maq_Hora;
    }

    public Integer getWin_Maq_Hora() {
        return Win_Maq_Hora;
    }

    public void setWin_Maq_Hora(Integer Win_Maq_Hora) {
        this.Win_Maq_Hora = Win_Maq_Hora;
    }

    public Integer getImporte_Partida() {
        return Importe_Partida;
    }

    public void setImporte_Partida(Integer Importe_Partida) {
        this.Importe_Partida = Importe_Partida;
    }

    public String getNombre_Archivo() {
        return Nombre_Archivo;
    }

    public void setNombre_Archivo(String Nombre_Archivo) {
        this.Nombre_Archivo = Nombre_Archivo;
    }

    public Double getImporte_Coinin_Promo() {
        return Importe_Coinin_Promo;
    }

    public void setImporte_Coinin_Promo(Double Importe_Coinin_Promo) {
        this.Importe_Coinin_Promo = Importe_Coinin_Promo;
    }

   
    
}
