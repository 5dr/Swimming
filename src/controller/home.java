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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    private Label l_g;
    @FXML
    private VBox pnItems;
    @FXML
    private HBox itemC;
    
     @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOverview;

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
            l_g.setText(t.size()+"");

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

        l1.setPrefSize(80, 50);
        l2.setPrefSize(50, 50);
        l3.setPrefSize(20, 50);
        l4.setPrefSize(40, 50);
        l5.setPrefSize(80, 50);
        
        HBox h = new HBox();
        h.setPrefSize(600, 300);
        h.setAlignment(Pos.CENTER_LEFT);
        h.setSpacing(80);
        h.setStyle("-fx-background-color : #02030A");

          h.setOnMouseEntered(event -> {
                    h.setStyle("-fx-background-color : #0A0E3F");
                });
                h.setOnMouseExited(event -> {
                    h.setStyle("-fx-background-color : #02030A");
                });
        h.getChildren().add(l1);
        h.getChildren().add(l2);
        h.getChildren().add(l3);
        h.getChildren().add(l4);
        h.getChildren().add(l5);
        pnItems.getChildren().add(h);
    }


   public void swi(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==btnOrders)
        {
            pnlOrders.setStyle("-fx-background-color : #464F67");
            pnlOrders.toFront();
        }
    }

}
