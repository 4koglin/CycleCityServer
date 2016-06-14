package server.user.rest;

import server.user.service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/User")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Path("/CreateNew")
    @Produces("application/json")
    public Response getMsg(@QueryParam("username") String username,
                           @QueryParam("password") String password,
                           @QueryParam("email") String email) {
        try {
            // Try to create a new token for user
            String msg = userService.createNewUser(username, password, email);

            // Return the token on the response
            return Response.ok(msg).build();

        } catch (Exception e) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

    }

}