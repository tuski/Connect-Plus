/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author tuski-Revolve
 */
public class ProfileInfoController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }    
    
}
