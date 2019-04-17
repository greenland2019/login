/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import eplant.config.UserSession;
import eplant.services.ReclamationDao;
import eplant.entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private Button btn;
    @FXML
    private TextField email;
    @FXML
    private TextField sujet;
    @FXML
    private TextField contenue;
    @FXML
    private TextField Categorie;
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton quiz;
    @FXML
    private JFXButton rating;
    @FXML
    private JFXButton reclamation;
    @FXML
    private Pane BoutonValider;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
                UserSession session = UserSession.getInstace("", "");
 email.setText(session.getUserName());
          email.setDisable(true);
         btn.setOnAction(event -> {
         
            Reclamation r= new Reclamation( email.getText(),sujet.getText(),contenue.getText(),Categorie.getText());
          ReclamationDao pdao = ReclamationDao.getInstance();
            pdao.insert(r);
          
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Reclamation insérée avec succés!");
        alert.show();
        Categorie.setText("");
        sujet.setText("");       
        contenue.setText("");
        email.setText("");
        });
        
         
         
         
          ReclamationDao pdao = ReclamationDao.getInstance();
        ObservableList<Reclamation> l = pdao.displayAll();
        ObservableList<String> lis =FXCollections.observableArrayList();
        for(Reclamation r:l){
        lis.add(r.getCategorie());
        }
          // categorie.getItems().addAll(lis);
        
        
    
        
        
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/eplant/view/Acceuil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilreclamController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void rate(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/eplant/view/AjouterAvis.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilreclamController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
