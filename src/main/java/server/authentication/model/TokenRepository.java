package server.authentication.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by pingu on 6/19/16.
 */

public interface TokenRepository extends CrudRepository<Token, Long>{

    Token findTokenByUserid(long user_id);

    Token findTokenByToken(String token);

}
