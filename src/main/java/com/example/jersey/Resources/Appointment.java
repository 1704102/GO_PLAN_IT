package com.example.jersey.Resources;

import com.example.jersey.Database.AppointmentDatabase;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/appointment")
public class Appointment {

    @PUT
    @Consumes("application/json")
    public void addAppointment(String input) {
        JSONObject appointment = new JSONObject(input);
        AppointmentDatabase appointmentDatabase = new AppointmentDatabase();
        appointmentDatabase.addAppointment(appointment);
    }

    @DELETE
    @Consumes("application/json")
    public void deleteAppointment(String x) {
        JSONObject input = new JSONObject(x);
        AppointmentDatabase appointmentDatabase = new AppointmentDatabase();
        appointmentDatabase.deleteAppointment(input.getInt("id"));
    }

//    @POST
//    @Consumes("application/json")
//    public String getAppointment(String x) {
//        JSONObject input = new JSONObject(x);
//        AppointmentDatabase appointmentDatabase = new AppointmentDatabase();
//        return appointmentDatabase.getAppointments(input).toString();
//    }

}
