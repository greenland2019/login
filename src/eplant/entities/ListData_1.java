/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.entities;

import eplant.services.AvisDao;
import eplant.entities.Avis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author wiemhjiri
 */
public class ListData_1 {
    
     /**
     * The data as an observable list of Persons.
     */
    
    private ObservableList<Avis> avis=FXCollections.observableArrayList();

    public ListData_1() {
        
        AvisDao pdao=AvisDao.getInstance();
        avis= pdao.displayAll();
        System.out.println(avis);
    }
    
    public ObservableList<Avis> getAvis(){
        return avis;
    }
   
}
