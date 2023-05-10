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
import com.mycompany.entities.Facture;
import com.mycompany.entities.outils;
import com.mycompany.services.ServiceOutils;

/**
 *
 * @author Amen
 */
public class Addoutils extends Form{ 
    
     public Addoutils(Form previous) {
        setTitle("Add a new facture");
        setLayout(BoxLayout.y());
        
        TextField tfnumfact = new TextField("","numero_facture");
        TextField tfdesignation = new TextField("","designation");
        TextField tfprixdesignat= new TextField("","prix_designation");
         TextField tfmontant = new TextField("","montant");
         
      
   
        
        
        
        
        Button btnValider = new Button("Add outil");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnumfact.getText().length()==0) && (tfprixdesignat.getText().length()==0) && (tfprixdesignat.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                           Float prixdes = Float.parseFloat(tfprixdesignat.getText().toString())  ;
         
         Float montant = Float.parseFloat(tfmontant.getText().toString())  ;
                        Facture  p = new Facture(tfdesignation.getText().toString(),prixdes,montant, tfnumfact.getText().toString() );
                        if( ServiceOutils.getInstance().addOutils(p))
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
        
        addAll(tfnumfact,tfprixdesignat,tfdesignation,tfmontant,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
}
