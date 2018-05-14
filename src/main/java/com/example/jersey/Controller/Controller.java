package com.example.jersey.Controller;

import com.example.jersey.Model.User;
import java.util.ArrayList;


public class Controller {

    public static ArrayList<User> users;

    public Controller(){
        users = new ArrayList<>();
    }

    public static void addUser(User user){
        users.add(user);
    }

    public static User getUser(String token){
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getToken().equals(token)){
                return users.get(i);
            }
        }
        return null;
    }


}
