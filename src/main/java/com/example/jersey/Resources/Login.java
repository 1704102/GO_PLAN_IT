package com.example.jersey.Resources;


import com.example.jersey.Controller.Controller;
import com.example.jersey.Database.LoginDatabase;
import com.example.jersey.Model.User;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/login")
public class Login {

    public static Controller controller;

    public Login(){
        if (controller == null){
            controller = new Controller();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String login(String login){
        JSONObject object = new JSONObject(login);
        LoginDatabase loginDatabase = new LoginDatabase();
        User user;
        if ((user = loginDatabase.Login(object)) != null){
            Controller.addUser(user);
            System.out.println(user.getToken());
            return user.getToken();
        }else {
            System.out.println("error");
            return "error";
        }
    }

    @Path("/check")
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public String loginCheck(String login){
        JSONObject object = new JSONObject(login);
        User user = Controller.getUser(object.getString("token"));
        if (user != null){
            return "true";
        }else{
            System.out.println("false");
            return "false";
        }

    }
}