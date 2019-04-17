/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.config.ConnexionSingleton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.jdbc.JDBCCategoryDataset;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class StatsprodController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ConnexionSingleton cons;
     Connection con= cons.getInstance().getCnx();
    @FXML
    private AnchorPane apstat;
    @FXML
    private Button back;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
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
        
        final String SQL = "    SELECT produits , COUNT(user_id) FROM commande GROUP BY produits";
        
final CategoryDataset dataset;
        try {
            dataset = new JDBCCategoryDataset(con, SQL);
              JFreeChart chart = ChartFactory.createBarChart3D("produit", "axe x", "axe y", dataset, PlotOrientation.VERTICAL, true, true, true);
         ChartFrame frame = new ChartFrame("produit",chart);
         frame.pack();
         frame.setVisible(true);
        // apstat.getChildren().add(frame);
        } catch (SQLException ex) {
            Logger.getLogger(StatsprodController.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }    
    
}
