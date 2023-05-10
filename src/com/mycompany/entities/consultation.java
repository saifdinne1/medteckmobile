/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package com.mycompany.entities;
 
import java.util.Date;



/**
 *
 * @author ZeroS TF
 */
public class consultation {
    private int id;
    private Date date_debut;
    private Date date_fin;
    private String description;

    public consultation(int id, Date date_debut, Date date_fin, String description) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }

    public consultation( Date date_debut, Date date_fin, String description) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.description = description;
    }
    
    public consultation( String description) {
         this.description = description;
        
    }

    public consultation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Consulation{" + "id=" + id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", description=" + description + '}';
    }
}
