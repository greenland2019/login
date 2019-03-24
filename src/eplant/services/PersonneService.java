/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.services;

import eplant.config.ConnexionSingleton;
import eplant.entities.Personne;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public boolean update(Personne os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
