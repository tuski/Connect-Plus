/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import email.Constants;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import jdbc.DBManager;

/**
 * FXML Controller class
 *
 * @author tuski-Revolve
 */
public class Workstation3Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane connectProot;
    @FXML
    private AnchorPane mailPane;
   
     @FXML
    private Label loginName;
     
     @FXML
     private  ImageView propic;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        try {
            //  pane = null;
            DBManager.getAllPropic();
             //System.out.println("d:/img/"+LoginController.userName+".jpg");
             
             
             File im= new File(Constants.PROPICLINK);
             
        Image prPic= new Image(im.toURI().toString());
//         BufferedImage img = ImageIO.read(new File("d:/img/"+LoginController.userName+".jpg")); // load image
//        BufferedImage scaledImg = Scalr.resize(img, 800);
//        Image prPic = SwingFXUtils.toFXImage(scaledImg, null);
      
         propic.setImage(prPic); 
         //propic.setFitWidth(700);
        } catch (SQLException ex) {
            Logger.getLogger(Workstation3Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Workstation3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
         AnchorPane   pane = FXMLLoader.load(getClass().getResource("/main/ChatLayout.fxml"));
                    connectProot.getChildren().setAll(pane);
        AnchorPane EmailPane = FXMLLoader.load(getClass().getResource("/email/emailView.fxml"));
        mailPane.getChildren().setAll(EmailPane);

        } catch (IOException ex) {
            Logger.getLogger(Workstation3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println(LoginController.userName);
    
         loginName.setText("Logged in as: "+LoginController.userName);
       
          
         
    }    
    
    

    
    @FXML
    public  void loadChatLayout() throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/main/ChatLayout.fxml"));
        connectProot.getChildren().clear();
        connectProot.getChildren().setAll(pane);//   setAll(pane);
       
    }
    @FXML
    public  void loadFrindsLayout() throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/main/friendsLayout.fxml"));
       connectProot.getChildren().clear();
       connectProot.getChildren().setAll(pane);
    
    }
    
    @FXML
     public  void loadProfile() throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/main/userProfile.fxml"));
        connectProot.getChildren().clear();
        connectProot.getChildren().setAll(pane);
      
    }
    @FXML
        public  void loadSettings() throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/main/settings.fxml"));
        connectProot.getChildren().clear();
        connectProot.getChildren().setAll(pane);
        WorkStation2.drawerP.close();
  
    }
    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
}
