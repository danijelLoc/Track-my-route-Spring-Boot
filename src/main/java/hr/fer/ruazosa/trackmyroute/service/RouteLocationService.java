package hr.fer.ruazosa.trackmyroute.service;

import hr.fer.ruazosa.trackmyroute.model.RouteLocation;
import hr.fer.ruazosa.trackmyroute.repository.RouteLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public boolean saveRouteLocations(List<RouteLocation> routeLocations) {
        if (routeLocations!=null) {
            for (RouteLocation rl: routeLocations) {
                routeLocationRepository.save(rl);
            }
            return true;
        } else {
            return false;
        }
    }
}
