/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.config.ConnexionSingleton;
import eplant.config.UserSession;
import eplant.entities.Produit;
import eplant.services.ProduitService;
import eplant.entities.Panier;
import eplant.entities.Personne;
import eplant.services.PanierService;
import eplant.services.PersonneService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartFrame;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.data.category.CategoryDataset;
//import org.jfree.data.jdbc.JDBCCategoryDataset;
//import DataSource.*;




/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class StoreController implements Initializable {

    
    @FXML
    private ScrollPane scrollprod;
    @FXML
    private Button panier;
    @FXML
    private AnchorPane sc;
    @FXML
    private Button stat;
    
    private static int idprod;

    public static int getIdprod() {
        return idprod;
    }

    public static void setIdprod(int idprod) {
        StoreController.idprod = idprod;
    }
    @FXML
    private Button ajoutprod;
    @FXML
    private Button statprod;
    @FXML
    private Button back;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         UserSession session = UserSession.getInstace("", "");
        PersonneService pers = new PersonneService();
        ProduitService ps = new  ProduitService();
        PanierService pas =  PanierService.getInstance();
        GridPane Prodgrid = new GridPane();
        Prodgrid.setVgap(35);
        Prodgrid.setHgap(35);
          // TODO
           if("admin".equalsIgnoreCase(session.getRole())){
           sc.getChildren().remove(panier);
           }
           if("client".equalsIgnoreCase(session.getRole())){
           sc.getChildren().remove(stat);
           sc.getChildren().remove(ajoutprod);
           }
           
           stat.setOnAction((event8) -> {
                try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/eplant/view/Stat.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event8.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(StoreController.class.getName()).log(Level.SEVERE, null, ex);
            }
           });
        panier.setOnAction(event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/eplant/view/Panier.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(StoreController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        List<Produit> Produits = ps.read();
        int row = 0;
        int column =0;
        int i =0;
        for(Produit p : Produits){
            
        Pane produit = new Pane();
        
         File file = new File("C:/Users/HP/Desktop/Eplants/src/images/"+p.getImg());
              ImageView imag = new ImageView();
              imag.setFitHeight(80);
              imag.setFitWidth(150);
          Image image = new Image(file.toURI().toString());
        imag.setImage(image);
        
        Text ProdName = new Text(p.getNom());
        Button panbutton = new Button("Ajouter au panier");
        Text Quantitel = new Text("Quantite");
        TextField quant = new TextField("1");
        quant.setMaxWidth(40);
         if("client".equalsIgnoreCase(session.getRole())){
                 
        produit.getChildren().addAll(imag,ProdName,panbutton,Quantitel,quant);
         }
          if("admin".equalsIgnoreCase(session.getRole())){
                 
        produit.getChildren().addAll(imag,ProdName);
         }
         imag.setLayoutX(35);
             imag.setLayoutY(20);
             ProdName.setLayoutX(35);
             ProdName.setLayoutY(110);
             panbutton.setLayoutY(160);
             panbutton.setLayoutX(35);
              Quantitel.setLayoutY(145);
             Quantitel.setLayoutX(35);
             quant.setLayoutY(130);
             quant.setLayoutX(80);
                  
             imag.setOnMouseReleased((event4) -> {
                 StoreController.setIdprod(p.getId());
                 Parent page2;
                        try {
                            
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Detailsproducts.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event4.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
             });
             panbutton.setOnAction((event) -> {
                 
                 try{
                   
                Panier pan = new Panier(pers.personbyEmaill(session.getUserName()).getId(), p.getId(), quant.getText().equalsIgnoreCase("")?1:Integer.valueOf(quant.getText()), "");
                
                 if(!verifqt(p, Integer.valueOf(quant.getText())))
                 {
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Quantité inssufisante");
        alert.show();
                 }
                 
                 else if(!pas.checkproduit(pan))
                 { pas.ajouterPanier(pan);
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Produit ajouté au panier avec succés!");
        alert.show();}
                 else
                 { pas.updatepanier(pan);
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("quantité modifié avec succés!");
        alert.show();
                 }
                     
             }
                 
                    catch(Exception ex){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information ");
        alert.setHeaderText(null);
        alert.setContentText("Saisir un entier correct!");
        alert.show();
               }
             }
                  
                 );
             
              if(column == 3){
             row +=1;
             column=0;
          GridPane.setConstraints(produit, column,row);
         Prodgrid.getChildren().add(produit);
         column++;
         }
         
         else{
             
         GridPane.setConstraints(produit,column ,row);
         Prodgrid.getChildren().add(produit);
         column +=1;
         }
         i++;

        }
        
        scrollprod.setContent(Prodgrid);
                             // Always show vertical scroll bar
        scrollprod.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        // Horizontal scroll bar is only displayed when needed
        scrollprod.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
       ajoutprod.setOnAction((event5) -> {
           Parent page2;
           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Ajoutproduit.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event5.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                 
       });
       
       statprod.setOnAction((event3) -> {
            Parent page2;
           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Statsprod.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event3.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
    
    
    public boolean verifqt(Produit p,int x){
        if(p.getStock()<x)
        {return false;}
        
        
        return true;
}
     public boolean validqte(String qte){
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(qte);
        if(m.find() && m.group().equals(qte))
        
        {
            return true;}
        else {
            
            return false;}
        
}}