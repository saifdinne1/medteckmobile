/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author pc
 */
public class service {
      private int id;
      private String nom_service;
      private String type_service;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nom_service
     */
    public String getNom_service() {
        return nom_service;
    }

    /**
     * @param nom_service the nom_service to set
     */
    public void setNom_service(String nom_service) {
        this.nom_service = nom_service;
    }

    /**
     * @return the type_service
     */
    public String getType_service() {
        return type_service;
    }

    /**
     * @param type_service the type_service to set
     */
    public void setType_service(String type_service) {
        this.type_service = type_service;
    }

    @Override
    public String toString() {
        return "service{" + "id=" + id + ", nom_service=" + nom_service + ", type_service=" + type_service + '}';
    }

    public service(int id, String nom_service, String type_service) {
        this.id = id;
        this.nom_service = nom_service;
        this.type_service = type_service;
    }

    public service(String nom_service, String type_service) {
        this.nom_service = nom_service;
        this.type_service = type_service;
    }

    public service() {
    }
    
    
}
