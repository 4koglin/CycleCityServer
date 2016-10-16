package server;

import server.rest.authentication.AuthenticationResource;
import server.services.authentication.AuthenticationFilter;
import server.rest.gps.GpsResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import server.rest.user.UserResource;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(GpsResource.class);
        register(AuthenticationResource.class);
        register(UserResource.class);
        register(AuthenticationFilter.class);
    }
}