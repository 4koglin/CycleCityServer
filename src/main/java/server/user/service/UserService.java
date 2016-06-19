package server.user.service;

import org.springframework.stereotype.Service;
import server.user.model.User;
import server.user.model.UserRepository;

import javax.inject.Inject;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    public String createNewUser(String username, String password, String email) {

        User user = new User(username, password, email);
        userRepository.save(user);

        return null;
    }
}
