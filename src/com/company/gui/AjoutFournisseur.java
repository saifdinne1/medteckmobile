/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Fournisseur;
import com.mycompany.services.FournisseurServies;
import java.text.SimpleDateFormat;

/**
 *
 * @author dhafeur
 */
public class AjoutFournisseur extends BaseForm{
    Form current ;
    public AjoutFournisseur(Resources res ) {
      super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
                
        TextField nom = new TextField("", "Nom", 20, TextField.EMAILADDR);
        TextField prenom = new TextField("", "Prenom", 20, TextField.EMAILADDR);
        TextField email = new TextField("", "Email", 20, TextField.EMAILADDR);
        TextField tel = new TextField("", "Téléphone", 20, TextField.EMAILADDR);
        
        
      
        
        Picker start = new Picker();
start.setType(Display.PICKER_TYPE_DATE);
        prenom.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        tel.setSingleLineTextArea(false);
       
        
        Button next = new Button("Ajouter");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
     
        
        Container content = BoxLayout.encloseY(
                new Label("AJouter Une Annonce", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                    createLineSeparator(),
                new FloatingHint(email),
                    createLineSeparator(),
                new FloatingHint(tel),
            
  
                start
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next
            
        ));
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                  
                    
                    
                    
              
              

   try {
                
                if(nom.getText().equals("") || prenom.getText().equals("") || email.getText().equals("") || tel.getText().equals("") ) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
                    
                    
                    //njibo iduser men session (current user)
                    Fournisseur plat = new Fournisseur(
                           Integer.parseInt(String.valueOf(tel.getText()).toString()),
                            String.valueOf(nom.getText()).toString(),
                            String.valueOf(prenom.getText()).toString(),
                              String.valueOf(email.getText()).toString()
                                 ) ;
                            
                                 
                    
                    System.out.println("data  plat == "+plat);
                    
                    
                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    FournisseurServies.getInstance().ajoutFournisseur(plat);
                    
                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout
                    
                    //ba3d ajout net3adaw lel ListREclamationForm
                    new FournisseurList(res).show();
                    
                    
                    refreshTheme();//Actualisation
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
                
                    
                    
          
                            
        
            
            
            }
        });
    }
}
