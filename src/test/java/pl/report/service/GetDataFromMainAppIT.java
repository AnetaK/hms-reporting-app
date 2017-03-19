package pl.report.service;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import pl.report.RestApplication;
import pl.report.model.ReservationDTO;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Arquillian.class)
public class GetDataFromMainAppIT {

    @Deployment
    public static WebArchive createDeployment() {
        File[] deps = Maven.resolver()
                .resolve("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.5.4")
                .withTransitivity().asFile();

        return ShrinkWrap.create(WebArchive.class)
                .addAsLibraries(deps)
                .addClass(ReservationDTO.class)
                .addClass(GetDataFromMainApp.class)
                .addClass(RestApplication.class);
    }

    @ArquillianResource
    URL baseUrl;

    List<ReservationDTO> reservationDTOs;
    static URI create_event_location;

    @Before
    public void prepare_reservations_list() {

        reservationDTOs = new ArrayList<>();
        ReservationDTO res1;
        ReservationDTO res2;

        res1 = new ReservationDTO();
        res1.setGuestName("guest1");
        res1.setGuestSurname("surname1");
        res1.setId(1l);
        res1.setRoomId(0l);

        res2 = new ReservationDTO();
        res2.setGuestName("guest2");
        res2.setGuestSurname("surname2");
        res2.setId(2l);
        res2.setRoomId(3l);

        reservationDTOs.add(res1);
        reservationDTOs.add(res2);


    }


    @Test
    @RunAsClient
    @InSequence(1)
    public void should_create_record() throws URISyntaxException {


        URI uri = UriBuilder
                .fromUri(baseUrl.toString())
                .segment("api")
                .segment("input")
                .segment("reservations")
                .build();
        System.out.println("uri.toString() = " + uri.toString());

        Response response = ClientBuilder.newClient()
                .target(uri)
                .request()
                .acceptEncoding("UTF-8")
                .post(Entity.json(reservationDTOs));


        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation().getPath(), containsString("/input/reservations"));
    }



//    @Test
//    @RunAsClient
//    @InSequence(2)
//    public void should_return_an_event(@ArquillianResource URL baseUrl) {
//
//        List<ReservationDTO> retReservationDTOs = ClientBuilder.newClient()
//                .target(create_event_location)
//                .request()
//                .accept(MediaType.APPLICATION_JSON_TYPE)
//                .get(ReservationDTO.class);
//
//        assertThat(retReservationDTOs.get(0).getGuestName(), is(reservationDTOs.get(0).getGuestName()));
//
//
//    }


}


