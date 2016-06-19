package server.user.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by pingu on 6/18/16.
 */

// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByUsernameOrEmail(String username, String email);

    List<User> findByUsernameAndPassword(String username, String password);

}
