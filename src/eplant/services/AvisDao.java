
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.services;


import eplant.services.*;
import eplant.entities.Avis;
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
public class AvisDao implements Idao<Avis>{
    
    private static AvisDao instance;
    private Statement st;
    private ResultSet rs;
    
    private AvisDao() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(AvisDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static AvisDao getInstance(){
        if(instance==null) 
            instance=new AvisDao();
        return instance;
    }

    @Override
    public void insert(Avis o) {
        String req="insert into avis (etat,commentaire) values ('"    +o.getEtat()+"','"+o.getCommentaire()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(AvisDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Avis o) {
        String req="delete from avis where id="+o.getId();
        Avis p;
        p = displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(AvisDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     * @throws SQLException
     */
    public void supprimer(int id) throws SQLException {
            String req = "delete from avis where id=" + id;
            st.executeUpdate(req);
            System.out.println("suppression ok");
        

    }
    
    
    
    
    
    
    
    @Override
    public ObservableList<Avis> displayAll() {
        String req="select * from avis";
        ObservableList<Avis> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Avis p=new Avis();
                p.setId(rs.getInt(1));
                p.setEtat(rs.getString("Etat"));
                p.setCommentaire(rs.getString("commentaire"));
                
                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Avis> displayAllList() {
        String req="select * from avis";
       List<Avis> list=new ArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Avis p=new Avis();
                p.setId(rs.getInt(1));
                p.setEtat(rs.getString("etat"));
                p.setCommentaire(rs.getString("commentaire"));
               
                                
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public List<String> displayAllbyType(List<Integer> nbr) {
        String req="select etat , count(*) as count from avis group by etat ";
       List<String> list=new ArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               list.add(rs.getString("etat"));
               nbr.add(rs.getInt("count"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Avis displayById(int id) {
           String req="select * from avis where id ="+id;
           Avis p=new Avis();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                p.setId(rs.getInt("id"));
                p.setEtat(rs.getString("etat"));
                p.setCommentaire(rs.getString("commentaire"));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(AvisDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    @Override
    public boolean update(Avis p) {
        String qry = "UPDATE avis SET Etat = '"+p.getEtat()+"', Comentaire = '"+p.getCommentaire()+"' WHERE id = "+p.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AvisDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    
}
