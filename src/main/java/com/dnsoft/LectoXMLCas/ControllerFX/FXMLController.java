package com.dnsoft.LectoXMLCas.ControllerFX;

import com.dnsoft.LectorXMLCas.beans.ResumenPorMes;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class FXMLController implements Initializable {

    @FXML
    private BarChart<String, Double> barChartCoinIn;
    @FXML
    private AreaChart<String, Double> aChartCoinIn;
    @FXML
    private AreaChart<String, Double> aChartWin;
    @FXML
    private BarChart<String, Double> barChartWin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setCoinInData(List<ResumenPorMes> listResumen) {

        for (ResumenPorMes resumenPorMes : listResumen) {
            switch (resumenPorMes.getMes()) {
                case 1:
                    resumenPorMes.setNombreMes("Enero");
                    break;
                case 2:
                    resumenPorMes.setNombreMes("Febrero");
                    break;
                case 3:
                    resumenPorMes.setNombreMes("Marzo");
                    break;
                case 4:
                    resumenPorMes.setNombreMes("Abril");
                    break;
                case 5:
                    resumenPorMes.setNombreMes("Mayo");
                    break;
                case 6:
                    resumenPorMes.setNombreMes("Junio");
                    break;
                case 7:
                    resumenPorMes.setNombreMes("Julio");
                    break;
                case 8:
                    resumenPorMes.setNombreMes("Agosto");
                    break;
                case 9:
                    resumenPorMes.setNombreMes("Setiembre");
                    break;
                case 10:
                    resumenPorMes.setNombreMes("Octubre");
                    break;
                case 11:
                    resumenPorMes.setNombreMes("Noviembre");
                    break;
                case 12:
                    resumenPorMes.setNombreMes("Diciembre");
                    break;
                default:
                    throw new AssertionError();
            }
        }

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        XYChart.Series<String, Double> series2 = new XYChart.Series<>();

        for (int i = 0; i < listResumen.size(); i++) {
            series.getData().add(new XYChart.Data<>(listResumen.get(i).getNombreMes(), listResumen.get(i).getSuma()));
        }
        for (int i = 0; i < listResumen.size(); i++) {
            series2.getData().add(new XYChart.Data<>(listResumen.get(i).getNombreMes(), listResumen.get(i).getSuma()));
        }

        
        barChartCoinIn.getData().add(series);
        aChartCoinIn.getData().add(series2);
    }

     public void setWinData(List<ResumenPorMes> listResumen) {

        for (ResumenPorMes resumenPorMes : listResumen) {
            switch (resumenPorMes.getMes()) {
                case 1:
                    resumenPorMes.setNombreMes("Enero");
                    break;
                case 2:
                    resumenPorMes.setNombreMes("Febrero");
                    break;
                case 3:
                    resumenPorMes.setNombreMes("Marzo");
                    break;
                case 4:
                    resumenPorMes.setNombreMes("Abril");
                    break;
                case 5:
                    resumenPorMes.setNombreMes("Mayo");
                    break;
                case 6:
                    resumenPorMes.setNombreMes("Junio");
                    break;
                case 7:
                    resumenPorMes.setNombreMes("Julio");
                    break;
                case 8:
                    resumenPorMes.setNombreMes("Agosto");
                    break;
                case 9:
                    resumenPorMes.setNombreMes("Setiembre");
                    break;
                case 10:
                    resumenPorMes.setNombreMes("Octubre");
                    break;
                case 11:
                    resumenPorMes.setNombreMes("Noviembre");
                    break;
                case 12:
                    resumenPorMes.setNombreMes("Diciembre");
                    break;
                default:
                    throw new AssertionError();
            }
        }

        XYChart.Series<String, Double> series1 = new XYChart.Series<>();
        XYChart.Series<String, Double> series3 = new XYChart.Series<>();


        for (int i = 0; i < listResumen.size(); i++) {
            series1.getData().add(new XYChart.Data<>(listResumen.get(i).getNombreMes(), listResumen.get(i).getSuma()));
        }
        for (int i = 0; i < listResumen.size(); i++) {
            series3.getData().add(new XYChart.Data<>(listResumen.get(i).getNombreMes(), listResumen.get(i).getSuma()));
        }
        
        barChartWin.getData().add(series1);
        aChartWin.getData().add(series3);
    }

     
}
