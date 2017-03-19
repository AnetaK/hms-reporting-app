package pl.report.service;

import pl.report.model.ReservationDTO;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Path("/input")
public class GetDataFromMainApp {

    @POST
    @Path("/reservations")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response readReservations(@NotNull List<ReservationDTO> inputString){
        System.out.println(inputString.toString());
        return Response.noContent().build();
    }
}
