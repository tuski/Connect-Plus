package main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import jdbc.DBManager;
import networking.FileClient;
import networking.FileServer;
import networking.MessageListener;
import networking.MessageTransmitter;
import networking.WritableGUI;
import org.controlsfx.control.textfield.TextFields;
import org.imgscalr.Scalr;

/**
 * Created by tuski-Revolve on 26-Jul-17.
 */
public class ChatLayout implements WritableGUI {

    @FXML
    private JFXListView chatFriendList;

    @FXML
    private TextArea chatBox;

    @FXML
    private JFXTextField sendMessage;

    @FXML
    private JFXButton sendMsg;
    @FXML
    private JFXTextField serverPort;
    @FXML
    private JFXTextField serverFilePort;
    @FXML
    private JFXTextField clientPort;
    @FXML
    private JFXTextField clientFilePort;

    private final String hostName = "127.0.0.1";

    public void initialize() throws SQLException, FileNotFoundException, IOException {
        
        
        String[] suggest = {"How are you?", "I'm Fine", "I'm busy", "Bye now", "Hi", "Hey", "Hellow"};
        TextFields.bindAutoCompletion(sendMessage, suggest);

        HashMap<String, String> userlist = DBManager.showFriendList();

        Set set = userlist.entrySet();
        Iterator iterator = set.iterator();
        //Map.Entry mentry = (Map.Entry)iterator.next();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            String name = mentry.getKey().toString();
            if (name.equals(LoginController.userName)) {
                continue;
            } else {
                Label lbl = new Label(name);
                //Image img = ImageIO.read(new File("d:/img/"+name+".jpg")).getScaledInstance(100, 100, BufferedImage.SCALE_SMOOTH);
            BufferedImage img = ImageIO.read(new File("d:/img/"+name+".jpg")); // load image
        BufferedImage scaledImg = Scalr.resize(img, 50);
        Image image = SwingFXUtils.toFXImage(scaledImg, null);
            lbl.setGraphic(new ImageView(image));
                chatFriendList.getItems().add(lbl);

                // System.out.println(mentry.getKey()+" ip= "+mentry.getValue());
            }
        }
        chatFriendList.depthProperty().set(1);

    }

    @FXML
    public void sendMessage() {
        System.out.println("value is ");
        String msg = "(" + LoginController.userName + ") " + sendMessage.getText();
        MessageTransmitter sendMsg
                = new MessageTransmitter(msg, hostName, Integer.parseInt(clientPort.getText()));

        sendMsg.start();

        chatBox.appendText(msg + System.lineSeparator());
        sendMessage.setText("");
    }

    @FXML
    public void sendImageFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.ico"));
        File selectedFile = fc.showOpenDialog(null);
        long filelength = selectedFile.length();
       
        FileClient sendFile = new FileClient("127.0.01", Integer.parseInt(clientFilePort.getText()), selectedFile.getAbsolutePath(), selectedFile.getName(), filelength);
         System.out.println(filelength);
        System.out.println(selectedFile.getAbsolutePath());
        chatBox.appendText("Sending File " + selectedFile.getName() + System.lineSeparator());
        sendMessage.setText("");

    }

    @FXML
    public void sendVideoFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mkv"));
        File selectedFile = fc.showOpenDialog(null);
        long filelength = selectedFile.length();
        FileClient sendFile = new FileClient("127.0.01", Integer.parseInt(clientFilePort.getText()), selectedFile.getAbsolutePath(), selectedFile.getName(), filelength);
        System.out.println(selectedFile.getAbsolutePath());
        chatBox.appendText("Sending File " + selectedFile.getName() + System.lineSeparator());
        sendMessage.setText("");

    }

    @FXML
    public void sendFile() {
        FileChooser fc = new FileChooser();
        // fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg","*.png","*.ico"));
        File selectedFile = fc.showOpenDialog(null);
        long filelength = selectedFile.length();
        FileClient sendFile = new FileClient("127.0.01", Integer.parseInt(clientFilePort.getText()), selectedFile.getAbsolutePath(), selectedFile.getName(), filelength);
        System.out.println(selectedFile.getAbsolutePath());
        chatBox.appendText("Sending File " + selectedFile.getName() + System.lineSeparator());
        sendMessage.setText("");

    }

    @Override
    public void write(String s) {
        chatBox.appendText(s + System.lineSeparator());
    }

    public void StartServer() {
        MessageListener listn;
        FileServer startFileServer = null;

        try {
            listn = new MessageListener(this, Integer.parseInt(serverPort.getText()));
            listn.start();
            startFileServer = new FileServer( Integer.parseInt(serverFilePort.getText()));
            startFileServer.start();

        } catch (Exception ex) {

            // Logger.getLogger(ChatLayout.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
