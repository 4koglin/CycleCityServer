package server.authentication.service;

import org.springframework.stereotype.Service;
import server.authentication.model.Token;
import server.authentication.model.TokenRepository;
import server.user.model.User;
import server.user.model.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

@Service
public class AuthenticationService {

    @Inject
    UserRepository userRepository;

    @Inject
    TokenRepository tokenRepository;

    // returns userid
    private long authenticateUser(String username, String password) throws NotAuthorizedException {

        List<User> users = userRepository.findByUsernameAndPassword(username, password);

        if(users.size() == 1) {
            return users.get(0).getId();
        }
        else {
            throw new NotAuthorizedException("Not authorized");
        }
    }

    public String createToken(String username, String password) {

        long userid;
        String tokenS;

        try {
            userid = authenticateUser(username, password);
        } catch (NotAuthorizedException e)
        {
            throw new NotAuthorizedException("Not authorized, no token created");
        }

        tokenS = generateToken();

        try {
            Token token = tokenRepository.findTokenByUserid(userid);
            token.setToken(tokenS);
            tokenRepository.save(token);

        } catch (Exception e) {
            Token token = new Token(userid, tokenS);
            tokenRepository.save(token);
        }

        return tokenS;
    }


    private String generateToken() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }
}
