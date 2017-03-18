package pl.report.service;

import pl.report.model.Reservation;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/input")
public class GetDataFromMainApp {

    @POST
    @Path("/reservations")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response readReservations(@NotNull Reservation inputString){
        System.out.println(inputString.toString());
        return Response.noContent().build();
    }
}
