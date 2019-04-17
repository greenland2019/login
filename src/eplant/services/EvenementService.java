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
import static com.itextpdf.text.pdf.XfaXpathConstructor.XdpPackage.Pdf;
import eplant.config.ConnexionSingleton;
import java.sql.Connection;
import java.sql.ResultSet;
import eplant.entities.Evenement;
import eplant.entities.Participation;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;

/**
 *
 * @author HP
 */
public class EvenementService implements IEplants<Evenement>  
{

private static  EvenementService instance;
     Connection cx; 
    Statement ste;
    ConnexionSingleton myconx ;

    public EvenementService() {
         cx  = ConnexionSingleton.getInstance().getCnx();
        try {
            ste = cx.createStatement();
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
    }
    
    
       public static EvenementService getInstance(){
        if(instance==null) 
            instance=new EvenementService();
        return instance;
    }
    
    @Override
    public void insert(Evenement o) {
       
       java.sql.Date date = java.sql.Date.valueOf(o.getDate_event());
             String req="insert into evenement (date_event,type,nb_participants,lieu,description,affiche) values ('"+date+"','"+o.getType()+"','"+o.getNb_participants()+"','"+o.getLieu()+"','"+o.getDescription()+"','"+o.getAffiche()+"')";
  try {
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    

    @Override
    public void delete(Evenement o) {
try {
   
  
            
            String req2= "delete from Evenement where id = '"+o.getId()+"' ";
         
            ste.executeUpdate(req2);
           
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean check(Evenement p) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public ObservableList<Evenement> displayAll() {
 String req="select * from evenement";
ObservableList<Evenement> list=FXCollections.observableArrayList();         
        try {
            ResultSet rs=ste.executeQuery(req);
            while(rs.next()){
                Evenement p=new Evenement();
                p.setId(rs.getInt(1));
                 p.setDescription(rs.getString(6));
                p.setDate_event(rs.getDate(2).toString());
                p.setAffiche(rs.getString(7));
                p.setLieu(rs.getString(5));
                p.setType(rs.getString(3));
                p.setNb_participants(rs.getInt(4));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    
    }

    @Override
    public Evenement displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Evenement o) {
java.sql.Date date = java.sql.Date.valueOf(o.getDate_event());
             String req="update evenement set date_event ='"+date+"',type='"+o.getType()+"', nb_participants='"+o.getNb_participants()+"', lieu='"+o.getLieu()+"',description='"+o.getDescription()+"',affiche='"+o.getAffiche()+"' where id='"+o.getId()+"'";
  try {
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }
    
    public Evenement SearchByMail(String mail){
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

   }
    
     public ObservableList<Evenement> searchEvent(String search) {
 String req="select * from evenement where (description like '%"+search+"%' or date_event  like '%"+search+"%' or type  like '%"+search+"%') and date_event>=CURRENT_TIME order by date_event asc"; 
ObservableList<Evenement> list=FXCollections.observableArrayList();         
        try {
            ResultSet rs=ste.executeQuery(req);
            while(rs.next()){
                Evenement p=new Evenement();
                p.setId(rs.getInt(1));
                 p.setDescription(rs.getString(6));
                p.setDate_event(rs.getDate(2).toString());
                p.setAffiche(rs.getString(7));
                p.setLieu(rs.getString(5));
                p.setType(rs.getString(3));
                p.setNb_participants(rs.getInt(4));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    
    }
     
     public void ajoutParticipation(Participation p){
         String req="insert into participation (participant_id,evenement_id) values ('"+p.getParticipant_id()+"','"+p.getEvenement_id()+"')";
         String req1="update evenement set nb_participants=nb_participants+1 where id='"+p.getEvenement_id()+"'";
  try {
            ste.executeUpdate(req);
            ste.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public boolean checkParticipation(int evenement_id,int participant_id){
     String req="select * from participation where evenement_id='"+evenement_id+"' and participant_id='"+participant_id+"'"; 
       // Participation p = new Participation(0,0,0);       
        try {
            ResultSet rs=ste.executeQuery(req);
            while(rs.next()){
              /*  p.setId(rs.getInt(1));
                 p.setParticipant_id(rs.getInt(2));
                p.setEvenement_id(rs.getInt(3));*/
              return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
     }
     
     public List<Participation> ListParticipEvents(int evenement_id){
     String req="select * from participation where evenement_id='"+evenement_id+"'"; 
          List<Participation> part = new ArrayList<>();
        try {
            ResultSet rs=ste.executeQuery(req);
            while(rs.next()){
                 Participation p = new Participation(0,0,0);    
                p.setId(rs.getInt(1));
                 p.setParticipant_id(rs.getInt(2));
                p.setEvenement_id(rs.getInt(3));
                part.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return part;   
     }
     
     
     public void deleteParticipation(Participation p){
     try {
   
   String req2= "delete from participation where evenement_id = '"+p.getEvenement_id()+"' and  participant_id = '"+p.getParticipant_id()+"'";
          String req1="update evenement set nb_participants=nb_participants-1 where id='"+p.getEvenement_id()+"'";
            ste.executeUpdate(req2);
            ste.executeUpdate(req1);
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }

      public List<Evenement> EventsByPopularity() {
 String req="select * from evenement order by nb_participants desc"; 
List<Evenement> list=new ArrayList<>();         
        try {
            ResultSet rs=ste.executeQuery(req);
            while(rs.next()){
                Evenement p=new Evenement();
                p.setId(rs.getInt(1));
                 p.setDescription(rs.getString(6));
                p.setDate_event(rs.getDate(2).toString());
                p.setAffiche(rs.getString(7));
                p.setLieu(rs.getString(5));
                p.setType(rs.getString(3));
                p.setNb_participants(rs.getInt(4));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;    
    }
      
      public void generatepdfparts(String text,String event,String datee,int id){
     Document document = new Document();
        try {
     try {
         PdfWriter.getInstance(document, new FileOutputStream("Participants"+"_"+event+"_"+datee+"_"+id+".pdf"));
     } catch (DocumentException ex) {
         System.out.println(ex.getMessage());
     }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
 
document.open();
        com.itextpdf.text.Font font = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.GREEN);
         com.itextpdf.text.Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.RED);
        Date now = new Date();
Chunk date = new Chunk("Sortie le "+now.toString(), font);
        Chunk title = new Chunk(event+" du "+datee, font1);
        title.setUnderline(2,-2);
          Paragraph para = new Paragraph();
    para.add(date); 
para.add(Chunk.NEWLINE); 
para.add(Chunk.NEWLINE); 
para.add(title); 
para.add(Chunk.NEWLINE); 
para.add(text);
        try {
            Image img;
         try {
             img = Image.getInstance("C:/Users/HP/Desktop/Eplants/src/images/logo.png");
        img.scaleAbsolute(125, 125);
         document.add(img);
         } catch (BadElementException ex) {
             Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
            
            document.add(para);
           
        } catch (DocumentException ex) {
            System.out.println(ex.getMessage());
        }
document.close();
      }
      
      public Evenement NextEvent() {
 String req="select * from evenement where date_event>CURRENT_TIME order by date_event asc limit 1"; 
                Evenement p=new Evenement();
        try {
            ResultSet rs=ste.executeQuery(req);
            while(rs.next()){
                p.setId(rs.getInt(1));
                 p.setDescription(rs.getString(6));
                p.setDate_event(rs.getDate(2).toString());
                p.setAffiche(rs.getString(7));
                p.setLieu(rs.getString(5));
                p.setType(rs.getString(3));
                p.setNb_participants(rs.getInt(4));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;    
    }
}
