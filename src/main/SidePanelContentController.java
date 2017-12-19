package main;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.WorkStation2;


public class SidePanelContentController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;
    @FXML
    private JFXButton b11;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void changeColor(ActionEvent event) throws IOException {
        JFXButton btn = (JFXButton) event.getSource();
        System.out.println(btn.getText());
        switch(btn.getText())
        {
            case "Chat":loadChatLayout();
                break;
            case "Friends":loadFrindsLayout();
                break;
            case "Profile":loadProfile();
                break;
             case "Settings":loadSettings();
        }
    }

    public  void loadChatLayout() throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/main/ChatLayout.fxml"));
        WorkStation2.rootP.getChildren().clear();
        WorkStation2.rootP.getChildren().setAll(pane);//   setAll(pane);
        WorkStation2.drawerP.close();
        WorkStation2.rootP.requestFocus();
        //WorkStation2.drawerP.mouseTransparentProperty();
        //WorkStation2.drawerP.prefWidth(0);
    }
    public  void loadFrindsLayout() throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/main/friendsLayout.fxml"));
        WorkStation2.rootP.getChildren().clear();
        WorkStation2.rootP.getChildren().setAll(pane);
        WorkStation2.drawerP.close();
       // WorkStation2.drawerP.prefWidth(0);
    }
    
     public  void loadProfile() throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/main/userProfile.fxml"));
        WorkStation2.rootP.getChildren().clear();
        WorkStation2.rootP.getChildren().setAll(pane);
        WorkStation2.drawerP.close();
     //   WorkStation2.drawerP.prefWidth(0);
    }
        public  void loadSettings() throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/main/settings.fxml"));
        WorkStation2.rootP.getChildren().clear();
        WorkStation2.rootP.getChildren().setAll(pane);
        WorkStation2.drawerP.close();
     //   WorkStation2.drawerP.prefWidth(0);
    }
    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
}
