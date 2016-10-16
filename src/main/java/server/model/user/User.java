package server.model.user;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.*;

@Entity
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String username;

    @Column(unique=true)
    private String email;

    private String password;
    private String activationtoken;
    private int active;
    private Date created_on;

    protected User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.activationtoken = generateToken();
        this.active = 0;
        this.created_on = Calendar.getInstance().getTime();
    }


    @Override
    public String toString() {
        return String.format(
                "User[username='%s', email='%s']",
                username, email);
    }

    private String generateToken() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

    public Date getCreated_on() {
        return created_on;
    }

    public void setCreated_on(Date created_on) {
        this.created_on = created_on;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getActivationtoken() {
        return activationtoken;
    }

    public void setActivationtoken(String activationtoken) {
        this.activationtoken = activationtoken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
