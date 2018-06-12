package com.example.jersey.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    private final String IP = "";
    private final String PORT = "6603";
    private final String USERNAME = "root";
    private final String PASSWORD = "Klbxjmpv526f_";

    Connection connection;

    public void connect(){
        try {
           Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",USERNAME,PASSWORD);
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
