/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.controller.*;
import eplant.services.AvisDao;
import eplant.entities.Avis;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class PieChartViewController_1 implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart pieChart;
    ObservableList<Data> list=FXCollections.
            observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AvisDao pdao=AvisDao.getInstance();
        List<Avis> avis=pdao.displayAllList();
        for(Avis a:avis) {
            list.addAll(
                new Data(a.getEtat(), 12.0)             
        );
        }
        pieChart.setAnimated(true);
        pieChart.setData(list);
        
    }

}
