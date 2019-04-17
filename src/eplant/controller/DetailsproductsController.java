/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.config.UserSession;
import eplant.entities.Produit;
import eplant.entities.Promotion;
import eplant.services.ProduitService;
import eplant.services.PromotionService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
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
public class DetailsproductsController implements Initializable {

    @FXML
    private ImageView imgp;
    @FXML
    private Text nomp;
    @FXML
    private TextField descp;
    @FXML
    private TextField prixp;
    @FXML
    private TextField stockp;
    @FXML
    private Button supprimp;
    @FXML
    private Button modifp;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button promouvoir;
    @FXML
    private Button annulpromo;
    @FXML
    private Text valeurtxt;
    @FXML
    private DatePicker datefin;
    @FXML
    private Text datetxt;
    @FXML
    private TextField red;
    @FXML
    private Text promotext;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
                 UserSession session = UserSession.getInstace("", "");

              ProduitService pr = new  ProduitService();
              PromotionService pms = new PromotionService();
                Produit prod = pr.FindProduit(StoreController.getIdprod());
                
                 File file = new File("C:/Users/HP/Desktop/Eplants/src/images/"+prod.getImg());
           
          Image image = new Image(file.toURI().toString());
        imgp.setImage(image);
           nomp.setText(prod.getNom());
           descp.setText(prod.getDescription());
           prixp.setText(""+prod.getPrix());
           stockp.setText(""+prod.getStock());
           if("client".equalsIgnoreCase(session.getRole())){
                  descp.setDisable(true);
                  prixp.setDisable(true);
                  stockp.setDisable(true);
                  ap.getChildren().remove(modifp);
                  ap.getChildren().remove(supprimp);
                  ap.getChildren().remove(promouvoir);
                  ap.getChildren().remove(annulpromo);
                  ap.getChildren().remove(datefin);
                  ap.getChildren().remove(datetxt);
                  ap.getChildren().remove(valeurtxt);
                  ap.getChildren().remove(red);
              }
           
           if(pms.searchPromotion(prod.getId())==null){
            annulpromo.setVisible(false);
            promotext.setVisible(false);
           }
           
           if(pms.searchPromotion(prod.getId())!=null){
            promouvoir.setVisible(false);
            datefin.setVisible(false);
            datetxt.setVisible(false);
            valeurtxt.setVisible(false);
            red.setVisible(false);
            promotext.setText(promotext.getText()+pms.searchPromotion(prod.getId()).getReduction()+"%");
           }
           
           annulpromo.setOnAction((event) -> {
                int oldvalue = pms.searchPromotion(prod.getId()).getReduction();
               Promotion prom = new Promotion();
                prom.setReduction(oldvalue);
                prom.setProduit_id(prod.getId());
                prom.setId(pms.searchPromotion(prod.getId()).getId());
                
                pms.supprimerPromotion(prom);
                 Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Detailsproducts.fxml"));
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
        alert.setContentText("Promotion annulée");
        alert.show();
           });
           
           promouvoir.setOnAction((event) -> {
               
               Promotion promo = new Promotion();
               try {
                    promo.setDate_fin(""+datefin.getValue());
               
                promo.setReduction(Integer.valueOf(red.getText()));
                promo.setProduit_id(prod.getId());
                promo.setDescription("Promotion du produit : "+prod.getNom());
                  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 Date date1 = null;
                 Date date2 = null;
                     try {
                          date1 = sdf.parse(promo.getDate_fin());
                          date2 = sdf.parse(sdf.format(new Date()));
                     } catch (ParseException ex) {
                         Logger.getLogger(CommunauteController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     
                 if(promo.getReduction()>100 || (""+datefin.getValue()).equalsIgnoreCase("") || date2.compareTo(date1)>0){
                     Alert alert = new Alert(Alert.AlertType.WARNING);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Promotion maximale de 100 % ou date de fin invalide!");
        alert.show();

              }
                 else{
                     pms.insertPromotion(promo);
                 Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Detailsproducts.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                
                        } catch (IOException ex) {
                            Logger.getLogger(DetailsproductsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Produit en promotion!");
        alert.show();
                 }
               } catch (Exception e) {
                   Alert alert = new Alert(Alert.AlertType.WARNING);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir une reduction correcte!");
        alert.show();
               }
               
           });
           
           supprimp.setOnAction((event) -> {
               if(pms.searchPromotion(prod.getId())==null){
        pr.deleteById(prod);
        
         Parent page2;
           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Store.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                
                        } catch (IOException ex) {
                            Logger.getLogger(DetailsproductsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Produit supprimé");
        alert.show();
               }
                else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez d'abord annuler la promotion");
        alert.show();
               } 
           });
           
           modifp.setOnAction((event1) -> {
               if(pms.searchPromotion(prod.getId())==null){
               Produit produ= new Produit();
               try {
                   produ.setId(prod.getId());
                    produ.setPrix(Float.valueOf(prixp.getText()));
               produ.setDescription(descp.getText());
               produ.setStock(Integer.valueOf(stockp.getText()));
               pr.updateProd(produ);
                     Parent page2;
           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Detailsproducts.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                
                        } catch (IOException ex) {
                            Logger.getLogger(DetailsproductsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Produit modifé");
        alert.show();
               } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Stock ou prix non valide");
        alert.show();
               }
           }
               else{
                 Alert alert = new Alert(Alert.AlertType.ERROR);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez d'abord annuler la promotion");
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
