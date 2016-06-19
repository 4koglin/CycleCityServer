package server.gps.model;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by pingu on 6/19/16.
 */
public interface WaypointRepository extends CrudRepository<Waypoint, Long>{

    List<Waypoint> findByClientTimestampBetweenOrderByClientTimestampAsc(Date from, Date to);


    //@Query("select  from User u group by u.lastname")
    //Page<String> findByLastnameGrouped(Pageable pageable);
}
