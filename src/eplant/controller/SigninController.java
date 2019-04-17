/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.controller;

import eplant.config.UserSession;
import eplant.entities.Personne;
import eplant.services.PersonneService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SigninController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private Button connect;
    @FXML
    private Button signin;
    @FXML
    private TextField nom;
        @FXML
    private TextField prenom;
      @FXML
    private TextField email;
        @FXML
    private TextField mdp;
        @FXML
    private TextField mobile;
        @FXML
    private TextField adresse;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signin.setOnAction((event) -> {
            if(email.getText().equalsIgnoreCase("") || mdp.getText().equalsIgnoreCase("") || nom.getText().equalsIgnoreCase("") || prenom.getText().equalsIgnoreCase("") || mobile.getText().equalsIgnoreCase("") || adresse.getText().equalsIgnoreCase("")){
            
                  Alert alert = new Alert(Alert.AlertType.WARNING);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs");
        alert.show();

            
            }
            
            if(!isValid(email.getText())){
                 Alert alert = new Alert(Alert.AlertType.WARNING);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Email incorrect!");
        alert.show();

            }
            
            if(!mobile.getText().matches("-?\\d+(\\.\\d+)?")){
             Alert alert = new Alert(Alert.AlertType.WARNING);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Numero de telephone incorrect!");
        alert.show();
            }
            
            else{
              Personne p = new Personne();
                    p.setAdresse(adresse.getText());
                    p.setRole("client");
                    p.setZone("");
                    p.setEmail(email.getText());
                    p.setPassword(mdp.getText());
                    p.setPrenom(prenom.getText());
                    p.setNom(nom.getText());
                    p.setNumtel(Integer.valueOf(mobile.getText()));
                    PersonneService s1 = new PersonneService();
                    
                    if(!s1.check(p)){
                    s1.insert(p);
                     Parent page2;
                        UserSession session = UserSession.getInstace(p.getEmail(), p.getRole());
           
                        if("client".equalsIgnoreCase(session.getRole())){
                        Notifications.create().title("Notification").
                                text("Bienvenu "+ p.getPrenom()).position(Pos.TOP_CENTER).showInformation();
                      }
                        try {
                            page2 = FXMLLoader.load(getClass().getResource("/eplant/view/Accueil.fxml"));
                             Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     }
                    
                    else{
                     Alert alert = new Alert(Alert.AlertType.WARNING);

                        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Client dejà existant!");
        alert.show();
                    }
            }
        });
        
         connect.setOnAction((event) -> {
            Parent page;
              try {
                            page = FXMLLoader.load(getClass().getResource("/eplant/view/Login.fxml"));
                             Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        });
    }    
    
    
    public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
}
