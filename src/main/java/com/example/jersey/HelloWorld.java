package com.example.jersey;

import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class HelloWorld {
    @POST
    @Consumes("application/json")
    @Path("/create")
    public void create(String x) {
        System.out.printf(x);
    }
}