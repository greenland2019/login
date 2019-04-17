/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.entities;

/**
 *
 * @author User
 */
public class Commande {
    private int id;
    private int user_id;
    private String date;
    private int prix_total;
    private String produits;

    public Commande() {
    }

    public Commande(int user_id, String date, int prix_total, String produits) {
        this.user_id = user_id;
        this.date = date;
        this.prix_total = prix_total;
        this.produits = produits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(int prix_total) {
        this.prix_total = prix_total;
    }

    public String getProduits() {
        return produits;
    }

    public void setProduits(String produits) {
        this.produits = produits;
    }
    
    
    
}
