package server.authentication.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    private void authenticateUser(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    }

    public String createToken(String username, String password) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token

        return null;
    }
}
