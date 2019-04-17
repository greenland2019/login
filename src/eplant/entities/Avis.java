/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.entities;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author wiemhjiri
 */
public class Avis {
    
    private SimpleIntegerProperty id;
    //private SimpleStringProperty id_user;
    private SimpleStringProperty etat;
    private SimpleStringProperty commentaire;

    public Avis() {
    }

    
    public Avis(int id,String etat,String commentaire) {
        this.id = new SimpleIntegerProperty(id);
        this.etat = new SimpleStringProperty(etat);
        this.commentaire = new SimpleStringProperty(commentaire);

    }

    public Avis(String etat, String commentaire) {
        this.etat = new SimpleStringProperty(etat);
        this.commentaire = new SimpleStringProperty(commentaire);
       
    }
    
    
    public SimpleStringProperty getEtatProperty(){
        return etat;
    }
    public SimpleStringProperty getCommentaireProperty(){
        return commentaire;
    }    
    

    @Override
    public String toString() {
        return "Avis{" + "id=" + id.get() + ", Etat=" + etat.get() + ", commentaire=" + commentaire.get()+ '}';
    }
    
    
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id =new SimpleIntegerProperty(id);
    }

    public String getEtat() {
        return etat.get();
    }

    public void setEtat(String etat) {
        this.etat = new SimpleStringProperty(etat);
    }
    public String getCommentaire() {
        return commentaire.get();
    }

    public void setCommentaire(String categorie) {
        this.commentaire = new SimpleStringProperty(categorie);
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
        final Avis other = (Avis) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}