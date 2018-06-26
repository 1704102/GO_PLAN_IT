package com.example.jersey.Resources;

import com.example.jersey.Database.AppointmentDatabase;
import org.json.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/appointment")
public class Appointment {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointment(String x){
        JSONObject object = new JSONObject(x);
        AppointmentDatabase database = new AppointmentDatabase();
        return Response.ok(database.getAppointment(object.getInt("id"), object.getString("timeOffset")).toString()).build();
    }
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


}
