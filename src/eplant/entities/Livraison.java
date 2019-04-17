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
public class Livraison {
     private int id;
    private int livreur_id;
    private int commande_id;
    private String addresse;
    private String etat;

    public Livraison() {
    }

    public Livraison(int livreur_id, int commande_id, String addresse, String etat) {
        this.livreur_id = livreur_id;
        this.commande_id = commande_id;
        this.addresse = addresse;
        this.etat = etat;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLivreur_id() {
        return livreur_id;
    }

    public void setLivreur_id(int livreur_id) {
        this.livreur_id = livreur_id;
    }

    public int getCommande_id() {
        return commande_id;
    }

    public void setCommande_id(int commande_id) {
        this.commande_id = commande_id;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    
}
