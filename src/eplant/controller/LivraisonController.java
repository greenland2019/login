/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.config.UserSession;
import eplant.entities.Livraison;
import eplant.entities.Panier;
import eplant.services.LivraisonService;
import eplant.services.PanierService;
import eplant.services.PersonneService;
import eplant.services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LivraisonController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Livraison> livraisontable ;
    @FXML
    private TableColumn<Livraison, String> id_commande;
    @FXML
    private TableColumn<Livraison, String> adresse;
    @FXML
    private Button livre;
     private   ObservableList<Livraison> Livraisons= FXCollections.observableArrayList();
    @FXML
    private Button back;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            UserSession session = UserSession.getInstace("", "");
        PersonneService pers = new PersonneService();
          LivraisonService ls =  LivraisonService.getInstance();
      ProduitService pr = new ProduitService();
      Livraisons= ls.DisplayAll(pers.SearchByMail(session.getUserName()).getId());
      livraisontable.setItems(Livraisons);
        id_commande.setCellValueFactory(cell ->new SimpleStringProperty (""+cell.
                getValue().getCommande_id()));
        adresse.setCellValueFactory(cell ->new SimpleStringProperty (""+cell.
                getValue().getAddresse()));
         livre.setOnAction(event -> {

          int x=livraisontable.getSelectionModel().getSelectedItem().getCommande_id();
               
                
                     ls.modifierlivraison(x);
                     
        });
         
         back.setOnMouseReleased((event) -> {
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
    }    
    
}
