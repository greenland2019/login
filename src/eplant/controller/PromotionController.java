/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.config.UserSession;
import eplant.entities.Produit;
import eplant.entities.Promotion;
import eplant.services.PromotionService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class PromotionController implements Initializable {

    @FXML
    private AnchorPane ap;
  @FXML
    private TableView<Promotion> promoTable;
     @FXML
    private TableColumn<Promotion, String> produit;
    @FXML
    private TableColumn<Promotion, String> reduction;
    
      @FXML
    private Text prix;
     @FXML
    private TextField nomp;
         @FXML
    private TextField red;
           @FXML
    private TextField datedeb;
             @FXML
    private DatePicker datefin;
               @FXML
    private TextField search;
               
               @FXML
    private Button logout;
                  @FXML
    private Button edit;
               @FXML
    private Button supprimer;
               
                        @FXML
    private ImageView imgprod;
               
    private   ObservableList<Promotion> promotions= FXCollections.observableArrayList();
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        UserSession session = UserSession.getInstace("", "");
        
          if("client".equalsIgnoreCase(session.getRole()))
               {
                
                   datefin.setEditable(false);
                 red.setEditable(false);
                 ap.getChildren().remove(supprimer);
                 ap.getChildren().remove(edit);
                
               }
        
        nomp.setEditable(false);
        datedeb.setEditable(false);
        search.setPromptText("Chercher un produit");
        PromotionService p1 = new PromotionService();
        
                      
                   promotions= p1.displayBySearch(search.getText());
                   
                    promoTable.setItems(promotions);
       produit.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProp());
        reduction.setCellValueFactory(cell -> new SimpleStringProperty(""+cell.
                getValue().getReduction()));    
        
          promoTable.setOnMouseClicked(event->{
              int oldvalue= promotions
                .get(promoTable.getSelectionModel().getSelectedIndex())
                .getReduction();
              
             Produit prod= p1.searchProduit(promotions
                .get(promoTable.getSelectionModel().getSelectedIndex())
                .getProduit_id());
        
             prix.setText(String.valueOf(prod.getPrix())+" DT");
        nomp.setText(prod.getNom());
        red.setText(String.valueOf(promotions
                .get(promoTable.getSelectionModel().getSelectedIndex())
                .getReduction()));
        datedeb.setText(promotions
                .get(promoTable.getSelectionModel().getSelectedIndex())
                .getDate_promo());
        datefin.setPromptText(String.valueOf(promotions
                .get(promoTable.getSelectionModel().getSelectedIndex())
                .getDate_fin()));
        
         File file = new File("C:/Users/HP/Documents/NetBeansProjects/Eplants/src/images/"+prod.getImg());
          Image image = new Image(file.toURI().toString());
        imgprod.setImage(image);
        
        edit.setOnAction((event2) -> {
            if(red.getText().equalsIgnoreCase("") || (datefin.getEditor().getText().equalsIgnoreCase("") && datefin.getPromptText().equalsIgnoreCase(""))){
            
              Alert alert = new Alert(Alert.AlertType.WARNING);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs!");
        alert.show();
            }
            
          
            else {
            try {
                  
                Promotion promo = new Promotion();
                promo.setDate_fin((!"".equalsIgnoreCase(datefin.getEditor().getText()))?""+datefin.getValue():datefin.getPromptText());
               
                promo.setReduction(Integer.valueOf(red.getText()));
                promo.setProduit_id(promotions
                .get(promoTable.getSelectionModel().getSelectedIndex())
                .getProduit_id());
                promo.setId(promotions
                .get(promoTable.getSelectionModel().getSelectedIndex())
                .getId());
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 Date date1 = null;
                 Date date2 = null;
                     try {
                          date1 = sdf.parse(promo.getDate_fin());
                          date2 = sdf.parse(sdf.format(new Date()));
                     } catch (ParseException ex) {
                         Logger.getLogger(CommunauteController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 
              if(date2.compareTo(date1)>0){
              
              Alert alert = new Alert(Alert.AlertType.WARNING);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs!");
        alert.show();

              }
              
              else{
                 p1.modifierPromotion(promo,oldvalue);
                 
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
             Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Promotion modifiée");
        alert.show();
           
                 }
              }
            catch (Exception e) {
                   Alert alert = new Alert(Alert.AlertType.ERROR);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Reduction incorrecte!");
        alert.show();

            }
            } 
        });
               
        supprimer.setOnAction((event4) -> {
               Promotion prom = new Promotion();
                prom.setReduction(oldvalue);
                prom.setProduit_id(promotions
                .get(promoTable.getSelectionModel().getSelectedIndex())
                .getProduit_id());
                prom.setId(promotions
                .get(promoTable.getSelectionModel().getSelectedIndex())
                .getId());
                
                p1.supprimerPromotion(prom);
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
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Promotion annulé");
        alert.show();
          });
        
                  });
          
          search.setOnKeyReleased((event) -> {
              promotions= p1.displayBySearch(search.getText());
                  promoTable.setItems(promotions);
       produit.setCellValueFactory(cell -> cell.
                getValue().getDescriptionProp());
        reduction.setCellValueFactory(cell -> new SimpleStringProperty(""+cell.
                getValue().getReduction()));    
          });
          
          
          

    logout.setOnAction((event) -> {
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
