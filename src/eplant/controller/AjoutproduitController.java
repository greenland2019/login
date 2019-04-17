/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.entities.Produit;
import eplant.services.ProduitService;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutproduitController implements Initializable {

    @FXML
    private TextField nomp;
    @FXML
    private TextField descrip;
    @FXML
    private TextField prixp;
    @FXML
    private TextField catp;
    @FXML
    private TextField stockp;
    @FXML
    private TextField imagp;
    @FXML
    private Button importp;
    @FXML
    private Button ajouterp;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProduitService ps = new ProduitService();
        importp.setOnAction((event) -> {
             FileChooser f = new FileChooser();
                f.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image", "*.*")
            );
                
                File fi = f.showOpenDialog(null);
                
                if(fi!=null){
                imagp.setText(fi.getName());
    }
        });
        
        ajouterp.setOnAction((event2) -> {
            Produit p = new Produit();
           
            try {
                 p.setCategorie(catp.getText());
            p.setDescription(descrip.getText());
            p.setImg(imagp.getText());
            p.setNom(nomp.getText());
            p.setPrix(Float.valueOf(prixp.getText()));
            p.setStock(Integer.valueOf(stockp.getText()));
            ps.add(p);
            
            Parent page2;
           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Store.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event2.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                
                        } catch (IOException ex) {
                            Logger.getLogger(AjoutproduitController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Produit ajouté");
        alert.show();
            } catch (Exception e) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);

                        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir les champs correctement!");
        alert.show();
            }
        });
                
                
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
    }  
}
