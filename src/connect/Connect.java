/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tuski-Revolve
 */
public class Connect extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/main/Login.fxml"));
        primaryStage.setTitle("Connect+");
        primaryStage.setScene(new Scene(root));
        //primaryStage.setFullScreen(false);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
            System.out.println("exit");
        
        });
    }
    //WorkStation2   loginForm  Login   workstation3

    public static void main(String[] args) {
        launch(args);
    }

}
