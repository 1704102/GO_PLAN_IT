package com.example.jersey.Resources;

import com.example.jersey.Database.AppointmentDatabase;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/appointment")
public class Appointment {

    @PUT
    @Consumes("application/json")
    public void getAppointment(String input) {
        JSONObject appointment = new JSONObject(input);
        AppointmentDatabase appointmentDatabase = new AppointmentDatabase();
        appointmentDatabase.addAppointment(appointment);
    }

    @Path("/all")
    @POST
    @Consumes("application/json")
    public String getAppointments(String x) {
        JSONObject input = new JSONObject(x);
        AppointmentDatabase appointmentDatabase = new AppointmentDatabase();
        return appointmentDatabase.getAppointments(input).toString();
    }
}
