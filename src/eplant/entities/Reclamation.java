/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.entities;

import java.util.Objects;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author wiemhjiri
 */
public class Reclamation {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty sujet;
    private SimpleStringProperty contenue;
    private SimpleStringProperty email;
    private SimpleBooleanProperty etat;
    private SimpleStringProperty categorie;

    public Reclamation() {
    }

    
    public Reclamation(int id, String sujet, String contenue , String email,boolean etat,String categorie) {
        this.id = new SimpleIntegerProperty(id);
        this.sujet = new SimpleStringProperty(sujet);
        this.contenue = new SimpleStringProperty(contenue);
        this.email = new SimpleStringProperty(email);
        this.etat = new SimpleBooleanProperty(etat);
        this.categorie = new SimpleStringProperty(categorie);

    }

    public Reclamation(String email, String sujet , String contenue , String categorie ) {
        this.email = new SimpleStringProperty(email);
        this.sujet = new SimpleStringProperty(sujet);
        this.contenue = new SimpleStringProperty(contenue);
        this.categorie = new SimpleStringProperty(categorie);

    }
    
    
    public SimpleStringProperty getEmailProperty(){
        return email;
    }
    public SimpleStringProperty getCategorieProperty(){
        return categorie;
    }    
    public SimpleStringProperty getSujetProperty(){
        return sujet;
    }
    public SimpleStringProperty getContenueProperty(){
        return contenue;
   
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id.get() + ", Email=" + email.get() + ", Sujet=" + sujet.get() + ", contenue=" + contenue.get() +", categorie=" + categorie.get() + '}';
    }
    
    
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id =new SimpleIntegerProperty(id);
    }

    public String getSujet() {
        return sujet.get();
    }

    public void setSujet(String sujet) {
        this.sujet = new SimpleStringProperty(sujet);
    }
    public String getCategorie() {
        return categorie.get();
    }

    public void setCategorie(String categorie) {
        this.categorie = new SimpleStringProperty(categorie);
    }

    public String getContenue() {
        return contenue.get();
    }

    public void setContenue(String contenue) {
        this.contenue = new SimpleStringProperty(contenue);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty( email);
    }

   
    public SimpleBooleanProperty getEtatProperty() {
        return etat;
    }
    
     public boolean getEtat() {
        return etat.get();
    }

    public void setEtat(boolean etat) {
        this.etat = new SimpleBooleanProperty (etat);
    }

      @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}