package server.authentication.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by pingu on 6/19/16.
 */
@Entity
public class Token  implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private long userid;

    @Column(unique=true)
    private String token;

    protected Token() {};

    public Token(long user_id, String token)
    {
        this.userid = user_id;
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long user_id) {
        this.userid = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
