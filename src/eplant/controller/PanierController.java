/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import com.itextpdf.text.BadElementException;
import eplant.config.UserSession;
import eplant.entities.Panier;
import eplant.entities.Produit;
import eplant.services.CommandeService;
import eplant.services.PanierService;
import eplant.services.PersonneService;
import eplant.services.ProduitService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PanierController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private TableView<Panier> paniertable;
    @FXML
    private TableColumn<Panier, String> imgColonne;
    @FXML
    private TableColumn<Panier, String> nomColonne;
    @FXML
    private TableColumn<Panier, String> qteColonne;
    @FXML
    private TableColumn<Panier, String> puColonne;
    @FXML
    private TableColumn<Panier, String> ptColonne;
    
     @FXML
    private Button supp;
      @FXML
    private Button mod;
       @FXML
    private Button valide;
       @FXML
        private TextField qtemod ;
    
     private   ObservableList<Panier> paniers= FXCollections.observableArrayList();
    
    @FXML
    private TextField recherche;
    @FXML
    private Button back;
     
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
                     UserSession session = UserSession.getInstace("", "");
        PersonneService pers = new PersonneService();

      PanierService ps =  PanierService.getInstance();
      ProduitService pr = new  ProduitService();
      CommandeService cs =  CommandeService.getInstance();
      paniers = ps.DisplayAll(pers.personbyEmaill(session.getUserName()).getId(),"");
      paniertable.setItems(paniers);
       
        imgColonne.setCellValueFactory(cell ->new SimpleStringProperty (pr.FindProduit( cell.
                getValue().getProduit_id()).getImg()));
        nomColonne.setCellValueFactory(cell -> new SimpleStringProperty (pr.FindProduit( cell.
                getValue().getProduit_id()).getNom()));
        qteColonne.setCellValueFactory(cell -> new SimpleStringProperty (""+cell.
                getValue().getQuantite()));
        puColonne.setCellValueFactory(cell -> new SimpleStringProperty (""+pr.FindProduit( cell.
                getValue().getProduit_id()).getPrix()));
        ptColonne.setCellValueFactory(cell -> new SimpleStringProperty (""+pr.FindProduit( cell.
                getValue().getProduit_id()).getPrix()*cell.
                getValue().getQuantite()));
        
        recherche.setOnKeyReleased((event) -> {
            paniers = ps.DisplayAll(pers.personbyEmaill(session.getUserName()).getId(),recherche.getText());
      paniertable.setItems(paniers);
        });
        //Aller a  Commande.fxml
          valide.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/eplant/view/Commande.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
         //Modifier la quantité 
           mod.setOnAction((event) -> {
               int x=paniertable.getSelectionModel().getSelectedItem().getProduit_id();
              
             try{ 
             
                 Panier pan = new Panier(pers.personbyEmaill(session.getUserName()).getId(),x, qtemod.getText().equalsIgnoreCase("")?1:Integer.valueOf(qtemod.getText()), "");
                if(!verifqt(x, Integer.valueOf(qtemod.getText())))
                 {
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Quantité inssufisante");
        alert.show();
                 }
                else{
                     ps.modifierpanier(pan);
                    // paniertable.refresh();
                     try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/eplant/view/Panier.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
               
               }
               }
               
              
          
             
                 catch(Exception ex){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information ");
        alert.setHeaderText(null);
        alert.setContentText("Saisir un entier correct!");
        alert.show();
               }
             });
        
         //Supprimer un élément du panier
         supp.setOnAction((event) -> {
                int x=paniertable.getSelectionModel().getSelectedItem().getProduit_id();
                 int y=paniertable.getSelectionModel().getSelectedItem().getUser_id();
                 ps.supppanier(x, 3);
                
               paniertable.getItems().removeAll(paniertable.getSelectionModel().getSelectedItem());
               
                    
             });
         
        
        //Retour a Produit.fxml
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
    
    //verification de la quantité
    public boolean verifqt(int y,int x){
        ProduitService pr = new ProduitService();
        if(pr.FindProduit(y).getStock()<x)
        {return false;}
        
        
        return true;}
    
     

}

   
    

