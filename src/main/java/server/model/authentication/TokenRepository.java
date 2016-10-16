package server.model.authentication;

import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long>{

    Token findTokenByUserid(long user_id);

    Token findTokenByToken(String token);

}
