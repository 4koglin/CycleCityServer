package server.services.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.model.authentication.TokenRepository;

import java.io.IOException;
import java.security.Principal;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import javax.annotation.Priority;

@Provider
@Secured
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Inject
    TokenRepository tokenRepository;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String token = "";

        LOGGER.info("loggin here");
        // Get the HTTP Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        LOGGER.info(authorizationHeader);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Token ")) {
            LOGGER.info("lets abort now");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).type("text/plain")
                    .entity("Authorization header must be provided").build());

        } else {
            // Extract the Token from the HTTP Authorization header
            token = authorizationHeader.substring("Token".length()).trim();

            try {

                validateToken(token);

            } catch (Exception e) {
                requestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).type("text/plain")
                        .entity(e.getMessage()).build());
            }
        }

        final String finalToken = token;

        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {

                return () -> String.valueOf(tokenRepository.findTokenByToken(finalToken).getId());
            }

            @Override
            public boolean isUserInRole(String role) {
                return true;
            }

            @Override
            public boolean isSecure() {
                return false;
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }
        });
    }

    private void validateToken(String token) throws NotAuthorizedException {

        try {
            tokenRepository.findTokenByToken(token).getToken();
        } catch (Exception e)
        {
            throw new NotAuthorizedException("invalid user");
        }
    }

}
