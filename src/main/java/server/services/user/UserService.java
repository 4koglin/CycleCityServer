package server.services.user;

import org.springframework.stereotype.Service;
import server.model.user.User;
import server.model.user.UserRepository;

import javax.inject.Inject;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    public String createNewUser(String username, String password, String email) throws Exception {

        if(userRepository.findByUsernameOrEmail(username, password).size() > 0) {
            throw new Exception("username or eamil already in use");
        }
        User user = new User(username, password, email);
        userRepository.save(user);

        return null;
    }
}
