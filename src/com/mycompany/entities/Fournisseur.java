/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author dhafeur
 */
public class Fournisseur {
    int id,tel;
    String nom,prenom,email;

    public Fournisseur() {
    }
    

    public Fournisseur(int id, int tel, String nom, String prenom, String email) {
        this.id = id;
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Fournisseur(int tel, String nom, String prenom, String email) {
        this.tel = tel;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Fournisseur{" + "id=" + id + ", tel=" + tel + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + '}';
    }
    
    
}
