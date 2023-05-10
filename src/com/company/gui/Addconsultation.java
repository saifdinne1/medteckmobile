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
import com.mycompany.entities.consultation;

import com.mycompany.entities.service;
import com.mycompany.entities.outils;
import com.mycompany.services.ServiceConsultation;
import com.mycompany.services.Serviceservice;

/**
 *
 * @author Amen
 */
public class Addconsultation extends Form{ 
    
     public Addconsultation(Form previous) {
        setTitle("Add a new consultation");
        setLayout(BoxLayout.y());
        
        TextField tfdes = new TextField("","discription");
       
       
         
      
   
        
        
        
        
        Button btnValider = new Button("Add consultation");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfdes.getText().length()==0)   )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                           String nomser = (tfdes.getText().toString())  ;
         
         
                        consultation  p = new consultation(nomser );
                        if( ServiceConsultation.getInstance().addConsultation(p))
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
        
        addAll(tfdes,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
