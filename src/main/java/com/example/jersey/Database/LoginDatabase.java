package com.example.jersey.Database;

import com.example.jersey.Controller.Util;
import com.example.jersey.Model.User;
import org.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDatabase extends DatabaseHelper{

    public User Login(JSONObject login){
        connect();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user where username = ? and password = ?");
            preparedStatement.setString(1,login.getString("username"));
            preparedStatement.setString(2,login.getString("password"));
            ResultSet s = preparedStatement.executeQuery();
            while (s.next()){
                return new User(s.getInt("id"), Util.generateToken());
            }
        }catch (Exception e){}
        disconnect();
        return null;
    }
}
