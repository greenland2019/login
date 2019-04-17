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
import eplant.entities.Produit;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public class PanierService {
     
     private static PanierService instance;
    private Statement st;
    private ResultSet rs;
    
     private PanierService() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public static PanierService getInstance(){
        if(instance==null) 
            instance=new PanierService();
        return instance;
    }
    
 

    public void ajouterPanier(Panier p) {
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         Date date = Date.valueOf(sdf.format(new java.util.Date()));
          
            String req = "INSERT INTO `Panier`(`user_id`,`produit_id`,`quantite`,`date_p`) "
                    + "VALUES ('"+p.getUser_id()+"',"+p.getProduit_id()+","+p.getQuantite()+",CURRENT_TIME) ";
            
            
           st.executeUpdate(req);

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean checkproduit(Panier p){
      String req="select * from panier where user_id='"+p.getUser_id()+"' and produit_id='"+p.getProduit_id()+"'";
        
        try {
            rs=st.executeQuery(req);
              while(rs.next()){
                   return true;
              }
      }
            
         catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void updatepanier(Panier p){
      String req="update panier set quantite=quantite+'"+p.getQuantite()+"' where user_id='"+p.getUser_id()+"' and produit_id='"+p.getProduit_id()+"'  ";
        
        try {
            st.executeUpdate(req);
      }
            
         catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ObservableList<Panier> DisplayAll(int user_id,String search){
        
        String req="select * from panier p inner join produit pr on p.produit_id = pr.id where p.user_id='"+user_id+"' and  pr.nom like '%"+search+"%'";
        ObservableList<Panier> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Panier p=new Panier();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt(2));
                p.setProduit_id(rs.getInt(3));
                p.setQuantite(rs.getInt(4));
               
                p.setDate_p(rs.getString(5));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
     public void modifierpanier(Panier p){
      String req="update panier set quantite='"+p.getQuantite()+"' where user_id='"+p.getUser_id()+"' and produit_id='"+p.getProduit_id()+"'  ";
        
        try {
            st.executeUpdate(req);
      }
            
         catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void supppanier(int x , int y ){
      String req="delete from panier where produit_id='"+x+"'and user_id='"+y+"'  ";
        
        try {
            st.executeUpdate(req);
      }
            
         catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

      public void suppaniers(int user_id) {
        
          
           
             String req2 = "delete from panier where user_id='"+user_id+"'  ";
        try {
           
            
            
           
           st.executeUpdate(req2);

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}