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
public class Facture { 
     private int id;
    private Date date_facture;
    private String designation;
    private float prix_designation ; 
    private float montant;
    private int patient_id;
    private String numero_facture ;

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
     * @return the date_facture
     */
    public Date getDate_facture() {
        return date_facture;
    }

    /**
     * @param date_facture the date_facture to set
     */
    public void setDate_facture(Date date_facture) {
        this.date_facture = date_facture;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the montant
     */
    public float getMontant() {
        return montant;
    }

    /**
     * @param montant the montant to set
     */
    public void setMontant(float montant) {
        this.montant = montant;
    }

    /**
     * @return the patient_id
     */
    public int getPatient_id() {
        return patient_id;
    }

    /**
     * @param patient_id the patient_id to set
     */
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    /**
     * @return the numero_facture
     */
    public String getNumero_facture() {
        return numero_facture;
    }

    /**
     * @param numero_facture the numero_facture to set
     */
    public void setNumero_facture(String numero_facture) {
        this.numero_facture = numero_facture;
    }

    @Override
    public String toString() {
        return "facture{" + "id=" + id + ", date_facture=" + date_facture + ", designation=" + designation + ", montant=" + montant + ", patient_id=" + patient_id + ", numero_facture=" + numero_facture + '}';
    }

    public Facture(int id, String designation, float montant, int patient_id, String numero_facture) {
        this.id = id;
       
        this.designation = designation;
        this.montant = montant;
        this.patient_id = patient_id;
        this.numero_facture = numero_facture;
    }

    public Facture( String designation, float montant, float prix_designation ,  String numero_facture) {
       
        this.designation = designation;
        this.montant = montant;
     this.prix_designation = prix_designation ; 
        this.numero_facture = numero_facture;
    }

    /**
     * @return the prix_designation
     */
    public float getPrix_designation() {
        return prix_designation;
    }

    /**
     * @param prix_designation the prix_designation to set
     */
    public void setPrix_designation(float prix_designation) {
        this.prix_designation = prix_designation;
    }

    public Facture() {
    }
    
    
    
    
}
