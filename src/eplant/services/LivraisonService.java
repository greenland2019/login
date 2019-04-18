/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.services;

import eplant.config.ConnexionSingleton;
import eplant.entities.Commande;
import eplant.entities.Livraison;
import eplant.entities.Panier;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public class LivraisonService {
       private static LivraisonService instance;
    private Statement st;
    private ResultSet rs;
    
     private LivraisonService() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public static LivraisonService getInstance(){
        if(instance==null) 
            instance=new LivraisonService();
        return instance;
    }
    
      
        
    public ObservableList<Livraison> DisplayAll(int livreur_id){
        String req="select * from livraison where livreur_id='"+livreur_id+"'and etat='en cours'";
        ObservableList<Livraison> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Livraison p=new Livraison();
                p.setId(rs.getInt(1));
                p.setLivreur_id(rs.getInt(2));
                p.setCommande_id(rs.getInt(3));
                p.setAddresse(rs.getString(4));
                p.setEtat(rs.getString(5));
               
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
//    public Livraison DisplaybyEmail(String email){
//        String req="select * from livraison where email='"+livreur_id+"'and etat='en cours'";
//         Livraison p=new Livraison();
//        try {
//            rs=st.executeQuery(req);
//            while(rs.next()){
//               
//                p.setId(rs.getInt(1));
//                p.setLivreur_id(rs.getInt(2));
//                p.setCommande_id(rs.getInt(3));
//                p.setEtat(rs.getString(4));
//               
//                    }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return p;
//    }
//    
     public void modifierlivraison(int x ){
      String req="update livraison set etat='livré' where commande_id='"+x+"'  ";
        
        try {
            st.executeUpdate(req);
      }
            
         catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
      public void ajouterLivraison(Livraison l) {
         
          
           
            String req1 = "INSERT INTO `livraison`(`livreur_id`,`commande_id`,`addresse`,`etat`) "
                    + "VALUES ('"+l.getLivreur_id()+"','"+l.getCommande_id()+"','"+l.getAddresse()+"','"+l.getEtat()+"') ";
            
        try {
           
            
           
        
           st.executeUpdate(req1);
          

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
