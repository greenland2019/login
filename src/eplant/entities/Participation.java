/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eplant.entities;

/**
 *
 * @author HP
 */
public class Participation {
    
    private int id;
    private int participant_id;
    private int evenement_id;

    public Participation(int id, int participant_id, int evenement_id) {
        this.id = id;
        this.participant_id = participant_id;
        this.evenement_id = evenement_id;
    }

    public Participation(int participant_id, int evenement_id) {
        this.participant_id = participant_id;
        this.evenement_id = evenement_id;
    }

    public Participation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(int participant_id) {
        this.participant_id = participant_id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }
    
    
    
}
