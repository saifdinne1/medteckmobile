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
import com.mycompany.entities.consultation;
import com.mycompany.entities.service;
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
public class ServiceConsultation { 
    
       private ConnectionRequest req;
    
         public ArrayList<consultation> cat;
           public static ServiceConsultation instance = null; 
               public boolean resultOK;
 
     private ServiceConsultation() {
        req = new ConnectionRequest();
    }

    public static ServiceConsultation getInstance() {
        if (instance == null) {
            instance = new ServiceConsultation();
        }
        return instance;
    }          
         
         public ArrayList<consultation> parseOutils(String jsonText) {
        try {
            cat = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> outilsListJson= j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) outilsListJson.get("root");
            for (Map<String, Object> obj : list) {
                consultation c = new consultation();
              float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                System.out.println(id);
/**                 String description = obj.get("description").toString();
                c.setDescription(description);
                System.out.println(description);
                     
                                       */

/*
                c.setDescription(obj.get("description").toString());
                System.out.println();
 */                
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
       
       public ArrayList<consultation> getAllOutils() {
        req = new ConnectionRequest();
        
        String url = Statics.BASE_URL + "/mobile/allConsultation";
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
       
         public boolean addConsultation(consultation p) {
        System.out.println(p);
        System.out.println("********");
        String url = Statics.BASE_URL +"/mobile/AddCons/?discription=" + p.getDescription();
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
             public boolean  Delete(consultation p){
       String url = Statics.BASE_URL + "/delete/" +p.getId();
  
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
