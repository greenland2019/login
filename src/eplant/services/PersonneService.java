/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.services;

import eplant.config.ConnexionSingleton;
import eplant.entities.Participation;
import eplant.entities.Personne;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author HP
 */
public class PersonneService implements IEplants<Personne>
{
    
     Connection cx; 
    Statement ste;
    ConnexionSingleton myconx ;

    public PersonneService() {
         cx  = ConnexionSingleton.getInstance().getCnx();
        try {
            ste = cx.createStatement();
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
    }
    

    @Override
    public void insert(Personne o) {
     String req="insert into personne (nom,prenom,email,password,num_tel,addresse,role,zone) values ('"+o.getNom()+"','"+o.getPrenom()+"','"+o.getEmail()+"','"+o.getPassword()+"','"+o.getNumtel()+"','"+o.getAdresse()+"','"+o.getRole()+"','"+o.getZone()+"')";
        try {
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PersonneService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Personne o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean check(Personne p) {
try {
   
  
            
            String req2= "select * from Personne where email = '"+p.getEmail()+"' and password = '"+p.getPassword()+"' ";
         
            ResultSet res =   ste.executeQuery(req2);
            while (res.next()) {  
                p.setRole(res.getString("role"));
                return true;
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return false;
    }

    @Override
    public List<Personne> displayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personne displayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Personne os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public Personne SearchByMail(String mail){
        Personne p = new Personne();
try {
            
            String req2= "select * from Personne where email = '"+mail+"' ";
         
            ResultSet res =   ste.executeQuery(req2);
            
            while (res.next()) {  
                p.setId(res.getInt("id"));
                p.setPassword(res.getString("password"));
                p.setNom(res.getString("nom"));
                p.setPrenom(res.getString("prenom"));
                p.setEmail(res.getString("email"));
                p.setNumtel(res.getInt("num_tel"));
                p.setAdresse(res.getString("addresse"));
                p.setRole(res.getString("role"));
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return p;
   }
    
    public ObservableList<Personne> ListParticipants(List<Participation> part){
    ObservableList<Personne> list=FXCollections.observableArrayList();  
        for(Participation par:part){
    String req="select * from personne where id = '"+par.getParticipant_id()+"'";
       
        try {
            ResultSet rs=ste.executeQuery(req);
            while(rs.next()){
                Personne p=new Personne();
                p.setId(rs.getInt(1));
                 p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setPassword(rs.getString(5));
                p.setNumtel(rs.getInt(6));
                p.setAdresse(rs.getString(7));
                p.setRole(rs.getString(8));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        return list;    
    }
    
    public ObservableList<Personne> getPersons(String nom , String prenom, int eventid){
    ObservableList<Personne> list=FXCollections.observableArrayList();  
    String req="select * from personne p inner join participation par on p.id=par.participant_id where (p.nom like '%"+nom+"%' or p.prenom like '%"+prenom+"%') and par.evenement_id='"+eventid+"'";
       
        try {
            ResultSet rs=ste.executeQuery(req);
            while(rs.next()){
                Personne p=new Personne();
                p.setId(rs.getInt(1));
                 p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                p.setEmail(rs.getString(4));
                p.setPassword(rs.getString(5));
                p.setNumtel(rs.getInt(6));
                p.setAdresse(rs.getString(7));
                p.setRole(rs.getString(8));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;    
    }
    
}
