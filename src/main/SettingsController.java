/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import networking.FileServer;
import networking.MessageListener;
import networking.WritableGUI;

/**
 * FXML Controller class
 *
 * @author tuski-Revolve
 */
public class SettingsController implements Initializable {

    @FXML
    private TextField serverMsgPort;

    @FXML
    private TextField serverFilePort;

    @FXML
    private TextField clientMsgPort;

    @FXML
    public static TextField clientFilePort;

//    public static int clientmsgPort;
//     public static int clientfilePort;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void StartServer() throws IOException {
//        MessageListener listn;
//        listn = new MessageListener(ChatLayout.gui, Integer.parseInt(serverMsgPort.getText()));
//        listn.start();
//        FileServer startFileServer;
//        startFileServer = new FileServer(Integer.parseInt(serverFilePort.getText()));
//        startFileServer.start();

        //ChatLayout.clientmsgPort = Integer.parseInt(clientMsgPort.getText());
        //ChatLayout.clientfilePort=Integer.parseInt(clientFilePort.getText());

        //System.out.println(" inside sett value is " + ChatLayout.clientmsgPort);
    }

}
