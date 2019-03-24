/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.entities.Personne;
import eplant.services.PersonneService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eplants
 */
public class LoginController implements Initializable {

    
    /**
     * Initializes the controller class.
     */
    
     @FXML
    private Button connect;
     
     @FXML
    private Button inscription;
      @FXML
    private TextField email;
        @FXML
    private TextField mdp;
        
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connect.setOnAction((event) -> {
          
                if(email.getText().equalsIgnoreCase("") || mdp.getText().equalsIgnoreCase("")){
                    Alert alert = new Alert(AlertType.WARNING);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.show();

                }
                
                else {
                    Personne p = new Personne();
                    p.setEmail(email.getText());
                    p.setPassword(mdp.getText());
                    PersonneService s1 = new PersonneService();
                    if(s1.check(p)==true){
                     Parent page2;
                        try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Accueil.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                // Give the controller access to the main app.
//                AfficherPersonneController controller =loader.getController();
//                controller.setListData(new ListData());
               
                    }
                    else{
                    Alert alert = new Alert(AlertType.ERROR);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Informations erronees");
        alert.show();

                    }
                }
      
        });
        
        inscription.setOnAction((event) -> {
            Parent page;
              try {
                            page = FXMLLoader.load(getClass().getResource("/eplant/view/signin.fxml"));
                             Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        });
    }    
    
}
