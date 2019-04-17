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

    public ListData() {
        
        ReclamationDao pdao=ReclamationDao.getInstance();
        reclam= pdao.displayAll();
         System.out.println(reclam);
    }
    
    public ObservableList<Reclamation> getReclam(){
        return reclam;
    }
   
}
