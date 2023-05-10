/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Fournisseur;
import com.mycompany.myapp.MyApplication;
import com.mycompany.services.FournisseurServies;
import java.util.ArrayList;
import java.util.Hashtable;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.codename1.ui.Image;
/**
 *
 * @author dhafeur
 */
public class FournisseurList extends BaseForm{
        Button deleteBtn,updateBtn,qrbutton ; 
    public static Fournisseur currentforn = null;
    private Object FournisseurServices;
   
    public FournisseurList(Resources res ) {
          super("Fournisseurs", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste de fournisseur");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
 
        });
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("test.jpg"), spacer1, " ", "", "Voici Tous nos Fournissseurs. ");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0x000000);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0x000000);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Tous Les Fournisseurs", barGroup);
        all.setUIID("SelectBar");
       
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, all),
                FlowLayout.encloseBottom(arrow)
        ));
        
        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
       
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
            /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Fournisseur> rdvs = FournisseurServies.getInstance().AffichageFournisseur();
        for (Fournisseur t : rdvs) {
            addElement(t , res);
        }
            
      
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();
        
        
    }
    
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            FlowLayout.encloseIn(likes, comments),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
   private void addButton(Image img,String nom, String prenom, String email,int tel ) {
       int height = Display.getInstance().convertToPixels(11.5f);
       int width = Display.getInstance().convertToPixels(14f);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
       cnt.setLeadComponent(image);
        String title1l =nom ;
       TextArea taa =  new TextArea(title1l);
       taa.setUIID("NewsTopLine");
       taa.setEditable(false);
           String title1l1 =prenom ;
       TextArea taaa =  new TextArea(title1l1);
       taaa.setUIID("NewsTopLine");
       taaa.setEditable(false);
           String title1l2 =email ;
       TextArea t =  new TextArea(title1l2);
       t.setUIID("NewsTopLine");
       t.setEditable(false);
     
       Label likes = new Label(nom + "", "NewsBottomLine");
       likes.setTextPosition(RIGHT);
       Label likes1 = new Label(prenom + "", "NewsBottomLine");
       likes.setTextPosition(LEFT);
  
       Label comments = new Label(email + "", "");
       FontImage.setMaterialIcon(likes, FontImage.MATERIAL_CHAT);
       
       
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       taa,taaa,t
                       
               ));
       add(cnt);
       image.addActionListener(e -> ToastBar.showMessage(title1l, FontImage.MATERIAL_INFO));
   }
    
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if(b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    
       public void addElement(Fournisseur forn , Resources res) {

        
        addButton(res.getImage("test.jpg"), forn.getNom(),  forn.getPrenom(), forn.getEmail(),forn.getTel());
        
     deleteBtn = new Button("supprimer") ;
     updateBtn = new Button("Modifier") ;
     qrbutton = new Button("QR Code") ;
     updateBtn.addPointerPressedListener(l -> {
        MyApplication.forn= forn;
        new UpdateForm(res).show();
});   
        deleteBtn.addPointerPressedListener(l -> {

    Dialog dig = new Dialog("Suppression");

    if (dig.show("Suppression", "Vous voulez supprimer cette plat ?", "Annuler", "Oui")) {
        dig.dispose();
    } else {
        dig.dispose();
    }

    // appel au service de suppression de la réclamation
    if (FournisseurServies.getInstance().deleteFournisseur(forn.getId())) {
        // si la suppression est réussie, on affiche à nouveau la page des plats
        new FournisseurList(res).show();
    }
});    
qrbutton.addActionListener(e -> {
    try {
        // Call the generateQRCode function with the required parameters
        Image qrCodeImage = generateQRCode(forn.getNom(),forn.getPrenom(),forn.getEmail(),forn.getTel(), 500, 500);

        // Create an EncodedImage from the QR code image
        EncodedImage qrCodeEncodedImage = EncodedImage.createFromImage(qrCodeImage, false);

        // Create a Label with the QR code image
        Label qrCodeLabel = new Label(qrCodeEncodedImage);

        // Create a Container with a BorderLayout and add the Label to it
        Container qrCodeContainer = new Container(new BorderLayout());
        qrCodeContainer.add(BorderLayout.CENTER, qrCodeLabel);

        // Create a Button with the text "Cancel"
        Button cancelButton = new Button("Cancel");

        // Create a Dialog with the title "QR Code" and add the Container and the Button to it
        Dialog qrCodeDialog = new Dialog("QR Code");
        qrCodeDialog.setLayout(new BorderLayout());
        qrCodeDialog.add(BorderLayout.CENTER, qrCodeContainer);
        qrCodeDialog.add(BorderLayout.SOUTH, cancelButton);

        // Add an ActionListener to the cancelButton to close the Dialog
        cancelButton.addActionListener(evt -> qrCodeDialog.dispose());

        // Set the Dialog to be modal and show it
      //  qrCodeDialog.setModality(true);
        qrCodeDialog.show();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
});





           add(qrbutton) ;
           add(updateBtn) ; 
           add(deleteBtn) ; 
        

       }
             //QrCode 
public Image generateQRCode(String description,String prix,String calories, int tel, int width, int height) throws WriterException {
    Hashtable hintMap = new Hashtable();
    hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix byteMatrix = qrCodeWriter.encode("Nom : "+description+"\n"+"Prenom : "+prix+"\n"+"Email : "+calories+"\n"+"Tel : "+tel, BarcodeFormat.QR_CODE, width, height, hintMap);

    int matrixWidth = byteMatrix.getWidth();
    int matrixHeight = byteMatrix.getHeight();
    int[] pixels = new int[matrixWidth * matrixHeight];

    // Iterate through the matrix and set the pixel value for each element
    for (int y = 0; y < matrixHeight; y++) {
        int offset = y * matrixWidth;
        for (int x = 0; x < matrixWidth; x++) {
            pixels[offset + x] = byteMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
        }
    }

    // Create the image using the pixel array
    Image image = Image.createImage(pixels, matrixWidth, matrixHeight);

    return image;
} 

}
