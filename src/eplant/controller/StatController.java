/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.services.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class StatController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML 
    private BarChart <?,?> stat;
    @FXML
    private CategoryAxis x;
     @FXML
    private NumberAxis y;
    @FXML
    private Button back;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CommandeService cs =  CommandeService.getInstance();
        XYChart.Series set1 = new XYChart.Series<>();
        
        set1.getData().add(new XYChart.Data("JANVIER", cs.Calculerstat(1)));
         set1.getData().add(new XYChart.Data("FEVRIER", cs.Calculerstat(2)));
          set1.getData().add(new XYChart.Data("MARS", cs.Calculerstat(3)));
           set1.getData().add(new XYChart.Data("AVRIL", cs.Calculerstat(4)));
        
        stat.getData().addAll(set1);
        
        back.setOnMouseReleased((event) -> {
                   Parent page2;
                        try {
                            
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Store.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
              });
        // TODO
    }    
    
}
