/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.entities.ListData;
import eplant.entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AfficherReclamationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Reclamation> reclamTable;
    @FXML
    private TableColumn<Reclamation, String> EmailColonne;
     @FXML
    private TableColumn<Reclamation, String> SujetColonne;
      @FXML
    private TableColumn<Reclamation, String> ContenueColonne;
    @FXML
    private TableColumn<Reclamation, String> CategorieColonne;

    @FXML
    private Label CategorieLabel;
    @FXML
    private Label EmailLabel;
   
    @FXML
    private Label ContenueLabel;
    @FXML
    private Label SujetLabel;

    
     @FXML
    private TextField EmailTF;
    @FXML
    private TextField CategorieTF;
    @FXML
    private TextField ContenueTF;
    @FXML
    private TextField SujetTF;
    
    private ListData listdata = new ListData();

    @FXML
    private Button btn_pie;
    @FXML
    private Button btn_mail;
    @FXML
    private Button btn_avis;
    @FXML
    private Button backbutton;
    @FXML
    private TextField searchreclam;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        reclamTable.setItems(listdata.getReclam());
        EmailColonne.setCellValueFactory(cell -> cell.
                getValue().getEmailProperty());
        SujetColonne.setCellValueFactory(cell -> cell.
                getValue().getSujetProperty());
        ContenueColonne.setCellValueFactory(cell -> cell.
                getValue().getContenueProperty());
        CategorieColonne.setCellValueFactory(cell -> cell.
                getValue().getCategorieProperty());
        
          
        reclamTable.setOnMouseClicked(event->{
        CategorieTF.setText(listdata.getReclam()
                .get(reclamTable.getSelectionModel().getSelectedIndex())
                .getCategorie());
        EmailTF.setText(listdata.getReclam()
                .get( reclamTable.getSelectionModel().getSelectedIndex())
                .getEmail());
        SujetTF.setText(listdata.getReclam()
                .get(reclamTable.getSelectionModel().getSelectedIndex())
                .getSujet());
        ContenueTF.setText(listdata.getReclam()
                .get(reclamTable.getSelectionModel().getSelectedIndex())
                .getContenue());
        
       
    });
       
        searchreclam.setOnKeyReleased((event) -> {
            listdata.setEmail(searchreclam.getText());
             reclamTable.setItems(listdata.getReclam());
        EmailColonne.setCellValueFactory(cell -> cell.
                getValue().getEmailProperty());
        SujetColonne.setCellValueFactory(cell -> cell.
                getValue().getSujetProperty());
        ContenueColonne.setCellValueFactory(cell -> cell.
                getValue().getContenueProperty());
        CategorieColonne.setCellValueFactory(cell -> cell.
                getValue().getCategorieProperty());
        });
        //Redirection vers l'interface PieChart
        btn_pie.setOnAction(event->{
            try {
                System.out.println("testttttttttttttt");
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/eplant/view/PieChartView.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage= new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btn_avis.setOnAction(event->{
            try {
                System.out.println("testttttttttttttt");
                Parent pagePieChart=FXMLLoader.load(getClass().getResource("/eplant/view/AfficherAvis.fxml"));
                Scene scene=new Scene(pagePieChart);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
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
        
    }

}
