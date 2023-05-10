/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Facture;
import com.mycompany.entities.outils;
import com.mycompany.utils.Statics;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Amen
 */
public class ServiceOutils { 
    
       private ConnectionRequest req;
    
         public ArrayList<Facture> cat;
           public static ServiceOutils instance = null; 
               public boolean resultOK;
 
     private ServiceOutils() {
        req = new ConnectionRequest();
    }

    public static ServiceOutils getInstance() {
        if (instance == null) {
            instance = new ServiceOutils();
        }
        return instance;
    }          
         
         public ArrayList<Facture> parseOutils(String jsonText) {
        try {
            cat = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> outilsListJson= j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) outilsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Facture c = new Facture();
              float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                System.out.println(id);
                 String designation = obj.get("designation").toString();
                c.setDesignation(designation);
                System.out.println(designation);
                     float prixdesignation = Float.parseFloat(obj.get("prix_designation").toString());
                     c.setPrix_designation(prixdesignation);
                                          float montant = Float.parseFloat(obj.get("montant").toString());
                     c.setMontant(montant);
                String numero_facture = obj.get("numero_facture").toString();
                c.setNumero_facture(numero_facture) ; 
    System.out.println(numero_facture);
                       //  float patient_id = Float.parseFloat(obj.get("patient_id").toString());
                //c.setPatient_id((int) patient_id);
    
                     System.out.println(c);
                cat.add(c) ; 
            }

        } catch (IOException ex) {

        }
             System.out.println(cat);
        return cat;
    }
       
       public ArrayList<Facture> getAllOutils() {
        req = new ConnectionRequest();
        
        String url = Statics.BASE_URL + "/mobile/allOutils";
            System.out.println(url);
        System.out.println("===>" + url);
        req.setUrl(url);
       req.setPost(false);
      //  req.setCheckSSLCertificates(false);
       
  
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cat = parseOutils(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req); 
        System.out.println(cat);
        return cat;
    } 
       
           public boolean addOutils(Facture p) {
        System.out.println(p);
        System.out.println("********");
        String url = Statics.BASE_URL +"/mobile/Addoutils/?numero_facture=" + p.getNumero_facture()+"&designation=" + p.getDesignation()+"&prix_designation=" + p.getPrix_designation()+"&montant=" + p.getMontant();
        //String url = Statics.BASE_URL + "create";
               System.out.println(url);

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
             public boolean  Delete(Facture p){
       String url = Statics.BASE_URL + "/mobile/deleteFacture/" +p.getId();
  
        req.setUrl(url);
        req.setPost(false);
        req.setFailSilently(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
    
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

      
}
}
