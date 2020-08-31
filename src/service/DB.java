/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.overview;

/**
 *
 * @author asd
 */
public class DB {

    private static Connection connection;
    private static Statement statement;
//////////////////////////////connection/////////////////

    public void DB_connection() throws SQLException {

        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/swimming", "root", "");

            System.out.println("connected");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void DB_close() throws SQLException {

        try {
            connection.close();
            System.out.println("closed");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
//////////////////////////////connection/////////////////

    //////////////////////////////login//////////////////////
    public boolean login(String user, String pass) throws SQLException {
        boolean b = false;
        //SELECT * FROM `login` WHERE `user` LIKE 'samy' AND `pass` LIKE 'samy'
        try {
            statement = connection.createStatement();
            ResultSet r = statement
                    .executeQuery("SELECT * FROM `login` WHERE `user` LIKE '" + user + "' AND `pass` LIKE '" + pass + "'");
            b = r.next();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Login :" + ex);
        }
        return b;
    }
    //////////////////////////////login//////////////////////
    
//SELECT couch.name,groups.g_id,groups.level,groups.track,groups.g_time FROM groups INNER JOIN couch ON groups.c_id=couch.c_id WHERE groups.g_day='السبت' ORDER BY g_time ASC
  
        //////////////////////////////overview//////////////////////
public List<overview> over(int b) throws SQLException {

    List<overview> t = new ArrayList<overview>();
       try {
            statement = connection.createStatement();
            ResultSet r = statement
                    .executeQuery("SELECT couch.name,groups.g_id,groups.level,groups.track,groups.g_time FROM groups INNER JOIN couch ON groups.c_id=couch.c_id WHERE g_day="+b+" ORDER BY g_time ASC");
           System.out.println(r.next());
            while (r.next()) {
                //System.out.println(r.getString("couch.name"));
                t.add(new overview(r.getString("couch.name"), r.getInt("groups.g_id"),
                        r.getString("groups.level"), r.getString("groups.track"),r.getTime("groups.g_time")));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "over :" + ex);
        }

       return t;
}
}
