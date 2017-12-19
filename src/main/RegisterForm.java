package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.net.UnknownHostException;
import java.sql.SQLException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import jdbc.DBManager;
import org.controlsfx.control.Notifications;


/**
 * Created by tuski-Revolve on 28-Jun-17.
 */
public class RegisterForm {

 
    @FXML
    private TextField registerUserName;

    @FXML
    private PasswordField registerPassword;

    @FXML
    private TextField registerEmail;
    @FXML
    private AnchorPane mainLayout;
    @FXML
    private Button loginBtn;
    private InputStream is;
   
      @FXML
    public void register() {
        String regUserName = registerUserName.getText().toString();
        String regPassword = registerPassword.getText().toString();
        String regEmail = registerEmail.getText().toString();
         Image img=new Image("imgResource/icons8_Ok_32.png");
         Notifications ntf = Notifications.create().title("Action").text("Registration Successful")
                    .hideAfter(javafx.util.Duration.seconds(5)).position(Pos.BASELINE_RIGHT);
            ntf.graphic(new ImageView(img));
            
        try {
            File defaultImage=new File("src/imgResource/person_32.png");
            is=new FileInputStream((defaultImage));
            DBManager.registerUser(regUserName, regEmail, regPassword,is);
              
            
               ntf.show();
            //System.out.print("Registered");

        } catch (SQLException e) {
            System.out.print("Use different username");
            System.out.println(e.toString());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
