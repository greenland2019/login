/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class AccueilreclamController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private AnchorPane rootLayout;
    private Stage primaryStage;
    @FXML
    private Button btn_add_reclamation;
     @FXML
    private Button btn_add_avis;
    @FXML
    private Button btn_quiz;
    @FXML
    private Button backbutton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btn_add_reclamation.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/eplant/view/AjouterReclamation.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilreclamController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

       /* btn_display_reclamation.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/reclamation/view/Accueil.fxml"));
                Parent page2 = FXMLLoader.load(getClass().getResource("/com/reclamation/view/AfficherReclamation.fxml"));
                 // Give the controller access to the main app.
                //AfficherReclamationController controller =loader.getController();
               //controller.setListData(new ListData());
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilreclamController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });*/
        
        btn_add_avis.setOnAction(event -> {

            try {
                Parent page3 = FXMLLoader.load(getClass().getResource("/eplant/view/AjouterAvis.fxml"));
                Scene scene = new Scene(page3);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilreclamController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        backbutton.setOnMouseReleased((event) -> {
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
              });
       /* btn_display_avis.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/reclamation/view/Accueil.fxml"));
                Parent page4 = FXMLLoader.load(getClass().getResource("/com/reclamation/view/AfficherAvis.fxml"));
                 // Give the controller access to the main app.
                //AfficherReclamationController controller =loader.getController();
               //controller.setListData(new ListData());
                Scene scene = new Scene(page4);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilreclamController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });*/
        
        btn_quiz.setOnAction((event) -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                //loader.setLocation(getClass().getResource("/com/reclamation/view/Accueil.fxml"));
                Parent page5 = FXMLLoader.load(getClass().getResource("/eplant/view/quiz.fxml"));
                 // Give the controller access to the main app.
                //AfficherReclamationController controller =loader.getController();
               //controller.setListData(new ListData());
                Scene scene = new Scene(page5);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilreclamController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
    }

}
