/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.rgb;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.attend_couch;
import model.coach;
import model.overview;
import model.swimmer;
import service.DB;

/**
 *
 * @author asd
 */
public class home implements Initializable {

    @FXML
    private Label l_g, l_allcoach;
    @FXML
    private VBox v_s_attend,pnItems,v_attend;
    @FXML
    private HBox itemC,hBox_allgroup;

    @FXML
    private Button btnOverview, btnOrders, btnCustomers, btnMenus, btnPackages, btnSettings, btnSignout;

    @FXML
    private Pane p_allSwimmer,p_C_add,p_s_add,information_swimmer,information_coach, p_allCoach, p_allcoach, p_allswimmer, pnlMenus, pnlOverview, swimmer_group, p_allswimerOfgroup;

    @FXML
    private JFXButton curr_couch,add_coach;

    @FXML
    private JFXTextField add_C_name, add_C_phone, add_C_gender, add_C_adress;

    @FXML
    private JFXTextField add_s_name, add_s_adress, add_s_phone, add_s_age;

    @FXML
    private JFXComboBox<String> add_s_gender;
    @FXML
    private JFXComboBox<Integer> add_s_group;

    @FXML
    private TextField inf_c_name, inf_c_id, inf_c_address, inf_c_level, inf_c_phone, inf_s_name, inf_s_id, inf_s_address, inf_s_phone, inf_s_age, inf_s_group, inf_s_gender, inf_s_start_day, inf_s_end_day;
    
