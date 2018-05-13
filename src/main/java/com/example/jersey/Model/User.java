package com.example.jersey.Model;

public class User {

    int id;
    String token;

    public User(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
