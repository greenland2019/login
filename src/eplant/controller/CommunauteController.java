/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.config.UserSession;
import java.net.URL;
import eplant.entities.Evenement;
import eplant.entities.ListEvents;
import eplant.entities.Participation;
import eplant.entities.Personne;
import eplant.services.EvenementService;
import eplant.services.PersonneService;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateTimeStringConverter;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CommunauteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private PieChart pieChart;
     
    @FXML
    private AnchorPane ap;
    @FXML
    private AnchorPane ap1;
    @FXML
    private AnchorPane ap2;
    @FXML
    private Tab title2;
     @FXML
    private TabPane tab;
    @FXML
    private Pane img;
    @FXML
    private TableView<Personne> table;
     @FXML
    private TableColumn<Personne, String> Nomp;
    @FXML
    private TableColumn<Personne, String> Prenomp;
        @FXML
    private TableView<Evenement> EventTable;
    @FXML
    private TableColumn<Evenement, String> Nom;
    @FXML
    private TableColumn<Evenement, String> Date;
    
      @FXML
    private Button logout;
     @FXML
    private Button supprim;
    @FXML
    private Button modif;
    @FXML
    private Button ajout;
     @FXML
    private Button part;
      @FXML
    private Button importer;
       @FXML
    private Button pdffile;
       @FXML
    private ImageView imageview;
       @FXML
    private ImageView img2;
    
     @FXML
    private DatePicker ldate;
       @FXML
    private TextField type;
         @FXML
    private TextField lieu;
           @FXML
    private TextField affiche;
             @FXML
    private TextField description;
                     @FXML
    private TextField search;
                            @FXML
    private TextField chercher;
    
    @FXML
    private Text affichetxt;
    @FXML
    private Text title3;
    
       private ListEvents listdata = new ListEvents();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         EvenementService s1 = new EvenementService();
        PersonneService p1 = new PersonneService();
        
        tab.setOnMouseClicked((event) -> {
               
             ObservableList<PieChart.Data> list=FXCollections.observableArrayList();
            int i=0;
        List<Evenement> events=s1.EventsByPopularity();
       
        for(Evenement e:events) {
            if(i<6){
            list.addAll(
                new PieChart.Data(e.getDescription()+"\n"+e.getDate_event(), e.getNb_participants()) 
                 
        );}
               i++;
               
        }
         pieChart.setAnimated(true);
        pieChart.setData(list);
        
        }); 
      
      
       
        
        search.setPromptText("Chercher");
        chercher.setPromptText("Chercher");
        
        UserSession session = UserSession.getInstace("", "");
        
      
        
       EventTable.setItems(listdata.getEvents());
       Nom.setCellValueFactory(cell -> cell.
                getValue().getNomProperty());
        Date.setCellValueFactory(cell -> cell.
                getValue().getDateProperty());
        
        search.setOnKeyReleased((event) -> {
            listdata.setSearch(search.getText());
             EventTable.setItems(listdata.getEvents1());
       Nom.setCellValueFactory(cell -> cell.
                getValue().getNomProperty());
        Date.setCellValueFactory(cell -> cell.
                getValue().getDateProperty());
        });
        
         
        
          EventTable.setOnMouseClicked(event->{
              
               ObservableList<PieChart.Data> list=FXCollections.
            observableArrayList();
            int i=0;
        List<Evenement> events=s1.EventsByPopularity();
       
        for(Evenement e:events) {
            if(i<6){
            list.addAll(
                new PieChart.Data(e.getDescription()+"\n"+e.getDate_event(), e.getNb_participants()) 
                 
        );}
               i++;
               
        }
         pieChart.setAnimated(true);
        pieChart.setData(list);
        
              
              
        ldate.setPromptText(String.valueOf(listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getDate_event()));
       affiche.setText(String.valueOf(listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getAffiche()));
        type.setText(String.valueOf(listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getType()));
        lieu.setText(String.valueOf(listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getLieu()));
        description.setText(String.valueOf(listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getDescription()));
        
             File file = new File("C:/Users/HP/Documents/NetBeansProjects/Eplants/src/images/"+listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getAffiche());
          Image image = new Image(file.toURI().toString());
        imageview.setImage(image);
           
         supprim.setOnAction((event1) -> {
                 
              
                 Evenement e = new Evenement();
                 e.setId(listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getId());
                s1.delete(e);
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Evenement supprimé");
        alert.show();
                 Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Communaute.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  
             });
              
                 if(!s1.checkParticipation(listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getId(),p1.SearchByMail(session.getUserName()).getId())){
                   part.setText("Participer");
                 part.setOnAction((event2) -> {
                     s1.ajoutParticipation(new Participation(p1.SearchByMail(session.getUserName()).getId(),listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getId()));
                       Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Communaute.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                 });
                 }
                 else if(s1.checkParticipation(listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getId(),p1.SearchByMail(session.getUserName()).getId()))
                 {
                 
                  part.setText("Renoncer");
                 part.setOnAction((event2) -> {
                     s1.deleteParticipation(new Participation(p1.SearchByMail(session.getUserName()).getId(),listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getId()));
                       Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Communaute.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                 });
                 }
               
                  if("admin".equalsIgnoreCase(session.getRole()))
               {
                   table.setVisible(true);
                  tab.setOnMouseClicked((event3) -> {
                   ObservableList<Personne> participants= FXCollections.observableArrayList();
                     List<Participation> parti = s1.ListParticipEvents(listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getId());
                   participants= p1.ListParticipants(parti);
                   
                    table.setItems(participants);
       Nomp.setCellValueFactory(cell -> cell.
                getValue().getNomProperty());
        Prenomp.setCellValueFactory(cell -> cell.
                getValue().getPrenomProperty());
        
         pdffile.setOnAction((event6) -> {
          
        String contenu = "";
        for(int j=0;j<table.getItems().size();j++){
            int ind = j+1;
          contenu += "\n"+ind+" - "+table.getItems().get(j).getNom()+" "+table.getItems().get(j).getPrenom();
        }
        s1.generatepdfparts(contenu, listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getDescription(),listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getDate_event(),listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getId());
        
          Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("PDF généré avec succès");
        alert.show();
        });
        
        chercher.setOnKeyReleased((event5) -> {
            ObservableList<Personne> persons= FXCollections.observableArrayList();
            persons = p1.getPersons(chercher.getText(),chercher.getText(),listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getId());
                     table.setItems(persons);
       Nomp.setCellValueFactory(cell -> cell.
                getValue().getNomProperty());
        Prenomp.setCellValueFactory(cell -> cell.
                getValue().getPrenomProperty());
                  });
                  });
                  
                  
                 
               }
            });
               
               
                
               if("client".equalsIgnoreCase(session.getRole()))
               {
                ap1.getChildren().remove(affiche);
                ap1.getChildren().remove(affichetxt);
                ap1.getChildren().remove(ajout);
                ap1.getChildren().remove(modif);
                ap1.getChildren().remove(supprim);
                ap1.getChildren().remove(importer);
                ap2.getChildren().remove(chercher);
                ap2.getChildren().remove(img2);
                 ap2.getChildren().remove(pdffile);
                ap2.getChildren().remove(title3);
                table.setVisible(false);
                ldate.setEditable(false);
                type.setEditable(false);
                lieu.setEditable(false);
                description.setEditable(false);
                
               }
               if("admin".equalsIgnoreCase(session.getRole()))
               {
                ap1.getChildren().remove(part);
                
               }
             
             importer.setOnAction((event) -> {
                
                 FileChooser f = new FileChooser();
                f.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image", "*.*")
            );
                
                File fi = f.showOpenDialog(null);
                
                if(fi!=null){
                affiche.setText(fi.getName());
                }
             });
             
             ajout.setOnAction((event) -> {
                 
                 if((!ldate.getEditor().getText().equalsIgnoreCase("") || !(""+ldate.getValue()).equalsIgnoreCase(""))   && !lieu.getText().equalsIgnoreCase("") && !lieu.getText().equalsIgnoreCase("") && !type.getText().equalsIgnoreCase("") && !affiche.getText().equalsIgnoreCase("")&& !description.getText().equalsIgnoreCase("")){
                 Evenement e = new Evenement((!"".equalsIgnoreCase(ldate.getEditor().getText()))?""+ldate.getValue():ldate.getPromptText(), type.getText(), 0,lieu.getText() , description.getText(), affiche.getText());
                 
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 Date date1 = null;
                 Date date2 = null;
                     try {
                          date1 = sdf.parse(e.getDate_event());
                          date2 = sdf.parse(sdf.format(new Date()));
                     } catch (ParseException ex) {
                         Logger.getLogger(CommunauteController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 
                 if(date2.compareTo(date1)<0){
                 s1.insert(e);
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Evenement ajouté");
        alert.show();
                   Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Communaute.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
           
                 }
                 else 
                        {
                  Alert alert = new Alert(Alert.AlertType.ERROR);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La date doit etre superieure à la date d'aujourdhui");
        alert.show();

                 }
                 }
                 else
                 {
                  Alert alert = new Alert(Alert.AlertType.WARNING);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs!");
        alert.show();

                 }
             });
             
              modif.setOnAction((event) -> {
                 
                 if((!ldate.getEditor().getText().equalsIgnoreCase("") || !(""+ldate.getValue()).equalsIgnoreCase(""))   && !lieu.getText().equalsIgnoreCase("") && !lieu.getText().equalsIgnoreCase("") && !type.getText().equalsIgnoreCase("") && !affiche.getText().equalsIgnoreCase("")&& !description.getText().equalsIgnoreCase("")){
                 Evenement e = new Evenement((!"".equalsIgnoreCase(ldate.getEditor().getText()))?""+ldate.getValue():ldate.getPromptText(), type.getText(), 0,lieu.getText() , description.getText(), affiche.getText());
                 e.setId(listdata.getEvents1()
                .get(EventTable.getSelectionModel().getSelectedIndex())
                .getId());
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 Date date1 = null;
                 Date date2 = null;
                     try {
                          date1 = sdf.parse(e.getDate_event());
                          date2 = sdf.parse(sdf.format(new Date()));
                     } catch (ParseException ex) {
                         Logger.getLogger(CommunauteController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 
                 if(date2.compareTo(date1)<0){
                 s1.update(e);
                 
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Evenement modifié");
        alert.show();

                   Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Communaute.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
           
                 }
                 else 
                        {
                  Alert alert = new Alert(Alert.AlertType.ERROR);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La date doit etre superieure à la date d'aujourdhui");
        alert.show();

                 }
                 }
                 else
                 {
                  Alert alert = new Alert(Alert.AlertType.WARNING);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs!");
        alert.show();

                 }
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
