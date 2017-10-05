/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spades;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author carlosespejo
 */
public class StartMenuController implements Initializable {
    
    /**
     * Intitialize variables
     */
    @FXML
    private Button startButton;
    
    @FXML
    private Button exitButton;
    
    Stage stage = new Stage();
    
    /**
     * Begin methods
     */
    
    @FXML
    private void exit(){
        stage = (Stage) exitButton.getScene().getWindow();
                    stage.close();
    }
    
    @FXML
    private void startGame() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("mainView.fxml"));
        
        Scene scene = new Scene(root);
        
        //get the stage we are on from button
        stage = (Stage) startButton.getScene().getWindow();

        //set stage to new scene
        stage.setScene(scene);
        
        //show the new scene on the stage
        stage.show();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
