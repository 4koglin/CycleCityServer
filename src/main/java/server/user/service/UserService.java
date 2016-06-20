package server.user.service;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import server.user.model.User;
import server.user.model.UserRepository;

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
