/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package email;

import java.util.Properties;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import networking.GmailClient;
import org.controlsfx.control.Notifications;

/**
 *
 * @author tuski-Revolve
 */
public class checkNewMail extends Thread {

    int inboxLength = 0;

   // public static String USER = "";
    //public static String PASS = "";

    @Override
    public void run() {

        GmailClient newGmailClient = new GmailClient( Constants.USERNAME,  Constants.PASSWORD);

        newGmailClient.receivingHost = "imap.gmail.com";//for imap protocol

        Properties props2 = System.getProperties();
        props2.setProperty("mail.store.protocol", "imaps");
        Session session2 = Session.getInstance(props2, null);
        try {

            Store store = session2.getStore("imaps");
            store.connect(newGmailClient.receivingHost, newGmailClient.userName, newGmailClient.password);
            Folder folder = store.getFolder("INBOX");//get inbox
            folder.open(Folder.READ_ONLY);//open folder only to read

            while (true) {
                Message message[] = folder.getMessages();
                System.out.println(message.length);
                if (message.length > inboxLength) {
                    int numberOfMail = message.length - inboxLength;
                    Notifications nt = Notifications.create().title("New Mail").text("You have " + numberOfMail + " New Mails")
                            .hideAfter(javafx.util.Duration.seconds(5)).position(Pos.BASELINE_RIGHT);
                    //nt.graphic(new ImageView(img));
                    inboxLength = message.length;
                    Platform.runLater(
                            () -> {
                                // Update UI here.
                                nt.showConfirm();
                            }
                    );

                } else {
                    Thread.sleep(5000);
                }
                //folder.close(true);

//            store.close();
            }
            //  try{Thread.sleep(5000);}catch(InterruptedException e){System.out.println(e);}  

            // close connections
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());

        }

    }
}
