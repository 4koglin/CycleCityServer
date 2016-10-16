package server.model.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsernameOrEmail(String username, String email);

    List<User> findByUsernameAndPassword(String username, String password);

}
