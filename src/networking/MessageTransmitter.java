/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package networking;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 *
 * @author manthanhd
 */
public class MessageTransmitter extends Thread {

    String message, hostname;
    int port;
    
    public MessageTransmitter() {
    }

    public MessageTransmitter(String message, String hostname, int port) {
        BasicTextEncryptor cryptor= new BasicTextEncryptor();
        cryptor.setPassword("tusar099");
        
        this.message = cryptor.encrypt(message);
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket(hostname, port);
            s.getOutputStream().write(message.getBytes());
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MessageTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
