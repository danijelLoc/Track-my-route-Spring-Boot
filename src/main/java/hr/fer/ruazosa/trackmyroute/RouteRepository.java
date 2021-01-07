package hr.fer.ruazosa.trackmyroute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    @Query("SELECT r FROM Route r where r.user_id.user_id = ?1")
    List<RouteBasic> findRouteBasicByUserId(Long user_id);

    @Query("SELECT r FROM Route r where r.user_id.user_id = ?1")
    List<Route> findRouteByUserId(Long user_id);
}
