package server.gps.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.authentication.service.Secured;
import server.gps.service.GpsService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.json.JSONObject;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Path("/gpsdata")
public class GpsResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GpsResource.class);

    @Inject
    private GpsService gpsService;

    @Context
    private SecurityContext securityContext;

    @GET
    @Path("/get")
    @Produces("application/json")
    @Secured
    public Response getMsg(@QueryParam("from") String fromStr, @QueryParam("to") String toStr) {

        try {
            // Try to create
            Date from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromStr);
            Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toStr);

            // Return the gps on the response
            return Response.ok(gpsService.getCoordinatesFromTo(from, to).toString()).build();

        } catch (Exception e) {
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    @POST
    @Path("/log")
    @Consumes("application/json")
    @Secured
    public Response getMsg(String jsonData) {

        long userid;

        // Get current user
        try {
            Principal principal = securityContext.getUserPrincipal();
            String useridStr = principal.getName();
            userid = Long.parseLong(useridStr, 10);
        } catch (Exception e)
        {
            return Response.status(Response.Status.EXPECTATION_FAILED).type("text/plain").entity("User is invald").build();
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            gpsService.storeGPSData(userid, jsonObject);

            return Response.ok("").build();

        } catch(Exception e) {

            return Response.status(Response.Status.EXPECTATION_FAILED).type("text/plain").entity("Please check your json format").build();
        }
    }
}