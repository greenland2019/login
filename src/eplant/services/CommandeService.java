/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.services;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import eplant.config.ConnexionSingleton;
import eplant.entities.Commande;
import eplant.entities.Livraison;
import eplant.entities.Panier;
import eplant.entities.Produit;
import static java.awt.SystemColor.text;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class CommandeService {
       private static CommandeService instance;
    private Statement st;
    private ResultSet rs;
    
     private CommandeService() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public static CommandeService getInstance(){
        if(instance==null) 
            instance=new CommandeService();
        return instance;
    }
    
       public void ajouterCommande(Commande c) {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         Date date = Date.valueOf(sdf.format(new java.util.Date()));
          
            String req = "INSERT INTO `Commande`(`user_id`,`date`,`prix_total`,`produits`) "
                    + "VALUES ('"+c.getUser_id()+"',CURRENT_TIME,'"+c.getPrix_total()+"','"+c.getProduits()+"') ";
          
        try {
           
            
            
          
           st.executeUpdate(req);

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
         public ObservableList<Commande> DisplayAll(){
        String req="select * from commande ";
        ObservableList<Commande> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Commande p=new Commande();
                p.setId(rs.getInt(1));
                p.setUser_id(rs.getInt(2));
                p.setDate(rs.getString(3));
                p.setPrix_total(rs.getInt(4));
                p.setProduits(rs.getString(5));
               
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
         
         public int CalculerTotal(int id){
             
        String req="SELECT sum(pr.prix*p.quantite) FROM Panier p inner join produit pr on p.produit_id=pr.id where user_id='"+id+"'";
                   int x;

        try {
            rs=st.executeQuery(req);
            rs.next();
            x=rs.getInt(1);
            return(x);
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
           return 0;
       
    
     }
         
         //trouver la commande
          public int findCommande(){
            String req="SELECT MAX(id)  from commande"; 
       
                   int x;

        try {
            rs=st.executeQuery(req);
            rs.next();
            x=rs.getInt(1);
            return(x);
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
           return 0;
       
   
     }
           //trouver le livreur
           public int findlivreur(String zone){
            String req="select * from personne where zone='"+zone+"'"; 
       
                   int x;

        try {
            rs=st.executeQuery(req);
            rs.next();
            x=rs.getInt(1);
            return(x);
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
           return 0;
       
    
     }
          public void updatestock(int id ,int qte){
      String req="update produit set stock=stock-'"+qte+"' where id='"+id+"' ";
        
        try {
            st.executeUpdate(req);
      }
            
         catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          
          
          
   public void generatepdfparts(ObservableList Paniers,Commande c,int y) throws BadElementException{
     Document document = new Document();
     String text = null;
        try {
     try {
         PdfWriter.getInstance(document, new FileOutputStream("Facture"+y+".pdf"));
     } catch (DocumentException ex) {
         System.out.println(ex.getMessage());
     }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
 
document.open();
        com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK);
         
        java.util.Date now = new java.util.Date();
Chunk date = new Chunk("Sortie le "+now.toString(), font);
        Chunk title = new Chunk("                   Facture° "+y, font);
        title.setUnderline(2,-2);
          Paragraph para = new Paragraph();
    para.add(date); 
    para.add(Chunk.NEWLINE); 
para.add(Chunk.NEWLINE); 
para.add(title); 
for(Object p :Paniers)
{
    
    text=toString((Panier) p);
    para.add(Chunk.NEWLINE); 
para.add(text);
}
para.add(Chunk.NEWLINE); 

String text1="          "+"Prix Total="+c.getPrix_total();
para.add(text1);

        try {
            Image img;
         try {
             img = Image.getInstance("C:/Users/User/Documents/NetBeansProjects/eplant/src/images/plante1.png");
        img.scaleAbsolute(50, 50);
         document.add(img);
         } catch (BadElementException ex) {
             Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
         }
            
            document.add(para);
           
        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
        }
document.close();
      }
   
   public String toString(Panier p ){
        ProduitService pr = new ProduitService();
       
           return pr.FindProduit(p.getProduit_id()).getNom()+"  *   "+p.getQuantite()+"    "+pr.FindProduit(p.getProduit_id()).getPrix()*p.getQuantite();
   
   }
   
     public int Calculerstat(int id){
             
        String req="SELECT COUNT(*) FROM `commande` WHERE MONTH(date)='"+id+"'";
                   int x;

        try {
            rs=st.executeQuery(req);
            rs.next();
            x=rs.getInt(1);
            return(x);
            
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
           return 0;
       
    
     }

}
