package hr.fer.ruazosa.trackmyroute.repository;

import hr.fer.ruazosa.trackmyroute.model.Route;
import hr.fer.ruazosa.trackmyroute.model.RouteLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RouteLocationRepository extends JpaRepository<RouteLocation, Integer> {


    @Query("SELECT rl FROM RouteLocation rl where rl.route.id = ?1")
    List<RouteLocation> findAllByRouteId(Long route_id);

    @Transactional
    @Modifying
    @Query("delete FROM RouteLocation rl where rl.route.id = ?1")
    void deleteAllByRouteId(Long route_id);
}
