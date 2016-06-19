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



@Path("/user")
public class UserResource {

    @Inject
    private UserService userService;

    @Inject
    private UserRepository userRepository;

    @POST
    @Path("/create")
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

            userService.createNewUser(username, password, email);

            return Response.ok("Account " + username + " has been created").build();

        } catch (Exception e) {

            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
    }

    //TODO Create user with jaxb from xsd

    @POST
    @Path("/CreateNew")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes()
    public Response getMsg() {

        return Response.status(Response.Status.EXPECTATION_FAILED).type("text/plain")
                .entity("use application/x-www-form-urlencoded or application/json").build();
    }

}