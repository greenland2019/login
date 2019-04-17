/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import com.jfoenix.controls.JFXButton;
import eplant.entities.ListData_1;
import eplant.services.AvisDao;
import eplant.entities.Avis;
import java.sql.SQLException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AfficherAvisController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Avis> avisTable;
    @FXML
    private TableColumn<Avis, String> EtatColonne;
    @FXML
    private TableColumn<Avis, String> CommentaireColonne;


    private ListData_1 listdata = new ListData_1();

    @FXML
    private Button btn_pie;
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton quiz;
    @FXML
    private JFXButton rating;
    @FXML
    private JFXButton reclamation;
    @FXML
    private Button btn_delete;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        avisTable.setItems(listdata.getAvis());
        EtatColonne.setCellValueFactory(cell -> cell.
                getValue().getEtatProperty());
        CommentaireColonne.setCellValueFactory(cell -> cell.
                getValue().getCommentaireProperty());

        //Redirection vers l'interface PieChart
        btn_pie.setOnAction((ActionEvent event) -> {
            try {
                //System.out.println("testttttttttttttt");
                Parent pagePieChart = FXMLLoader.load(getClass().getResource("/eplant/view/PieChartView_1.fxml"));
                Scene scene = new Scene(pagePieChart);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AfficherAvisController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    @FXML
    public void supprimer(ActionEvent event) throws SQLException {

        if (avisTable.getSelectionModel().getSelectedItem() != null) {

            // suppFor.setDisable(false);
            //Alert Delete Partner :
            Alert deletePartnerAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deletePartnerAlert.setTitle("Delete Partner");
            deletePartnerAlert.setHeaderText(null);
            deletePartnerAlert.setContentText("Are you sure want to delete this Rate ?");

            Optional<ButtonType> optionDeletePartnerAlert = deletePartnerAlert.showAndWait();
            if (optionDeletePartnerAlert.get() == ButtonType.OK) {
                // System.out.println("salut ichrak");
                int index = avisTable.getSelectionModel().getSelectedItem().getId();
                //AvisDao.supprimer(avisTable.getSelectionModel().getSelectedItem().getId());
                //System.out.println("tessssssssst"+index);
                AvisDao a = AvisDao.getInstance();
                a.supprimer(index);
              
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/eplant/view/AfficherAvis.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilreclamController.class.getName()).log(Level.SEVERE, null, ex);
            }

            } else if (optionDeletePartnerAlert.get() == ButtonType.CANCEL) {

            }

            //Alert Delete Partner !
        } else {
            //Alert Select Partner :
            Alert selectPartnerAlert = new Alert(Alert.AlertType.WARNING);
            selectPartnerAlert.setTitle("Select Avis");
            selectPartnerAlert.setHeaderText(null);
            selectPartnerAlert.setContentText("You need to select Avis first!");
            selectPartnerAlert.showAndWait();
            //Alert Select Partner !
        }
        // avisTable.setItems(AvisDao.displayAll());

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
}
