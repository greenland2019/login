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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

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
    @FXML
    private Button produits;
    @FXML
    private Button savbut;
    @FXML
    private Button livraisonbut;
    @FXML
    private Text livraisontxt;
    @FXML
    private Text SAVtxt;
    @FXML
    private Text produitstxt;
    @FXML
    private Text comtxt;
    @FXML
    private Text promotionstxt;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserSession session = UserSession.getInstace("", "");
        
      
       if("livreur".equalsIgnoreCase(session.getRole())){
                 ap.getChildren().remove(promo);
                 ap.getChildren().remove(produits);
                 ap.getChildren().remove(savbut);
                 ap.getChildren().remove(com);
                 ap.getChildren().remove(SAVtxt);
                 ap.getChildren().remove(produitstxt);
                 ap.getChildren().remove(promotionstxt);
                 ap.getChildren().remove(comtxt);
                  
              }
        if("client".equalsIgnoreCase(session.getRole())){
                 ap.getChildren().remove(livraisonbut);
                 ap.getChildren().remove(livraisontxt);
                  
              }
         if("admin".equalsIgnoreCase(session.getRole())){
                  ap.getChildren().remove(livraisonbut);
                  ap.getChildren().remove(livraisontxt);

              }
       livraisonbut.setOnAction((event5) -> {
            Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Livraison.fxml"));
                           
        
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event5.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
       });
       
       produits.setOnAction((event6) -> {
            Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Store.fxml"));
                           
        
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event6.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
       });
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
        
        savbut.setOnAction((event5) -> {
           if("admin".equalsIgnoreCase(session.getRole())){
                  
                   Parent page2;
                              
            try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/AfficherReclamation.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event5.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
              }
           
           if("client".equalsIgnoreCase(session.getRole())){
                  
                   Parent page2;
                              
            try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Acceuil.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event5.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
              }
            
        });
        
       deconnect.setOnAction((event) -> {                     
           Parent page2;
               
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
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Communaute1.fxml"));
                           
        
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
       });
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
