/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.services;


import eplant.entities.Reclamation;
import eplant.config.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author wiemhjiri
 */
public class ReclamationDao implements Idao<Reclamation>{
    
    private static ReclamationDao instance;
    private Statement st;
    private ResultSet rs;
    
    private ReclamationDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ReclamationDao getInstance(){
        if(instance==null) 
            instance=new ReclamationDao();
        return instance;
    }

    @Override
    public void insert(Reclamation o) {
        String req="insert into reclamation (email,categorie,sujet,contenue) values ('"    +o.getEmail()+"','"+o.getCategorie()+"','"+o.getSujet()+"','"+o.getContenue()+             "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Reclamation o) {
        String req="delete from reclamation where id="+o.getId();
        Reclamation p;
        p = displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Reclamation> displayAll() {
        String req="select * from reclamation";
        ObservableList<Reclamation> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Reclamation p=new Reclamation();
                p.setId(rs.getInt("id"));
                p.setEmail(rs.getString("Email"));
                p.setSujet(rs.getString("Sujet"));
                p.setContenue(rs.getString("Contenue"));
                p.setCategorie(rs.getString("categorie"));
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Reclamation> displayAllList() {
        String req="select * from reclamation";
       List<Reclamation> list=new ArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Reclamation p=new Reclamation();
                p.setId(rs.getInt(1));
                p.setEmail(rs.getString("email"));
                p.setSujet(rs.getString("sujet"));
                p.setContenue(rs.getString("contenue"));
                p.setCategorie(rs.getString("categorie"));
                                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Reclamation displayById(int id) {
           String req="select * from reclamation where id ="+id;
           Reclamation p=new Reclamation();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setEmail(rs.getString("email"));
                p.setSujet(rs.getString("sujet"));
                p.setContenue(rs.getString("contenue"));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public boolean update(Reclamation p) {
        String qry = "UPDATE reclamation SET Email = '"+p.getEmail()+"', contenue = '"+p.getContenue()+"', categorie = '"+p.getCategorie()+"' WHERE id = "+p.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int research(Reclamation os) {
        return 1;
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
