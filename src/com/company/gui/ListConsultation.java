
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
import com.mycompany.entities.consultation;
import com.mycompany.entities.outils;
import com.mycompany.services.ServiceOutils;
import com.mycompany.services.ServiceConsultation;


/**
 *
 * @author Amen
 */
public class ListConsultation extends Form{
    
    public ListConsultation(Form previous) {
        
         setTitle("List Consultation");
          
               Container List = new Container (BoxLayout.y());
    
        Button exit = new Button("exit");
       exit.setUIID("exit");
           exit.addActionListener(e -> new FournisseurList(UIManager.initFirstTheme("/theme")).show());
           add(exit);
        for (consultation c : ServiceConsultation.getInstance().getAllOutils()) {
            MultiButton mb = new MultiButton(c.toString()); 
        
            //System.out.println(user.getId());
       //Button update = new Button("update");
       // update.setUIID("update");
            //update.addActionListener(e -> new Modifieroutil(c,previous).show());
         
        //Button delete = new Button("delete");
       //delete.setUIID("delete");
          // delete.addActionListener(e -> new deleteoutil(c,previous).show());
//            for(int i = 0; i < user; i++)
//            {
//                System.out.println();
//                }
           add(mb);
            //add(delete);
            //add(update);
            
            }
        
/*
    SpanLabel sp = new SpanLabel();
     sp.setText(ServiceOutils.getInstance().getAllOutils().toString());
    add(sp); */
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
