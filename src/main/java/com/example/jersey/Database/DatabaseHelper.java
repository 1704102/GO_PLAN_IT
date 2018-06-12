package com.example.jersey.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    private final String IP = "";
    private final String PORT = "6603";
    private final String USERNAME = "ablahblah";
    private final String PASSWORD = "achtkarakters";

    Connection connection;

    public void connect(){
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/go_plan_it?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",USERNAME,PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void disconnect(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
