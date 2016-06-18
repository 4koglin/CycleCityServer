package server;

import server.authentication.rest.AuthenticationResource;
import server.authentication.service.AuthenticationFilter;
import server.coordinates.rest.CoordinatesResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import server.user.rest.UserResource;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(CoordinatesResource.class);
        register(AuthenticationResource.class);
        register(UserResource.class);
        register(AuthenticationFilter.class);
    }
}