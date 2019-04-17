/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.services;

import eplant.config.ConnexionSingleton;
import eplant.entities.Produit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ProduitService {
    private static Statement ste;
     private ResultSet rs;
    ConnexionSingleton myconx ;
        Connection con=myconx.getInstance().getCnx();
    
        public ProduitService(){
             try{
                ste=con.createStatement();
            }catch(SQLException e){
                System.out.println(e);
            }
        }
    public void add(Produit pr)  {
        String req = "INSERT INTO produit (id ,img,prix ,stock,categorie, description,nom  )"
                + "VALUES (?, ?, ?, ?, ?,?,?)";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setInt(1, pr.getId());
            preStatement.setString(2, pr.getImg());
            preStatement.setFloat(3, pr.getPrix());
            preStatement.setInt(4, pr.getStock());
            preStatement.setString(5, pr.getCategorie());
            preStatement.setString(6, pr.getDescription());
            preStatement.setString(7, pr.getNom());
           
            preStatement.execute();
            System.out.println("add method was called successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
  }

         public void deleteById(Produit pr) {
        String req = "DELETE  FROM `produit` WHERE `id` = ?";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setInt(1, pr.getId());

            preStatement.executeUpdate();

            System.out.println("deleteById method was called successfully");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
        
         public void updatePrice(Produit pr, float nprice)  {
       // pr.setPrice(nprice);
        String req = "UPDATE `produit` SET prix=? WHERE id=?";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setFloat(1, nprice);
            preStatement.setInt(2, pr.getId());

            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
        public void updateQuantité(Produit pr, int nstock)  {
        //pr.setQuantity(nquantity);
        String req = "UPDATE `produit` SET stock=? WHERE id=?";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setInt(1, nstock);
            preStatement.setInt(2, pr.getId());

            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    } 
        
         public void updateProd(Produit pr)  {
        //pr.setQuantity(nquantity);
        String req = "UPDATE `produit` SET stock=?,description=?,prix=? WHERE id=?";
        PreparedStatement preStatement;
        try {
            preStatement = con.prepareStatement(req);

            preStatement.setInt(1, pr.getStock());
            preStatement.setString(2, pr.getDescription());
            preStatement.setFloat(3, pr.getPrix());
            preStatement.setInt(4, pr.getId());
            

            preStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    } 
        public ArrayList<Produit> read() {
        ArrayList<Produit> tab = new ArrayList<Produit>();
        String req = "SELECT * FROM `produit`" ;
        try {   
            Statement ste = con.createStatement();
            ResultSet result = ste.executeQuery(req);
            while (result.next()) {
             int id = result.getInt(1);
             String img = result.getString(3);
             float prix = result.getFloat(5);
             int stock = result.getInt(7);
              String categorie = result.getString(6);
              String description=result.getString(4);
              String nom = result.getString(2);
                                               
                //in every etiration we create a new instance 
                Produit pr = new Produit(id ,nom,img, description,prix,categorie ,stock);
                tab.add(pr);  
            }
        } catch (SQLException e) {
        } finally{
            return tab;
        }
}
   
        
        public  Produit rechercheprod(String url) throws SQLException {
        Produit pr = new Produit();
        String req = "SELECT * FROM `produit` where img= ? ";
       PreparedStatement preStatement = con.prepareStatement(req);
            preStatement.setString(1,url);
            ResultSet result = preStatement.executeQuery();
            
            
            while (result.next()) {
                pr.setId(result.getInt(1));
                pr.setImg ( result.getString(2)) ;
                pr.setPrix ( result.getInt(3));
                pr.setStock ( result.getInt(4));
                pr.setCategorie (result.getString(5));
                pr.setDescription ( result.getString(6));
                pr.setNom( result.getString(7));
               
          //in every etiration we create a new instance
            }
       
            return pr;
        }
        
         public Produit FindProduit(int id){
        String req="select * from produit where id = '"+id+"'";
Produit p=new Produit();        
        try {
            rs=ste.executeQuery(req);
            while(rs.next()){
                
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setImg(rs.getString(3));
                p.setPrix(rs.getFloat(5));
                p.setStock(rs.getInt(7));
                 p.setCategorie(rs.getString(6));
                p.setDescription(rs.getString(4));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    
    }
        
    }
    