/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.entities.Facture;
import com.mycompany.entities.outils;
import com.mycompany.services.ServiceOutils;


/**
 *
 * @author Amen
 */
public class ListOutils extends Form{
    
    public ListOutils(Form previous) {
        
         setTitle("List Outils");
          
               Container List = new Container (BoxLayout.y());
    
       Button exit = new Button("exit");
       exit.setUIID("exit");
           exit.addActionListener(e -> new FournisseurList(UIManager.initFirstTheme("/theme")).show());
           add(exit);
        for (Facture c : ServiceOutils.getInstance().getAllOutils()) {
            MultiButton mb = new MultiButton(c.toString()); 
        
            //System.out.println(user.getId());
       Button update = new Button("update");
       update.setUIID("update");
            //update.addActionListener(e -> new Modifieroutil(c,previous).show());
         
        Button delete = new Button("delete");
       delete.setUIID("delete");
           delete.addActionListener(e -> new deleteoutil(c,previous).show());
//            for(int i = 0; i < user; i++)
//            {
//                System.out.println();
//                }
           add(mb);
            add(delete);
            add(update);
            
            }
        

  
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
