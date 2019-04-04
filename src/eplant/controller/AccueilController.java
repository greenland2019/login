/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.config.UserSession;
import eplant.services.PersonneService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AccueilController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private AnchorPane ap;
      @FXML
    private Button deconnect;
      
      @FXML
    private Text login;
      
       @FXML
    private Button com;
       
        @FXML
    private Button promo;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ap.heightProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double height = (double) newValue;
                ap.setPrefHeight(height/2);
            }
        
        
        });
        
        ap.widthProperty().addListener(new ChangeListener(){
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                double width = (double) newValue;
                ap.setPrefWidth(width/2);
            }
        
        
        });
       deconnect.setOnAction((event) -> {                     
           Parent page2;
               UserSession session = UserSession.getInstace("", "");
               session.cleanUserSession();
               
            try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Login.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
       });
       
       com.setOnAction((event) -> {                     
           Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Communaute.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
       });
        UserSession session = UserSession.getInstace("", "");
        PersonneService s = new PersonneService();
       login.setText(s.SearchByMail(session.getUserName()).getPrenom());
       
       promo.setOnAction((event) -> {
           Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Promotion.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
       });
    }    
    
}
