/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.overview;
import service.DB;

/**
 *
 * @author asd
 */
public class home implements Initializable {

    @FXML
    private Label label;
    @FXML
    private VBox pnItems;
    @FXML
    private HBox itemC;

    @FXML
    private void close(ActionEvent event) {
        System.out.println(Screen.getPrimary());
        Platform.exit();
    }

    @FXML
    private void swit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }
    }
    DB allDb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<overview> t = new ArrayList<overview>();
            int day = 5;
            Date now = new Date();
            SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week abbreviated
            System.out.println(simpleDateformat.format(now));
            if (simpleDateformat.format(now).equals("الاثنين")) {
                day = 0;
            }
            allDb = new DB();
            allDb.DB_connection();
            t = allDb.over(day);
            allDb.DB_close();

            for (int i = 0; i < t.size(); i++) {
                buildover(t.get(i));
            }
        } catch (SQLException ex) {

            System.out.println("controller home: "+ex );
        }

    }

    private void buildover(overview t) {

        Label l1 = new Label(t.getCouch());
        Label l2 = new Label(t.getG_id()+"");
        Label l3 = new Label(t.getLevel());
        Label l4 = new Label(t.getTrack());
        Label l5 = new Label(t.getG_time()+"");

        l1.setTextFill(Color.WHITE);
        l2.setTextFill(Color.WHITE);
        l3.setTextFill(Color.WHITE);
        l4.setTextFill(Color.WHITE);
        l5.setTextFill(Color.WHITE);

        l1.setPrefSize(80, 20);
        l2.setPrefSize(50, 20);
        l3.setPrefSize(20, 20);
        l4.setPrefSize(40, 20);
        l5.setPrefSize(80, 20);
        
        HBox h = new HBox();
        h.setPrefSize(600, 53);
        h.setAlignment(Pos.CENTER_LEFT);
        h.setSpacing(80);
        h.setStyle("-fx-background-color: #000000; -fx-background-radius: 5; -fx-background-insets: 0;");

        h.getChildren().add(l1);
        h.getChildren().add(l2);
        h.getChildren().add(l3);
        h.getChildren().add(l4);
        h.getChildren().add(l5);
        pnItems.getChildren().add(h);
    }

}
