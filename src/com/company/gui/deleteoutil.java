/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Facture;
import com.mycompany.entities.outils;
import com.mycompany.services.ServiceOutils;

/**
 *
 * @author Amen
 */
public class deleteoutil extends Form{
    public deleteoutil(Facture p, Form previous) {
        setTitle("delete Facture");
        

        Button btnSubmit = new Button("Delete");
        Button btnret = new Button("return");
        
       //    btnret.addActionListener(e -> new HomeForm(p).show());
        
        
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    Dialog.show("Alert", "Are you sure !!", new Command("OK"));
             
                    System.out.println(p.getId());
                    System.out.println("deleted Promotion");
                    
                    if (ServiceOutils.getInstance().Delete(p)) {
                        Dialog.show("Success", "Connection Accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));
                    }
                        
                }
      
        });
        

        addAll(btnSubmit,btnret);
      // this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }



}
