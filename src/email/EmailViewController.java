/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import networking.GmailClient;

/**
 * FXML Controller class
 *
 * @author tuski-Revolve
 */
public class EmailViewController extends Thread implements Initializable {

    @FXML
    private JFXListView inboxList;

    @FXML
    private ImageView imgView;
    @FXML
    private Label emailnbr;
      @FXML
    public  JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private Button loginButton;

   
 
    
    int inboxLength = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        Image coverImg= new Image("imgResource/gmail.png");
//        imgView.setImage(coverImg);
        // readMail();
        
        
//        Constants.USERNAME = USER;
//        Constants.PASSWORD = PASS;


//        
//        EmailViewController view= new EmailViewController();
//        view.start();

//        checkNewMail chk= new checkNewMail();
//        chk.start();

    }

    @FXML
    void readMail() {
       String USER = usernameField.getText();
      String PASS = passwordField.getText();
   
         Constants.USERNAME = USER;
        Constants.PASSWORD = PASS;

        GmailClient newGmailClient = new GmailClient(USER, PASS);

        newGmailClient.receivingHost = "imap.gmail.com";//for imap protocol

        Properties props2 = System.getProperties();

        //PasswordAuthentication auth = new PasswordAuthentication("", "");
        props2.setProperty("mail.store.protocol", "imaps");

        Session session2 = Session.getInstance(props2, null);

        // Session session2=Session.getDefaultInstance(props2, auth);
        try {

            Store store = session2.getStore("imaps");

            store.connect(newGmailClient.receivingHost, newGmailClient.userName, newGmailClient.password);

            Folder folder = store.getFolder("INBOX");//get inbox

            folder.open(Folder.READ_ONLY);//open folder only to read

            Message message[] = folder.getMessages();
            System.out.println(message.length);
           // inboxLength = message.length;
            int i = message.length - 1;
            emailnbr.setText("("+(i+1)+")");
            int j = 0;
            //Connection conn =  getConnection();
            while (j < 10 && i >= 0) {
                String from = message[i].getFrom()[0].toString();
                String date = message[i].getReceivedDate().toString();
                String subject = message[i].getSubject();
                // String msg= message[i].getContent().toString();
//                StringBuilder s= new StringBuilder();
//                s.append(from);
//                s.append(System.lineSeparator());
//                s.append(date);
//                s.append(System.lineSeparator());
//                s.append(subject);
//                System.out.println(s);
                //print subjects of all mails in the inbox
                Label lbl = new Label(subject);
                inboxList.getItems().add(lbl);
//                System.out.println(subject)s;

//                  LocalDBManager.insert2Inbox(i, from, date, subject);
                j++;
                i--;

            }

            //close connections
            folder.close(true);

            store.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());

        }

    }

    public void composeMail() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/email/sendMail.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setTitle("Compose Mail");
        stage.show();

    }

}
