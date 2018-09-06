/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dnsoft.LectorXMLCas.beans;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "Resumen")
public class Resumen extends AbstractPersistable<Long> {

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    private Integer Cantidad_Videos;
    private Integer Cantidad_Rodillos;
    private Integer Cantidad_Ruletas;
    private Double Coin_in_video;
    private Double Coin_in_rodillo;
    private Double Coin_in_ruleta;
    private Double Win_video;
    private Double Win_rodillo;
    private Double Win_ruleta;
    private Double Coin_in_Total;
    private Double Ganancia_Total;
    private Double Win_maquina_videos;
    private Double Win_maquina_rodillos;
    private Double Win_maquina_ruletas;
    private Double Win_maquina_total;
    private Double Retencion_videos;
    private Double Retencion_rodillos;
    private Double Retencion_Ruletas;
    private Double Retencion_Total;
    private String Sala;
    private String Nombre_Archivo;
    private Double Importe_Coinin_Promo_Ruleta;
    private Double Importe_Coinin_Promo_Video;

    public Resumen() {
    }

    public String getSala() {
        return Sala;
    }

    public void setSala(String sala) {
        this.Sala = sala;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getCantidad_Videos() {
        return Cantidad_Videos;
    }

    public void setCantidad_Videos(Integer Cantidad_Videos) {
        this.Cantidad_Videos = Cantidad_Videos;
    }

    public Integer getCantidad_Rodillos() {
        return Cantidad_Rodillos;
    }

    public void setCantidad_Rodillos(Integer Cantidad_Rodillos) {
        this.Cantidad_Rodillos = Cantidad_Rodillos;
    }

    public Integer getCantidad_Ruletas() {
        return Cantidad_Ruletas;
    }

    public void setCantidad_Ruletas(Integer Cantidad_Ruletas) {
        this.Cantidad_Ruletas = Cantidad_Ruletas;
    }

    public Double getCoin_in_video() {
        return Coin_in_video;
    }

    public void setCoin_in_video(Double Coin_in_video) {
        this.Coin_in_video = Coin_in_video;
    }

    public Double getCoin_in_rodillo() {
        return Coin_in_rodillo;
    }

    public void setCoin_in_rodillo(Double Coin_in_rodillo) {
        this.Coin_in_rodillo = Coin_in_rodillo;
    }

    public Double getCoin_in_ruleta() {
        return Coin_in_ruleta;
    }

    public void setCoin_in_ruleta(Double Coin_in_ruleta) {
        this.Coin_in_ruleta = Coin_in_ruleta;
    }

    public Double getWin_video() {
        return Win_video;
    }

    public void setWin_video(Double Win_video) {
        this.Win_video = Win_video;
    }

    public Double getWin_rodillo() {
        return Win_rodillo;
    }

    public void setWin_rodillo(Double Win_rodillo) {
        this.Win_rodillo = Win_rodillo;
    }

    public Double getWin_ruleta() {
        return Win_ruleta;
    }

    public void setWin_ruleta(Double Win_ruleta) {
        this.Win_ruleta = Win_ruleta;
    }

    public Double getCoin_in_Total() {
        return Coin_in_Total;
    }

    public void setCoin_in_Total(Double Coin_in_Total) {
        this.Coin_in_Total = Coin_in_Total;
    }

    public Double getGanancia_Total() {
        return Ganancia_Total;
    }

    public void setGanancia_Total(Double Ganancia_Total) {
        this.Ganancia_Total = Ganancia_Total;
    }

    public Double getWin_maquina_videos() {
        return Win_maquina_videos;
    }

    public void setWin_maquina_videos(Double Win_maquina_videos) {
        this.Win_maquina_videos = Win_maquina_videos;
    }

    public Double getWin_maquina_rodillos() {
        return Win_maquina_rodillos;
    }

    public void setWin_maquina_rodillos(Double Win_maquina_rodillos) {
        this.Win_maquina_rodillos = Win_maquina_rodillos;
    }

    public Double getWin_maquina_ruletas() {
        return Win_maquina_ruletas;
    }

    public void setWin_maquina_ruletas(Double Win_maquina_ruletas) {
        this.Win_maquina_ruletas = Win_maquina_ruletas;
    }

    public Double getWin_maquina_total() {
        return Win_maquina_total;
    }

    public void setWin_maquina_total(Double Win_maquina_total) {
        this.Win_maquina_total = Win_maquina_total;
    }

    public Double getRetencion_videos() {
        return Retencion_videos;
    }

    public void setRetencion_videos(Double Retencion_videos) {
        this.Retencion_videos = Retencion_videos;

    }

    public Double getRetencion_rodillos() {
        return Retencion_rodillos;
    }

    public void setRetencion_rodillos(Double Retencion_rodillos) {
        this.Retencion_rodillos = Retencion_rodillos;
    }

    public Double getRetencion_Ruletas() {
        return Retencion_Ruletas;
    }

    public void setRetencion_Ruletas(Double Retencion_Ruletas) {
        this.Retencion_Ruletas = Retencion_Ruletas;
    }

    public Double getRetencion_Total() {
        return Retencion_Total;
    }

    public void setRetencion_Total(Double Retencion_Total) {
        this.Retencion_Total = Retencion_Total;
    }

    public String getNombre_Archivo() {
        return Nombre_Archivo;
    }

    public void setNombre_Archivo(String Nombre_Archivo) {
        this.Nombre_Archivo = Nombre_Archivo;
    }

    public Double getImporte_Coinin_Promo_Ruleta() {
        return Importe_Coinin_Promo_Ruleta;
    }

    public void setImporte_Coinin_Promo_Ruleta(Double Importe_Coinin_Promo_Ruleta) {
        this.Importe_Coinin_Promo_Ruleta = Importe_Coinin_Promo_Ruleta;
    }

    public Double getImporte_Coinin_Promo_Video() {
        return Importe_Coinin_Promo_Video;
    }

    public void setImporte_Coinin_Promo_Video(Double Importe_Coinin_Promo_Video) {
        this.Importe_Coinin_Promo_Video = Importe_Coinin_Promo_Video;
    }

   
}
