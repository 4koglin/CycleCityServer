package server.model.gps;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Track {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long userid;

    @Column(unique=true)
    private String tourid;
    private Date start;

    protected Track() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getTourid() {
        return tourid;
    }

    public void setTourid(String tourid) {
        this.tourid = tourid;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}
