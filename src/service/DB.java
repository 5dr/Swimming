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
import javax.swing.JOptionPane;

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
    
    
}
