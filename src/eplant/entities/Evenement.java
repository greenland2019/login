/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.entities;

import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HP
 */
public class Evenement {
    
     private int id;
     private SimpleStringProperty date_event;
    private String type;
    private int nb_participants;
    private SimpleStringProperty description;
    private String affiche;
    private String lieu;

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    

    public Evenement() {
    }

    public Evenement(String date_event, String type, int nb_participants,String lieu, String description, String affiche) {
        this.date_event = new SimpleStringProperty(date_event) ;
        this.type = type;
        this.nb_participants = nb_participants;
        this.description = new SimpleStringProperty(description);
        this.affiche = affiche;
       this.lieu = lieu;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_event() {
        return date_event.get();
    }

    public void setDate_event(String date_event) {
        this.date_event = new SimpleStringProperty(date_event);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNb_participants() {
        return nb_participants;
    }

     public SimpleStringProperty getDateProperty(){
        return date_event;
    }
    public SimpleStringProperty getNomProperty(){
        return description;
    }
    
    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }
    
    
    
}
