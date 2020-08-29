/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import service.DB;

/**
 *
 * @author asd
 */
public class login implements Initializable {

        @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    
     @FXML
    private void login(ActionEvent event) {
       try {
            allDb.DB_connection();
            System.out.println(allDb.login(Username.getText(), Password.getText()));
            allDb.DB_close();
       } catch (SQLException ex) {
        }
    }
    
    DB allDb;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
           allDb = new DB();
      
    }
    
}
