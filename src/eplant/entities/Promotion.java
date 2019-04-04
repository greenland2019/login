/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.entities;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HP
 */
public class Promotion {
    private int id;
    private int produit_id;
    private String date_promo;
    private String type;
    private int reduction;
    private SimpleStringProperty description;
    private String date_fin;

    public Promotion() {
    }

    public Promotion(int id, int produit_id, String date_promo, String type, int reduction, SimpleStringProperty description, String date_fin) {
        this.id = id;
        this.produit_id = produit_id;
        this.date_promo = date_promo;
        this.type = type;
        this.reduction = reduction;
        this.description = description;
        this.date_fin = date_fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(int produit_id) {
        this.produit_id = produit_id;
    }

    public String getDate_promo() {
        return date_promo;
    }

    public void setDate_promo(String date_promo) {
        this.date_promo = date_promo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }
    
     public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description = new SimpleStringProperty(description);
    }

    public SimpleStringProperty getDescriptionProp() {
        return description;
    }

    public void setDescriptionProp(SimpleStringProperty description) {
        this.description = description;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }
    
    
    
}
