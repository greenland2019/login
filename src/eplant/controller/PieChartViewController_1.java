/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.controller.*;
import eplant.services.AvisDao;
import eplant.entities.Avis;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        List<Integer> nbr = new ArrayList<>();
        List<String> avis=pdao.displayAllbyType(nbr);
        int i =0;
        for(String a:avis) {
            list.addAll(
                new Data(a, nbr.get(i)) 
                    
        );
            i++;
        }
        pieChart.setAnimated(true);
        pieChart.setData(list);
        
    }

}
