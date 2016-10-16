package server.model.gps;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface WaypointRepository extends CrudRepository<Waypoint, Long>{

    List<Waypoint> findByClientTimestampBetweenOrderByClientTimestampAsc(Date from, Date to);


    //@Query("select  from User u group by u.lastname")
    //Page<String> findByLastnameGrouped(Pageable pageable);
}
