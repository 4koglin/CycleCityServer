package server.user.rest;

import server.user.model.User;
import server.user.model.UserRepository;
import server.user.service.UserService;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;


@Path("/User")
public class UserResource {

    @Inject
    private UserService userService;

    @Inject
    private UserRepository userRepository;

    @POST
    @Path("/CreateNew")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getMsg(@FormParam("username") String username,
                           @FormParam("password") String password,
                           @FormParam("email") String email) {


        for(String param : Arrays.asList(username, password, email)) {
            if (param == null || param == "") {
                throw new WebApplicationException(
                        Response.status(Response.Status.BAD_REQUEST)
                                .entity("username, password and email are mandatory")
                                .build()
                );
            }
        }

        try {
            // Try to create a new tol);ken for User
            User user = new User(username, password, email);
            userRepository.save(user);

            // Return the token on the response
            return Response.ok("Account " + user.getUsername() + " has been created").build();

        } catch (Exception e) {


            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }

    }

}