/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.config.UserSession;
import eplant.entities.Evenement;
import eplant.entities.ListEvents;
import eplant.entities.Participation;
import eplant.entities.Personne;
import eplant.services.EvenementService;
import eplant.services.PersonneService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DetailseventsController implements Initializable {
 
    private Stage stage;
    
    @FXML
    private TextField desc;

   
           private ListEvents listdata = new ListEvents();
            UserSession session = UserSession.getInstace("", "");
            EvenementService s1 = new EvenementService();
        PersonneService p1 = new PersonneService();
    @FXML
    private ImageView imagevent;
    @FXML
    private DatePicker date;
    @FXML
    private TextField type;
    @FXML
    private TextField lieu;
    @FXML
    private TextField imgtext;
    @FXML
    private Button imgbut;
    @FXML
    private Button deletbut;
    @FXML
    private Button modifbut;
    @FXML
    private Button partbutton;
    @FXML
    private AnchorPane ap;
    @FXML
    private Text imgtxt;
    @FXML
    private Button pdfbut;
            

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Evenement e = listdata.getEvents1().get(session.getIndevent());
        desc.setText(e.getDescription());
        date.setPromptText(e.getDate_event());
        type.setText(e.getType());
        lieu.setText(e.getLieu());
        imgtext.setText(e.getAffiche());
         File file = new File("C:/Users/HP/Desktop/Eplants/src/images/"+e.getAffiche());
          Image image = new Image(file.toURI().toString());
        imagevent.setImage(image);
        
        if("client".equalsIgnoreCase(session.getRole())){
        ap.getChildren().remove(deletbut);
        ap.getChildren().remove(modifbut);
        ap.getChildren().remove(imgbut);
        ap.getChildren().remove(imgtxt);
        ap.getChildren().remove(imgtext);
        ap.getChildren().remove(pdfbut);
        desc.setEditable(false);
        date.setEditable(false);
        type.setEditable(false);
        lieu.setEditable(false);
        }
        
         if("admin".equalsIgnoreCase(session.getRole())){
        ap.getChildren().remove(partbutton);
        }
         
           imgbut.setOnAction((event) -> {
                
                 FileChooser f = new FileChooser();
                f.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image", "*.*")
            );
                
                File fi = f.showOpenDialog(null);
                
                if(fi!=null){
                imgtext.setText(fi.getName());
                }
             });
             
            deletbut.setOnAction((event1) -> {
                 
              
                 Evenement ev = new Evenement();
                 ev.setId(e.getId());
                s1.delete(ev);
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Evenement supprimé");
        alert.show();
                /* Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Communaute1.fxml"));
                            
                             Scene scene = new Scene(page2);
                 stage = (Stage) ((Node) event1.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(DetailseventsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  */
             });
         
           if(!s1.checkParticipation(e.getId(),p1.SearchByMail(session.getUserName()).getId())){
                   partbutton.setText("Participer");
                 partbutton.setOnAction((event2) -> {
                     s1.ajoutParticipation(new Participation(p1.SearchByMail(session.getUserName()).getId(),e.getId()));
                       Parent page2;

           try {
               session.setIndevent(session.getIndevent());
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/detailsevents.fxml"));
                             Scene scene = new Scene(page2);
                 stage = (Stage) ((Node) event2.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                 });
                 }
                 else if(s1.checkParticipation(e.getId(),p1.SearchByMail(session.getUserName()).getId()))
                 {
                 
                  partbutton.setText("Renoncer");
                 partbutton.setOnAction((event2) -> {
                     s1.deleteParticipation(new Participation(p1.SearchByMail(session.getUserName()).getId(),e.getId()));
                       Parent page2;

            /*try {
                              session.setIndevent(listdata.getEvents1().indexOf(e));

                           page2 = FXMLLoader.load(getClass().getResource("/eplant/view/detailsevents.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event2.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }*/
                 });
    }    
    
             
                     List<Participation> parti = s1.ListParticipEvents(e.getId());
                      final List<Personne> participants= p1.ListParticipants1(parti);
                   //participants= p1.ListParticipants1(parti);
                  //final int partsize = participants.size();
        
            pdfbut.setOnAction((event6) -> {
          
        String contenu = "";
        
        for(int j=0;j<participants.size();j++){
            int ind = j+1;
          contenu += "\n"+ind+" - "+participants.get(j).getNom()+" "+participants.get(j).getPrenom();
        }
        s1.generatepdfparts(contenu, e.getDescription(),e.getDate_event(),e.getId());
        
          Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("PDF généré avec succès");
        alert.show();
        });
            modifbut.setOnAction((event) -> {
                 
                 if((!date.getEditor().getText().equalsIgnoreCase("") || !(""+date.getValue()).equalsIgnoreCase(""))   && !lieu.getText().equalsIgnoreCase("") && !lieu.getText().equalsIgnoreCase("") && !type.getText().equalsIgnoreCase("") && !imgtext.getText().equalsIgnoreCase("")&& !desc.getText().equalsIgnoreCase("")){
                 Evenement ev = new Evenement((!"".equalsIgnoreCase(date.getEditor().getText()))?""+date.getValue():date.getPromptText(), type.getText(), 0,lieu.getText() , desc.getText(), imgtext.getText());
                 ev.setId(e.getId());
                 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                 Date date1 = null;
                 Date date2 = null;
                     try {
                          date1 = sdf.parse(ev.getDate_event());
                          date2 = sdf.parse(sdf.format(new Date()));
                     } catch (ParseException ex) {
                         Logger.getLogger(DetailseventsController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 
                 if(date2.compareTo(date1)<0){
                 s1.update(ev);
                 
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Evenement modifié");
        alert.show();

                   Parent page2;

           try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/detailsevents.fxml"));
                             Scene scene = new Scene(page2);
                 stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
            
            
}
}
