package server.rest.authentication;

import server.services.authentication.AuthenticationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/authentication")
public class AuthenticationResource {

    @Inject
    private AuthenticationService authService;

    @POST
    @Produces("application/json")
    @Consumes("application/x-www-form-urlencoded")
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("password") String password) {

        try {
            // Try to create a new Token for User
            String token = authService.createToken(username, password);

            // Return the Token on the response
            return Response.ok(token).build();

        } catch (Exception e) {

            return Response.status(Response.Status.UNAUTHORIZED).type("text/plain")
                    .entity(e.getMessage()).build();
        }
    }

}

