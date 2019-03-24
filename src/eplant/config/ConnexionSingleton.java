/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eplants
 */
public class ConnexionSingleton {
    
     private String url="jdbc:mysql://127.0.0.1:3306/eplants";
    private String login="root";
    private String pwd="";
    private Connection cnx;
    private static ConnexionSingleton instance;

    public Connection getCnx() {
        return cnx;
    }
    
    
    private ConnexionSingleton() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   public static ConnexionSingleton getInstance(){
       
       if(instance==null)
           instance=new ConnexionSingleton();
       return instance;
   }
    
}
