/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import networking.GmailClient;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author tuski-Revolve
 */
public class SendMailController implements Initializable {

  
    @FXML
    private TextField to;
    @FXML
    private TextField subject;
    @FXML
    private TextArea message;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       String[] suggest={"If you have any questions, please don't hesitate to contact me.",
           "I look forward to hearing from you","I’m just emailing to ask",
           "Could you look into this?","Look forward to hearing from you","Please accept our/my sincere apologies",
           "Might I take a moment of your time"};
       
       TextFields.bindAutoCompletion(to, suggest);

       
    }    
    
    
    
    public void sendMail(){
       
        GmailClient gmail=new GmailClient( Constants.USERNAME,  Constants.PASSWORD);
        
        gmail.sendGmail(to.getText(), subject.getText(), message.getText());
        
        
    }
    
}
