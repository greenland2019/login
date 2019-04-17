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
public class Panier {
     private int id;
    private int user_id;
    private int produit_id;
    private int quantite;
    private String date_p;

    public Panier() {
    }

    public Panier(int user_id, int produit_id, int quantite, String date_p) {
        this.user_id = user_id;
        this.produit_id = produit_id;
        this.quantite = quantite;
        this.date_p = date_p;
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

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getDate_p() {
        return date_p;
    }

    public void setDate_p(String date_p) {
        this.date_p = date_p;
    }
    
    
}
