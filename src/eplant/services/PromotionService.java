/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.services;

import eplant.config.ConnexionSingleton;
import eplant.entities.Produit;
import eplant.entities.Promotion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class PromotionService {
    private static  PromotionService instance;
     Connection cx; 
    Statement ste;
    ConnexionSingleton myconx ;

    public PromotionService() {
         cx  = ConnexionSingleton.getInstance().getCnx();
        try {
            ste = cx.createStatement();
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
    }
    
    
       public static PromotionService getInstance(){
        if(instance==null) 
            instance=new PromotionService();
        return instance;
    }
    
     public ObservableList<Promotion> displayAll() {
 String req="select * from promotion where date_fin>CURRENT_DATE";
ObservableList<Promotion> list=FXCollections.observableArrayList();         
        try {
            ResultSet rs=ste.executeQuery(req);
            while(rs.next()){
                Promotion p=new Promotion();
                p.setId(rs.getInt(1));
                 p.setDescription(rs.getString(6));
                p.setDate_promo(rs.getDate(3).toString());
                p.setProduit_id(rs.getInt(2));
                p.setReduction(rs.getInt(5));
                p.setType(rs.getString(4));
                p.setDate_fin(rs.getString(7));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    
    }
     
       public ObservableList<Promotion> displayBySearch(String search) {
 String req="select * from promotion p left join produit pr on p.produit_id=pr.id where p.date_fin>CURRENT_DATE and pr.nom like '%"+search+"%' or p.date_fin like '%"+search+"%'";
ObservableList<Promotion> list=FXCollections.observableArrayList();         
        try {
            ResultSet rs=ste.executeQuery(req);
            while(rs.next()){
                Promotion p=new Promotion();
                p.setId(rs.getInt(1));
                 p.setDescription(rs.getString(6));
                p.setDate_promo(rs.getDate(3).toString());
                p.setProduit_id(rs.getInt(2));
                p.setReduction(rs.getInt(5));
                p.setType(rs.getString(4));
                p.setDate_fin(rs.getString(7));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    
    }
     
      public Produit searchProduit(int id){
     String req="select * from produit where id='"+id+"'"; 
        Produit p = new Produit();       
        try {
            ResultSet rs=ste.executeQuery(req);
            while(rs.next()){
                p.setId(rs.getInt(1));
                 p.setNom(rs.getString(2));
                p.setImg(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setPrix(rs.getFloat(5));
                p.setCategorie(rs.getString(6));
                p.setStock(rs.getInt(7));
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;   
     }
      
      public void modifierPromotion(Promotion p,int oldvalue){
           java.sql.Date date = java.sql.Date.valueOf(p.getDate_fin());
       String req="update promotion set date_fin= '"+date+"', valeur = '"+p.getReduction()+"' where id='"+p.getId()+"'"; 
       String req1 = "update produit set prix=prix/(1-('"+oldvalue+"')/100) where id='"+p.getProduit_id()+"'";
        String req2 = "update produit set prix=prix - prix*('"+p.getReduction()+"')/100 where id='"+p.getProduit_id()+"'";
       try {
            ste.executeUpdate(req1);
            ste.executeUpdate(req2);
            ste.executeUpdate(req);
                  
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
      public void supprimerPromotion(Promotion p){
       String req="delete from promotion where id='"+p.getId()+"'"; 
       String req1 = "update produit set prix=prix/(1-('"+p.getReduction()+"')/100) where id='"+p.getProduit_id()+"'";
       try {
            ste.executeUpdate(req1);
            ste.executeUpdate(req);
                  
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
}
