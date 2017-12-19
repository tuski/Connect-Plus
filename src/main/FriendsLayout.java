package main;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import jdbc.DBManager;
import jdbc.Userinfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created by tuski-Revolve on 25-Jul-17.
 */
public class FriendsLayout implements Initializable {

    @FXML
    private TextField friendSearch;

    @FXML
    private ListView friendList;
    @FXML
    private ImageView propic;

    @FXML
    private Label name;

    @FXML
    private Label gender;

    @FXML
    private Label contact;

    @FXML
    private Label address;

    @FXML
    private Label email;

    String userName;

    List<String> friendDetails = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        friendList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.out.println(friendList.getSelectionModel().getSelectedItem());
                userName = friendList.getSelectionModel().getSelectedItem().toString();
                Userinfo user = new Userinfo();
                SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
                Session session = sessionFactory.openSession();
                session.beginTransaction();

                try {
                    user = (Userinfo) session.get(Userinfo.class, userName);
                    if (user.getPropic() != null) {
                        BufferedImage img = ImageIO.read(new ByteArrayInputStream(user.getPropic()));
                        Image image = SwingFXUtils.toFXImage(img, null);
                        propic.setImage(image);
                    }
                    friendDetails = DBManager.getUserinfo(userName);
                    setDetail();
                } catch (SQLException ex) {
                    Logger.getLogger(FriendsLayout.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FriendsLayout.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        try {
            HashMap<String, String> userlist = DBManager.showFriendList();

            Set set = userlist.entrySet();
            Iterator iterator = set.iterator();
            //Map.Entry mentry = (Map.Entry)iterator.next();
            while (iterator.hasNext()) {
                Map.Entry mentry = (Map.Entry) iterator.next();
                friendList.getItems().add(mentry.getKey());
                System.out.println(mentry.getKey() + " ip= " + mentry.getValue());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FriendsLayout.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setDetail() {

        name.setText(friendDetails.get(0));
        address.setText(friendDetails.get(1));
        contact.setText(friendDetails.get(2));
        gender.setText(friendDetails.get(3));
        email.setText(friendDetails.get(4));
    }

}
