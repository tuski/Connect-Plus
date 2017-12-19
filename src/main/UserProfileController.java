/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import jdbc.DBManager;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author tuski-Revolve
 */
public class UserProfileController implements Initializable {

    @FXML
    private ImageView profilePicImgLabel;

    @FXML
    private Button uploadPic;

    @FXML
    private TextField nameField;
     @FXML
    private TextField contact;
 @FXML
    private ComboBox gender;
     
    @FXML
    private TextField address;
    private Image image;
    private InputStream is;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         File im= new File("d:/img/"+LoginController.userName+".jpg");
             
        Image prPic= new Image(im.toURI().toString());
        profilePicImgLabel.setImage(prPic); 
      
        gender.getItems().addAll(
            "Male",
            "Female"
        );
        
        
        try {
            List<String> userInfo = DBManager.getUserinfo(LoginController.userName);
            nameField.setText(userInfo.get(0));
            address.setText(userInfo.get(1));
            contact.setText(userInfo.get(2));
            gender.setValue(userInfo.get(3));
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
    @FXML
    public void setImage() throws FileNotFoundException{
         FileChooser fc=new FileChooser();
        
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image file", "*.jpg", "*.png"));
        File selectedFile=fc.showOpenDialog(null);
        if(selectedFile!=null)
        {
        image=new Image(selectedFile.toURI().toString(),100,150,true,true);
        profilePicImgLabel.setImage(image);
        is=new FileInputStream((selectedFile));
        }
        
    }
    
    
    public void updateInfo() throws Exception{
        String gendr=gender.getSelectionModel().getSelectedItem().toString();
         System.out.println("success"+gendr);
        DBManager.updateUser(LoginController.userName, nameField.getText().toString(), address.getText(), is, contact.getText(),gendr);
        
       DBManager.getAllPropic();
      
      
        Image img=new Image("imgResource/icons8_Ok_32.png");  //new_mail_32  icons8_Ok_32
        Notifications nt = Notifications.create().title("Success").text("Successfully Updated Your Profile")
                    .hideAfter(javafx.util.Duration.seconds(5)).position(Pos.BASELINE_RIGHT);
            nt.graphic(new ImageView(img));
               nt.show();
    }
    
    
    
    
    
    
}
