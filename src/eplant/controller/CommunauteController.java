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
import java.util.Calendar;
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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateTimeStringConverter;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CommunauteController implements Initializable {

     @FXML
    private PieChart pieChart;
     
    @FXML
    private AnchorPane ap2;
    @FXML
    private AnchorPane apcom;
    @FXML
    private Tab ajouter;
     @FXML
    private TabPane tab;
      @FXML
    private ScrollPane scrollEvent;
    @FXML
    private Pane listpane;
    @FXML
    private TableView<Personne> table;
     @FXML
    private TableColumn<Personne, String> Nomp;
    @FXML
    private TableColumn<Personne, String> Prenomp;
    @FXML
    private Button ajout;
      @FXML
    private Button importer;
       @FXML
    private Button pdffile;
       @FXML
    private ImageView img2;
    
     @FXML
    private DatePicker ldate;
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
    private ComboBox comotype;
    
        
    @FXML
    private Text affichetxt;
    @FXML
    private Text title3;
    
    
   
    
       private ListEvents listdata = new ListEvents();

     UserSession session = UserSession.getInstace("", "");
         EvenementService s1 = new EvenementService();
        PersonneService p1 = new PersonneService();
    @FXML
    private AnchorPane ap21;
    @FXML
    private Button backbutton;
    @FXML
    private ComboBox comotri;
    
    void affichgridpane(){
      
        
         GridPane EventGrid = new GridPane();
         EventGrid.setPadding(new Insets(30,30,45,45));
        EventGrid.setVgap(45);
         EventGrid.setHgap(45);
          int row = 0;
             int column =0;
             int k=0;
         for(Evenement e :listdata.getEvents1()){
             //Evenement e = listdata.getEvents1().get(k);
             final int indice= k;
            Pane event= new Pane();
            //Description
             Label EventName= new Label(e.getDescription());
             //EventName.setTextFill(Color.DARKGREEN);
             //Affiche
              File file = new File("C:/Users/HP/Documents/NetBeansProjects/Eplants/src/images/"+e.getAffiche());
              ImageView imag = new ImageView();
              imag.setFitHeight(80);
              imag.setFitWidth(150);
          Image image = new Image(file.toURI().toString());
        imag.setImage(image);
            
             event.getChildren().addAll(imag,EventName);
         
             EventName.setLayoutY(85);
             //EventName.setLayoutX(35);
           event.setOnMouseReleased((event6) -> {
               try {
                                 UserSession.getInstace("","").setIndevent(indice);

                Parent page1 = FXMLLoader.load(getClass().getResource("/eplant/view/detailsevents.fxml"));
              /*  FXMLLoader loader = new FXMLLoader(getClass().getResource("/eplant/view/detailsevents.fxml"));
                   DetailseventsController cont = (DetailseventsController)loader.getController();
                   cont.setSendEvent(e);*/
                Scene scene = new Scene(page1);

                
                Stage stage = new Stage();
        	stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CommunauteController.class.getName()).log(Level.SEVERE, null, ex);
            }

           });
         if(column == 3){
             row +=1;
             column=0;
          GridPane.setConstraints(event, column,row);
         EventGrid.getChildren().add(event);
         column +=1;
         }
         
         else{
             
         GridPane.setConstraints(event,column ,row);
         EventGrid.getChildren().add(event);
         column +=1;
         }
         k++;
         imag.setOnMouseClicked((event8) -> {
                   ObservableList<Personne> participants= FXCollections.observableArrayList();
                     List<Participation> parti = s1.ListParticipEvents(e.getId());
                   participants= p1.ListParticipants(parti);
                   
                    table.setItems(participants);
       Nomp.setCellValueFactory(cell -> cell.
                getValue().getNomProperty());
        Prenomp.setCellValueFactory(cell -> cell.
                getValue().getPrenomProperty());
        
         pdffile.setOnAction((event6) -> {
              ObservableList<Personne> persons= FXCollections.observableArrayList();

         persons = p1.getPersons("",e.getId());
                     table.setItems(persons);
        String contenu = "";
        for(int j=0;j<table.getItems().size();j++){
            int ind = j+1;
          contenu += "\n"+ind+" - "+table.getItems().get(j).getNom()+" "+table.getItems().get(j).getPrenom();
        }
        s1.generatepdfparts(contenu,e.getDescription(),e.getDate_event(),e.getId());
        
          Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("PDF généré avec succès");
        alert.show();
         });
         
         });
         
          chercher.setOnKeyReleased((event5) -> {
            ObservableList<Personne> persons= FXCollections.observableArrayList();
            persons = p1.getPersons(chercher.getText(),e.getId());
                     table.setItems(persons);
       Nomp.setCellValueFactory(cell -> cell.
                getValue().getNomProperty());
        Prenomp.setCellValueFactory(cell -> cell.
                getValue().getPrenomProperty());
                  });
         }
         Pane imgpane = new Pane();
         File file2 = new File("C:/Users/HP/Documents/NetBeansProjects/Eplants/src/images/makro-rastenie-list-razmytost-macro-plant-leaf-bokeh.jpg");
        // imgpane.getChildren().add(new ImageView(new Image(file2.toURI().toString())));
         imgpane.getChildren().add(EventGrid);
         
         /*EventGrid.setLayoutX(50);
          EventGrid.setLayoutY(50);*/
         //  apcom.getChildren().add(EventGrid);
                             scrollEvent.setContent(imgpane);
                             // Always show vertical scroll bar
        scrollEvent.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        // Horizontal scroll bar is only displayed when needed
        scrollEvent.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
       
        
        
    
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Evenement next = s1.NextEvent();
        int jj= 0;
          SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                 Date dateevent = null;
                 Date datenow = null;
                     try {
                          dateevent = sdf1.parse(next.getDate_event());
                          datenow = sdf1.parse(sdf1.format(new Date()));
                          Calendar cal1 = Calendar.getInstance();
                          Calendar cal2 = Calendar.getInstance();
                          cal1.setTime(dateevent);
                          cal2.setTime(datenow);
                          jj = cal1.get(Calendar.DAY_OF_YEAR)-cal2.get(Calendar.DAY_OF_YEAR);
                     } catch (ParseException ex) {
                         Logger.getLogger(CommunauteController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 
         Notifications.create().title("Notification").
                                text("Prochain évenement: "+ next.getDescription()+"\n"+"JJ-"+jj)
                 .position(Pos.TOP_CENTER).showInformation();
        
        comotype.setItems(FXCollections 
                                 .observableArrayList("Rencontre","Apprentissage","Exposition"));
   //     description.getText().
   affichgridpane();
   
     comotri.setItems(FXCollections 
                                 .observableArrayList("Rencontre","Apprentissage","Exposition"));
   comotri.setOnAction((event) -> {
       listdata.setSearch(comotri.getValue().toString());
                   affichgridpane();

   });
        apcom.setOnMouseReleased((event10) -> {
            affichgridpane();
        });
         search.setOnKeyReleased((event) -> {
             listdata.setSearch(search.getText());
            affichgridpane();
               
        });
        
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
             
         
        tab.setOnMouseClicked((event) -> {
               
             ObservableList<PieChart.Data> list=FXCollections.observableArrayList();
            int i=0;
        List<Evenement> events = s1.EventsByPopularity();
       
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
        
        
         ajout.setOnAction((event) -> {
                 
                 if((!ldate.getEditor().getText().equalsIgnoreCase("") || !(""+ldate.getValue()).equalsIgnoreCase(""))   && !lieu.getText().equalsIgnoreCase("") && !lieu.getText().equalsIgnoreCase("") && !(""+comotype.getValue()).equalsIgnoreCase("") && !affiche.getText().equalsIgnoreCase("")&& !description.getText().equalsIgnoreCase("")){
                 Evenement e = new Evenement((!"".equalsIgnoreCase(ldate.getEditor().getText()))?""+ldate.getValue():ldate.getPromptText(), ""+comotype.getValue(), 0,lieu.getText() , description.getText(), affiche.getText());
                 
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
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Communaute1.fxml"));
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
        
       
        if("client".equalsIgnoreCase(session.getRole())){
                  listpane.setVisible(false);
                  ajouter.setDisable(false);
                 // tab.getChildrenUnmodifiable().remove(ajouter);
                  ajouter.setContent(null);
                  ajouter.setDisable(true);
                  
              }
        
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
        
        /*
        
        
        
        
        
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
            persons = p1.getPersons(chercher.getText(),listdata.getEvents1()
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
              });*/
                  }
 
}
