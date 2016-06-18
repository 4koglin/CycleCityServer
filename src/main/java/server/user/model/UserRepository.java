package server.user.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by pingu on 6/18/16.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
