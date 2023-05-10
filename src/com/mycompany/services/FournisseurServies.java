/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Fournisseur;
import com.mycompany.myapp.MyApplication;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dhafeur
 */
public class FournisseurServies {
          public static FournisseurServies instance = null ;
    public int resultCode;
    public static boolean resultOk = true;
    private ArrayList<Fournisseur> listplat;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static FournisseurServies getInstance() {
        if(instance == null )
            instance = new FournisseurServies();
        return instance ;
    }
    
    
    
    public FournisseurServies() {
        req = new ConnectionRequest();
        
    }
       //affichage
    public ArrayList<Fournisseur>AffichageFournisseur() {
        ArrayList<Fournisseur> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/mobile/Allfornisseursjson";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapannonce = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapannonce.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Fournisseur forn = new Fournisseur();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        float tel = Float.parseFloat(obj.get("tel").toString());
                        
                        String nom = obj.get("nom").toString();
                        
                        String prenom = obj.get("prenom").toString();
                        String email = obj.get("email").toString();
          
                        
                        
                        
                        forn.setId((int)id) ; 
                        forn.setTel((int)tel) ;
                        forn.setNom(nom);
                        forn.setPrenom(prenom);
                        forn.setEmail(email);
                       
//insert data into ArrayList result
                        result.add(forn);

                    }
                    
                }catch(IOException | NumberFormatException | NullPointerException ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
    }
        public boolean deleteFournisseur(int id){
     String url = Statics.BASE_URL+"/mobile/Deletefornisseursjson?id="+id;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
         @Override
         public void actionPerformed(NetworkEvent evt) {
             req.removeResponseCodeListener(this);//To change body of generated methods, choose Tools | Templates.
         }
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return true;
    }
                 public boolean ajoutFournisseur(Fournisseur forn) {
        
        String url =Statics.BASE_URL+"/mobile/Addfornisseursjson/new?nom="+forn.getNom()+"&prenom="+forn.getPrenom()+"&email="+forn.getEmail()+"&tel="+forn.getTel();
        
        ConnectionRequest req = new ConnectionRequest(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOk = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
    }
  public boolean updateFournisseur(Fournisseur forn) {
        
        String url =Statics.BASE_URL+"/mobile/Upatefornisseursjson?nom="+forn.getNom()+"&prenom="+forn.getPrenom()+"&email="+forn.getEmail()+"&tel="+forn.getTel()+"&id="+MyApplication.forn.getId(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        ConnectionRequest req = new ConnectionRequest(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOk = req.getResponseCode() == 200;
            req.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
    }
  
    public ArrayList<Fournisseur>TrierFournisseur() {
        ArrayList<Fournisseur> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/mobile/AllfornisseursjsonTri";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapannonce = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapannonce.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Fournisseur forn = new Fournisseur();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        float tel = Float.parseFloat(obj.get("tel").toString());
                        
                        String nom = obj.get("nom").toString();
                        
                        String prenom = obj.get("prenom").toString();
                        String email = obj.get("email").toString();
          
                        
                        
                        
                        forn.setId((int)id) ; 
                        forn.setTel((int)tel) ;
                        forn.setNom(nom);
                        forn.setPrenom(prenom);
                        forn.setEmail(email);
                       
//insert data into ArrayList result
                        result.add(forn);

                    }
                    
                }catch(IOException | NumberFormatException | NullPointerException ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
    }
    
}
