package server.gps.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import server.gps.model.Waypoint;
import server.gps.model.WaypointRepository;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GpsService {

    @Inject
    WaypointRepository waypointRepository;

        public JSONObject getCoordinatesFromTo(Date from, Date to) throws InputMismatchException {
            List<Waypoint> waypoints = waypointRepository.findByClientTimestampBetweenOrderByClientTimestampAsc(from, to);

            HashMap<String, JSONArray> tracks = new HashMap<>();

            try {
                for (Waypoint waypoint : waypoints) {
                    String tourid = waypoint.getTourid();

                    JSONArray tracksbytourid;
                    if (tracks.containsKey(tourid)) {
                        tracksbytourid = tracks.get(tourid);
                    } else {
                        tracksbytourid = new JSONArray();
                    }

                    JSONObject coordinate = new JSONObject();
                    coordinate.put("cmt", waypoint.getCmt());
                    coordinate.put("time", waypoint.getClientTimestamp());
                    coordinate.put("lat", waypoint.getLatitude());
                    coordinate.put("lon", waypoint.getLongitude());
                    coordinate.put("ele", waypoint.getAltitude());

                    tracks.put(tourid, tracksbytourid.put(coordinate));
                }
            } catch (Exception e) {
                throw new InputMismatchException();
            }
            return new JSONObject(tracks);
        }

    public void storeGPSData(long userid, JSONObject jsonObject) throws ParseException {

        List<Waypoint> waypoints = new ArrayList<>();

        for (Object key : jsonObject.keySet()) {

            String keyStr = (String) key;
            JSONArray keyvalue = (JSONArray) jsonObject.get(keyStr);

            for (int i = 0; i < keyvalue.length(); i++) {

                JSONObject currentWaypoint = keyvalue.getJSONObject(i);

                Waypoint waypoint = new Waypoint(
                        userid,
                        keyStr,
                        currentWaypoint.getLong("cmt"),
                        currentWaypoint.getDouble("lat"),
                        currentWaypoint.getDouble("lon"),
                        currentWaypoint.getDouble("ele"),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(currentWaypoint.getString("time")),
                        new Date()
                );
                waypoints.add(waypoint);
            }
        }

        waypointRepository.save(waypoints);
    }
}
