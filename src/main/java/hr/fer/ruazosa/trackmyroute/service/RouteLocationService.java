package hr.fer.ruazosa.trackmyroute.service;

import hr.fer.ruazosa.trackmyroute.model.Route;
import hr.fer.ruazosa.trackmyroute.model.RouteLocation;
import hr.fer.ruazosa.trackmyroute.repository.RouteLocationRepository;
import hr.fer.ruazosa.trackmyroute.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RouteLocationService implements IRouteLocationService {

    @Autowired
    private RouteLocationRepository routeLocationRepository;

    @Override
    public List<RouteLocation> getRouteLocationList(Long route_id) {
        List<RouteLocation> routeLocationList = routeLocationRepository.findAllByRouteId(route_id);
        if (routeLocationList.isEmpty()) {
            return null;
        }
        return routeLocationRepository.findAllByRouteId(route_id);
    }

    @Override
    public List<RouteLocation> saveRouteLocations(List<RouteLocation> routeLocations) {
        return routeLocationRepository.save(routeLocations);
    }
}
