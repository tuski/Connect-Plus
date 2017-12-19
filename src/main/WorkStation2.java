package main;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import jdbc.LocalDBConnector;

public class WorkStation2 {


    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane mailPane;
    @FXML
    private BorderPane brdrPane;

    @FXML
     AnchorPane connectProot;

    @FXML
    private JFXListView chatFriendList;

    public static AnchorPane rootP;
    public static JFXDrawer drawerP;

    public void initialize() throws SQLException, IOException {
        rootP = connectProot;
        drawerP = drawer;

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/main/ChatLayout.fxml"));
        
        rootP.getChildren().setAll(pane);
        AnchorPane EmailPane = FXMLLoader.load(getClass().getResource("/email/emailView.fxml"));
        mailPane.getChildren().setAll(EmailPane);

        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                    
                } else {
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(WorkStation2.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }

   

}