    public void addcoach(ActionEvent actionEvent) {
       
        try {
            allDb.DB_connection();
            allDb.addcoach(add_C_name.getText(), add_C_phone.getText(), add_C_adress.getText(), add_C_gender.getText());
            allDb.DB_close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void addswimmer(ActionEvent actionEvent) {
//        add_s_age.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
//                    vehiclePrice_TextField.setText(oldValue);
//                }
//            }
//
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });

        try {
            allDb.DB_connection();
            allDb.addswimmer(add_s_name.getText(), add_s_adress.getText(), Integer.parseInt(add_s_age.getText()), add_s_gender.getValue(), add_s_phone.getText(), add_s_group.getValue());
            allDb.DB_close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void swi(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnCustomers) {
            //   pnlCustomer.setStyle("-fx-background-color : #1620A1");
            p_allcoach.toFront();
        }
         if (actionEvent.getSource() == add_coach) {
            //   pnlCustomer.setStyle("-fx-background-color : #1620A1");
            p_C_add.toFront();
        }
        if (actionEvent.getSource() == btnMenus) {
            pnlMenus.setStyle("-fx-background-color : #53639F");
            pnlMenus.toFront();
        }
        if (actionEvent.getSource() == btnOverview) {
           // pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        if (actionEvent.getSource() == btnOrders) {
            p_s_add.toFront();
        }
    }

    DB allDb;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            add_s_gender.getItems().addAll("Male", "Female");

//            pnlOverview.setStyle("-fx-background-color : #02030A");
//            pnlOverview.toFront();
            List<overview> t = new ArrayList<overview>();
            List<coach> coach = new ArrayList<coach>();

            int day = 5;
            Date now = new Date();
            SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week abbreviated
            System.out.println(simpleDateformat.format(now));
            if (simpleDateformat.format(now).equals("الاثنين")) {
                day = 0;
            }
            //الثلاثاء,الأربعاء,الجمعة

            allDb = new DB();
            allDb.DB_connection();
            add_s_group.getItems().addAll(allDb.get_All_Id_Of_Group());
            t = allDb.over(0);
            coach = allDb.allcoach();
            allDb.DB_close();

            l_g.setText(t.size() + "");
            l_allcoach.setText(coach.size() + "");
            cardOfCoach(p_allCoach, coach);

            for (int i = 0; i < t.size(); i++) {
                buildover(t.get(i));
            }
        } catch (SQLException ex) {

            System.out.println("controller home: " + ex);
        }

    }

    private void buildover(overview t) {

        Label l1 = new Label(t.getCouch());
        Label l2 = new Label(t.getG_id() + "");
        Label l3 = new Label(t.getLevel());
        Label l4 = new Label(t.getTrack());
        Label l5 = new Label(t.getG_time() + "");

        l1.setTextFill(rgb(42, 115, 255));
        l2.setTextFill(rgb(42, 115, 255));
        l3.setTextFill(rgb(42, 115, 255));
        l4.setTextFill(rgb(42, 115, 255));
        l5.setTextFill(rgb(42, 115, 255));

        l1.setPrefSize(80, 50);
        l2.setPrefSize(50, 50);
        l3.setPrefSize(20, 50);
        l4.setPrefSize(40, 50);
        l5.setPrefSize(80, 50);

        l1.setFont(javafx.scene.text.Font.font("System Bold", 13));
        l2.setFont(javafx.scene.text.Font.font("System Bold", 18));
        l3.setFont(javafx.scene.text.Font.font("System Bold", 18));
        l4.setFont(javafx.scene.text.Font.font("System Bold", 18));
        l5.setFont(javafx.scene.text.Font.font("System Bold", 18));

        HBox h = new HBox();
        h.setPrefSize(600, 300);
        h.setAlignment(Pos.CENTER_LEFT);
        h.setSpacing(80);
        h.setStyle("-fx-background-color : #dddddd");

        h.setOnMouseEntered(event -> {
            h.setStyle("-fx-background-color : #000");
        });
        h.setOnMouseExited(event -> {
            h.setStyle("-fx-background-color : #dddddd");
        });
        h.getChildren().add(l1);
        h.getChildren().add(l2);
        h.getChildren().add(l3);
        h.getChildren().add(l4);
        h.getChildren().add(l5);

        h.setOnMouseClicked((event) -> {
            try {
                p_allswimerOfgroup.getChildren().clear();
                List<swimmer> swimmer = new ArrayList<swimmer>();
                allDb.DB_connection();
                swimmer = allDb.swimmerWithgroup(t.getG_id());
                allDb.DB_close();
                cardOfswimmer(p_allswimerOfgroup, swimmer, t.getCouch());
                swimmer_group.toFront();

            } catch (SQLException ex) {
                System.out.println("biuld over :" + ex);
            }
        }
        );
        pnItems.getChildren().add(h);
    }

    private void cardOfCoach(Pane p, List<coach> coach) throws SQLException {

        double lengh = coach.size();
        //  double lengh =3;
        int count = 0;
        int h = (int) Math.ceil(lengh / 2);
        int def = h - 3;

        if (def > 0) {
            p.setPrefHeight(p.getPrefHeight() + def * 300);
        }

        int colmn = (int) ((lengh < 4) ? lengh : 3);
        int row = (int) (Math.ceil(lengh / 3));
        int mod = (int) (lengh - ((int) Math.floor(lengh / 3) * 3));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colmn; j++) {
                if (i == row - 1 && j > mod - 1 && mod != 0) {
                } else {
                    Image image = new Image(getClass().getResourceAsStream("/images/icons8-coach-96.png"));
                    ImageView Iview = new ImageView(image);
                    Iview.setFitHeight(100);
                    Iview.setFitWidth(150);
                    Iview.setPickOnBounds(true);
                    Iview.setPreserveRatio(true);

                    JFXButton l = new JFXButton();
                    l.setButtonType(JFXButton.ButtonType.RAISED);
                    l.setContentDisplay(ContentDisplay.TOP);
                    l.setLayoutX(j * 250 + 35);
                    l.setLayoutY(i * 300 + 10);
                    l.setPrefHeight(280);
                    l.setPrefWidth(200);
                    l.setStyle("-fx-background-color:  #5e6066;");
                    l.setTextFill(rgb(0, 0, 0));
                    l.setGraphic(Iview);
                    l.setFont(javafx.scene.text.Font.font("System Bold", 18));
                    l.setText("Id : " + coach.get(count).getC_id()
                            + "\nName : " + coach.get(count).getName()
                            + "\nAddress : " + coach.get(count).getAdress()
                            + "\nPhone : " + coach.get(count).getPhone()
                            + "\nLevel : " + coach.get(count).getLevel());
                    l.setId(count+"");
                    
                      l.setOnAction(e -> { 
                        try {
                            hBox_allgroup.getChildren().clear();
                            v_attend.getChildren().clear();
                            allDb.DB_connection();
                            cardOfattend(v_attend,allDb.attend_couch(coach.get(Integer.parseInt(l.getId())).getC_id()),coach.get(Integer.parseInt(l.getId())));
                            allDb.DB_close();
                            information_coach.toFront();
                        } catch (SQLException ex) {

                        }
                      
                      });
                      count++;
                    p.getChildren().add(l);
                }
            }
        }

    }

    private void cardOfswimmer(Pane p, List<swimmer> swimmer, String coach) throws SQLException {

        curr_couch.setText(coach);
        curr_couch.setTextFill(rgb(255, 255, 255));
        curr_couch.setFont(javafx.scene.text.Font.font("System Bold", 22));

        double lengh = swimmer.size();
        System.out.println(swimmer.size());
        // double lengh =3;
        int count = 0;
        int h = (int) Math.ceil(lengh / 2);
        int def = h - 2;

        p.setPrefHeight(365);
        if (def > 0) {
            p.setPrefHeight(p.getPrefHeight() + def * 250);
        }

        int colmn = (int) ((lengh < 4) ? lengh : 3);
        int row = (int) (Math.ceil(lengh / 3));
        int mod = (int) (lengh - ((int) Math.floor(lengh / 3) * 3));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colmn; j++) {
                if (i == row - 1 && j > mod - 1 && mod != 0) {
                } else {
                    Image image = new Image(getClass().getResourceAsStream("/images/icons8-swimmer-40.png"));
                    ImageView Iview = new ImageView(image);
                    Iview.setFitHeight(75);
                    Iview.setFitWidth(125);
                    Iview.setPickOnBounds(true);
                    Iview.setPreserveRatio(true);

                    JFXButton l = new JFXButton();
                    l.setButtonType(JFXButton.ButtonType.RAISED);
                    l.setContentDisplay(ContentDisplay.TOP);
                    l.setLayoutX(j * 250 + 35);
                    l.setLayoutY(i * 240 + 10);
                    l.setPrefHeight(200);
                    l.setPrefWidth(200);
                    l.setStyle("-fx-background-color:   #660000;");
                    l.setTextFill(rgb(0, 0, 0));
                    l.setGraphic(Iview);
                    l.setFont(javafx.scene.text.Font.font("System Bold", 13));
                    l.setText("Id : " + swimmer.get(count).getS_id()
                            + "\nName : " + swimmer.get(count).getName()
                            + "\nGender : " + swimmer.get(count).getGender());
                   
                    JFXCheckBox ch=new JFXCheckBox("Attend");
                    ch.setCheckedColor(rgb(42, 115, 255));
                    ch.setTextFill(rgb(255, 255, 255));
                    ch.setSelected(true);
                    ch.setOnAction((event) -> {
                       if(ch.isSelected()){
                       
                       } 
                    });
                    ch.setLayoutX(j * 250 + 35);
                    ch.setLayoutY(i * 240 + 10);
                    
                    count++;
                    //  l.setOnAction(e -> { });
                   
                    p.getChildren().add(l);
                    p.getChildren().add(ch);
                }
            }
        }

    }

    private void cardOfattend(VBox v, List<attend_couch> attend_couch,coach curr) throws SQLException {

        System.out.println(attend_couch.size());
        for(int i=0;i<attend_couch.size();i++){
        Label l1 = new Label(attend_couch.get(i).getDay()+"");
        Label l2 = new Label(attend_couch.get(i).getG_id() + "");
        Label l3 = new Label(attend_couch.get(i).getTime()+"");
        Label l4 = new Label(attend_couch.get(i).getRep_name());
        
        l1.setTextFill(rgb(37, 139, 191));
        l2.setTextFill(rgb(37, 139, 191));
        l3.setTextFill(rgb(37, 139, 191));
        l4.setTextFill(rgb(37, 139, 191));


        l1.setFont(javafx.scene.text.Font.font("System Bold", 13));
        l2.setFont(javafx.scene.text.Font.font("System Bold", 13));
        l3.setFont(javafx.scene.text.Font.font("System Bold", 13));
        l4.setFont(javafx.scene.text.Font.font("System Bold", 13));

        HBox h = new HBox();
       // h.setPrefSize(600, 300);
        h.setAlignment(Pos.CENTER_LEFT);
        h.setSpacing(25);

        h.getChildren().add(l1);
        h.getChildren().add(l2);
        h.getChildren().add(l3);
        h.getChildren().add(l4);
        
        v.getChildren().add(h);
        }
        inf_c_name.setText(curr.getName());
        inf_c_id.setText(curr.getC_id()+"");
        inf_c_level.setText(curr.getLevel());
        inf_c_phone.setText(curr.getPhone());
        inf_c_address.setText(curr.getAdress());
        
         List<Integer> id=new ArrayList<Integer>();
        
       id=allDb.get_All_Id_Of_Group_for_couch(curr.getC_id());
       
        for(int i=0;i<id.size();i++){
        Label l1 = new Label(id.get(i)+"");
        l1.setTextFill(rgb(37, 139, 191));
        l1.setFont(javafx.scene.text.Font.font("System Bold", 18));
        
        hBox_allgroup.getChildren().addAll(l1);
        }
    
        
    }


}

//    @FXML
//    private void close(ActionEvent event) {
//        System.out.println(Screen.getPrimary());
//        Platform.exit();
//    }
//
//    @FXML
//    private void swit(ActionEvent event) {
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        if (stage.isFullScreen()) {
//            stage.setFullScreen(false);
//        } else {
//            stage.setFullScreen(true);
//        }
//    }
