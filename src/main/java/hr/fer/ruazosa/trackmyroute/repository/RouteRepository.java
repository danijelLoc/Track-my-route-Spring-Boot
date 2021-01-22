package hr.fer.ruazosa.trackmyroute.repository;

import hr.fer.ruazosa.trackmyroute.model.Route;
import hr.fer.ruazosa.trackmyroute.model.RouteLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {


    @Query("SELECT r FROM Route r where r.user.id = ?1")
    List<Route> findAllByUserId(Long user_id);

    @Query("SELECT r FROM Route r where r.id = ?1")
    List<Route> findAllByRouteId(Long route_id);

}
