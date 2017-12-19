/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.ChatLayout;

import org.jasypt.util.text.BasicTextEncryptor;

public class MessageListener extends Thread {

    ServerSocket server;
    int port = 8877;
    WritableGUI gui;

    public MessageListener(WritableGUI gui, int port) {
        this.port = port;
        this.gui = gui;

        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MessageListener() {
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Socket clientSocket;
        BasicTextEncryptor cryptor = new BasicTextEncryptor();
        cryptor.setPassword("tusar099");

        try {
            while ((clientSocket = server.accept()) != null) {
                InputStream is = clientSocket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = br.readLine();
                if (line != null) {
                    //  ChatLayout.chatBox.appendText(cryptor.decrypt(line)+ System.lineSeparator());
                    //gui.write(line);
                    gui.write(cryptor.decrypt(line));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
