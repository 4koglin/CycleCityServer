package server.model.gps;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Waypoint {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private long userid;
    private String tourid;
    private long cmt;
    private double latitude;
    private double longitude;
    private double altitude;
    private Date clientTimestamp;
    private Date serverTimestamp;

    protected Waypoint() {}

    public Waypoint(long userid, String tourid, long cmt, double latitude, double longitude, double altitude, Date clientTimestamp, Date serverTimestamp)
    {
        this.userid = userid;
        this.tourid = tourid;
        this.cmt = cmt;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.clientTimestamp = clientTimestamp;
        this.serverTimestamp = serverTimestamp;
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

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getTourid() {
        return tourid;
    }

    public void setTourid(String tourid) {
        this.tourid = tourid;
    }

    public long getCmt() {
        return cmt;
    }

    public void setCmt(long cmt) {
        this.cmt = cmt;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public Date getClientTimestamp() {
        return clientTimestamp;
    }

    public void setClientTimestamp(Date clientTimestamp) {
        this.clientTimestamp = clientTimestamp;
    }

    public Date getServerTimestamp() {
        return serverTimestamp;
    }

    public void setServerTimestamp(Date serverTimestamp) {
        this.serverTimestamp = serverTimestamp;
    }
}
