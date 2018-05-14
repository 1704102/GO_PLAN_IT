package com.example.jersey.Resources;

import com.example.jersey.Database.AppointmentDatabase;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/appointment")
public class Appointment {

    @PUT
    @Consumes("application/json")
    public void login(String input) {
        JSONObject appointment = new JSONObject(input);
        AppointmentDatabase appointmentDatabase = new AppointmentDatabase();
        appointmentDatabase.addAppointment(appointment);
    }

}
