package server.coordinates.REST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.authentication.service.Secured;
import server.coordinates.service.CoordinatesService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/Coordinates")
public class CoordinatesResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoordinatesResource.class);

    @Inject
    private CoordinatesService coordinatesService;

    @GET
    @Path("/get")
    @Produces("application/json")
    @Secured
    public Response getMsg(@QueryParam("from") String from, @QueryParam("to") String to) {

        LOGGER.info("bla blub");
        try {
            // Try to create
            String Coordinates = coordinatesService.getCoordinatesFromTo(from, to);

            // Return the coordinates on the response
            return Response.ok("hello").build();

        } catch (Exception e) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }


    }

}