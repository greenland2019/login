/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import com.itextpdf.text.BadElementException;
import eplant.config.UserSession;
import eplant.entities.Commande;
import eplant.entities.Livraison;
import eplant.entities.Panier;
import eplant.entities.Produit;
import eplant.services.CommandeService;
import eplant.services.LivraisonService;
import eplant.services.PanierService;
import eplant.services.PersonneService;
import eplant.services.ProduitService;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CommandeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Panier> commandetable;
    @FXML
    private TableColumn<Panier, String> produit;
    @FXML
    private TableColumn<Panier, String> qte;
    @FXML
    private TableColumn<Panier, String> prix;
    @FXML
    private  TextField addresse;
    @FXML
    private ComboBox zone;
     @FXML
    private Button valider;
       @FXML
    private Label total;
    @FXML
    private Label ptotal;
    
     private   ObservableList<Panier> paniers= FXCollections.observableArrayList();
     
      private   ObservableList<String> zones= FXCollections.observableArrayList("sokra","hammemet","bizerte");
    @FXML
    private Button back;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         PanierService ps =  PanierService.getInstance();
             UserSession session = UserSession.getInstace("", "");
        PersonneService pers = new PersonneService();
      ProduitService pr =  new ProduitService();
       CommandeService cs =  CommandeService.getInstance();
       LivraisonService ls =  LivraisonService.getInstance();
        paniers = ps.DisplayAll(pers.personbyEmaill(session.getUserName()).getId(),"");
        
       total.setText(cs.CalculerTotal(pers.personbyEmaill(session.getUserName()).getId())+" DT");
       ptotal.setText(cs.CalculerTotal(pers.personbyEmaill(session.getUserName()).getId())+50+" DT");
       
      commandetable.setItems(paniers);
         produit.setCellValueFactory(cell -> new SimpleStringProperty (pr.FindProduit( cell.
                getValue().getProduit_id()).getNom()));
       
        qte.setCellValueFactory(cell -> new SimpleStringProperty (""+cell.
                getValue().getQuantite()));
        
        prix.setCellValueFactory(cell -> new SimpleStringProperty (""+pr.FindProduit( cell.
                getValue().getProduit_id()).getPrix()*cell.
                getValue().getQuantite()));
        
        zone.setValue("Tunis");
        zone.setItems(zones);
        
         
          
          back.setOnMouseReleased((event) -> {
                   Parent page2;
                        try {
                            
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Panier.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
              });
          
           valider.setOnAction((event) -> {
               int x;
               int y;
               String z;
               int k;
               z=(String) zone.getSelectionModel().getSelectedItem();
                String pro ="";
        for(Panier p : paniers)
        {
            pro =pro+ pr.FindProduit(p.getProduit_id()).getNom()+"*"+p.getQuantite()+"/";
           cs.updatestock(pr.FindProduit(p.getProduit_id()).getId(), p.getQuantite());
        }
               System.out.println(pro);
              x=cs.CalculerTotal(pers.personbyEmaill(session.getUserName()).getId());
              k=cs.findlivreur(z);
                 Commande c=new Commande(pers.personbyEmaill(session.getUserName()).getId(),"",x,pro);
                
                 cs.ajouterCommande(c);
                 y=cs.findCommande();
                 if(!addresse.getText().equalsIgnoreCase("")){
                  Livraison l=new Livraison(k,y,addresse.getText(),"en cours");
                
                 ps.suppaniers(pers.personbyEmaill(session.getUserName()).getId());
                 ls.ajouterLivraison(l);
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information ");
        alert.setHeaderText(null);
        alert.setContentText("commande éffectué avec succes !");
        alert.show();
                 }
                 else
                 {
                  Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information ");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir l'adresse!");
        alert.show();
                 }
             try {
                 cs.generatepdfparts(paniers,c,y);
             } catch (BadElementException ex) {
                 Logger.getLogger(CommandeController.class.getName()).log(Level.SEVERE, null, ex);
             }
               
                  
             });
          
        
    }    
    
}
