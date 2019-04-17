/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.entities;

import eplant.services.EvenementService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class ListEvents {
    
      private ObservableList<Evenement> events= FXCollections.observableArrayList();
      private ObservableList<Evenement> events1= FXCollections.observableArrayList();
      private String search="";


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
      
    public ListEvents() {
        
        EvenementService pdao= EvenementService.getInstance();
        events = pdao.displayAll();
        
        System.out.println(events);
    }
    
    public ObservableList<Evenement> getEvents(){
        return events;
    }
    
     public ObservableList<Evenement> getEvents1(){
         EvenementService pdao= EvenementService.getInstance();
         events1 = pdao.searchEvent(search);
        return events1;
    }
}
