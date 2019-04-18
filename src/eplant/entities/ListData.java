/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.entities;

import eplant.services.ReclamationDao;
import eplant.entities.Reclamation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author wiemhjiri
 */
public class ListData {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Reclamation> reclam=FXCollections.observableArrayList();

    private String email="";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public ListData() {
        
            }
    
    public ObservableList<Reclamation> getReclam(){
        ReclamationDao pdao=ReclamationDao.getInstance();
        reclam= pdao.displaybyEmail(email);
        return reclam;
    }
   
}
