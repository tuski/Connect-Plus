/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jdbc.DBManager;
import static jdbc.DBManager.loginUser;

import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Saddam Hossain
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
      @FXML
    private TextField logUserName;
//    String logUserName=loginUserName.getText().toString();
    @FXML
    private PasswordField logPassword;
    @FXML
    private Button loginBtn;
    
    public static String userName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
    }    
    
    
      @FXML
    public void login() {
        Stage thisStage = (Stage) loginBtn.getScene().getWindow();
        String loginUserName = logUserName.getText().toString();
        String loginPassword = logPassword.getText().toString();
            userName=loginUserName;
            System.out.println(userName);
            Image img=new Image("imgResource/icons8_Ok_32.png");  //new_mail_32  icons8_Ok_32
        try {
            boolean reply = DBManager.loginUser(loginUserName, loginPassword);
            if (reply == true) {
                System.out.print("Login succesful!");
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/workstation3.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Connect+");
               // stage.setHeight(500);
                //stage.setWidth(700);
                //userName=loginUserName;
                stage.show();
                
               Notifications nt = Notifications.create().title("Action").text("Login Successful")
                    .hideAfter(javafx.util.Duration.seconds(5)).position(Pos.BASELINE_RIGHT);
            nt.graphic(new ImageView(img));
               nt.show();
                thisStage.close();}
             
                
        else {
               Notifications nt = Notifications.create().title("Action").text("Wrong Username or Password")
                    .hideAfter(javafx.util.Duration.seconds(5)).position(Pos.BASELINE_RIGHT);
            nt.showError();
            }
           
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        thisStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
            System.out.println("exit");
        
        });

    }
    
    
    public void showReg() throws IOException{
        
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/registerForm.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Register");
                stage.show();
        
    }
    
}
