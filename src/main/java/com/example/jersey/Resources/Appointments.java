package com.example.jersey.Resources;

import com.example.jersey.Database.AppointmentDatabase;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

@Path("appointments")
public class Appointments {

    @POST
    @Consumes("application/json")
    public String getAppointments(String x) {
        JSONObject input = new JSONObject(x);
        AppointmentDatabase appointmentDatabase = new AppointmentDatabase();
        return appointmentDatabase.getAppointments(input).toString();
    }
}
