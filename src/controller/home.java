/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author asd
 */
public class home implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void close(ActionEvent event) {
        System.out.println(Screen.getPrimary());    
       Platform.exit();
    }
      @FXML
    private void swit(ActionEvent event) {
   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   if(stage.isFullScreen()){ 
   stage.setFullScreen(false);
   }else{
      stage.setFullScreen(true);
   }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
