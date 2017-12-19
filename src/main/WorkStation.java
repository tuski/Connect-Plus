package main;

import java.io.File;
import java.io.IOException;
import jdbc.DBManager;
import networking.MessageListener;
import networking.MessageTransmitter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import java.util.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import networking.FileClient;
import networking.FileServer;
import networking.WritableGUI;
import org.controlsfx.control.textfield.TextFields;

public class WorkStation implements WritableGUI{


    @FXML
    private TextField searchField;
    @FXML
    private TextField port;

    @FXML
    private ListView friendList;

    @FXML
    private TextArea chatBox;

    @FXML
    private TextField chatText;
    
    
    ////////////////////////////////
    @FXML
    private static AnchorPane rootScene;
    @FXML
    private BorderPane brdrPane;
    private Stage primaryStage;
    
    
    @FXML
    private Button sendButton;
    
    @FXML
    public static ImageView friendsPic;
    @FXML
    public static Label friendName;
   
    private  String hostName="127.0.0.1";
    HashMap<String, String> userlist= DBManager.showFriendList();
   
    @FXML
    private TextField serverPort;
    @FXML
    private TextField fileServerPort;
    @FXML
    private TextField fileClientPort;

    public WorkStation() throws SQLException {
    }

    public void initialize() throws SQLException, IOException {
String[] suggest={"How are you?", "I'm Fine","I'm busy","Bye now","Hi","Hey","Hellow"};
        TextFields.bindAutoCompletion(chatText, suggest);
        
          //  userlist.forEach((name) -> friendList.getItems().add(name));
         brdrPane.setLeft(port);
        Set set = userlist.entrySet();
        Iterator iterator = set.iterator();
        //Map.Entry mentry = (Map.Entry)iterator.next();
        while(iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry)iterator.next();
            friendList.getItems().add(mentry.getKey());
            System.out.println(mentry.getKey()+" ip= "+mentry.getValue());
           }

     

    }


   


    @FXML
    public void sendMessage(){
        
        String msg="("+LoginController.userName+") "+chatText.getText();
        MessageTransmitter sendMsg=
                new MessageTransmitter(msg,hostName,Integer.parseInt(serverPort.getText()));

            sendMsg.start();
            
            chatBox.appendText(msg+System.lineSeparator());
            chatText.setText("");            
    }
    
    
      @FXML
    public void sendFile(){
        FileChooser fc=new FileChooser();
        File selectedFile=fc.showOpenDialog(null);
        long filelength= selectedFile.length();
         FileClient sendFile= new FileClient("localhost", Integer.parseInt(fileClientPort.getText()), selectedFile.getAbsolutePath(),selectedFile.getName(),filelength);
           System.out.println(selectedFile.getAbsolutePath());
            chatBox.appendText("Sending File "+selectedFile.getName()+System.lineSeparator());
            chatText.setText("");
            

    }

    @FXML
    public void getUserIP() throws SQLException, IOException{

      String user= friendList.getSelectionModel().getSelectedItem().toString();
        System.out.println(user+" ip= "+userlist.get(user));
        //hostName=userlist.get(user);
        DBManager.getUserinfo(LoginController.userName);
        
    }

    @FXML
    public  void listen() throws IOException{
        MessageListener listn=new MessageListener(this,Integer.parseInt(port.getText()));
        listn.start();
        FileServer startFileServer;
        startFileServer = new FileServer(Integer.parseInt(fileServerPort.getText()));
        startFileServer.start();
//startFileServer.interrupt();
    }
    
     public  void loadChatLayout() throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/main/ChatLayout.fxml"));
        WorkStation.rootScene.getChildren().setAll(pane);//   setAll(pane);
        
    }
    public  void loadFrindsLayout() throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/main/friendsLayout.fxml"));
       WorkStation.rootScene.getChildren().clear();
        WorkStation.rootScene.getChildren().addAll(pane);
       
    }
    
     public  void loadProfile() throws IOException {
      // rootScene= FXMLLoader.load(getClass().getResource("/main/userProfile.fxml"));
       // WorkStation.rootScene.getChildren().addAll(rootScene);
//         rootScene= FXMLLoader.load(getClass().getResource("/main/userProfile.fxml"));
//       Scene scene = new Scene(rootScene);
//            primaryStage.setScene(scene);
//            primaryStage.show();
       
 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main/userProfile.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.setTitle("Profile");
                stage.show();
              stage.setOnCloseRequest(e -> {
                  Platform.exit();
                  System.exit(0);
              });
    }
    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void write(String s) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
