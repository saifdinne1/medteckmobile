/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.service;
import com.mycompany.entities.outils;
import com.mycompany.services.Serviceservice;

/**
 *
 * @author Amen
 */
public class Addservice extends Form{ 
    
    
     public Addservice(Form previous) {
        setTitle("Add a new service");
        setLayout(BoxLayout.y());
        
        TextField tfnomserv = new TextField("","nom_service");
        TextField tftypeserv = new TextField("","type_service");
       
         
      
   
        
        
        
        
        Button btnValider = new Button("Add service");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnomserv.getText().length()==0) && (tftypeserv.getText().length()==0)  )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                           String nomser = (tfnomserv.getText().toString())  ;
         
         String typeserv = (tftypeserv.getText().toString())  ;
                        service  p = new service(nomser,typeserv );
                        if( Serviceservice.getInstance().addService(p))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tftypeserv,tfnomserv,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
