package hr.fer.ruazosa.trackmyroute.service;

import hr.fer.ruazosa.trackmyroute.model.Route;
import hr.fer.ruazosa.trackmyroute.model.RouteLocation;

import java.util.List;

public interface IRouteLocationService {

    List<RouteLocation> getRouteLocationList(Long route_id);
    boolean saveRouteLocations(List<RouteLocation> routeLocations, Route route);
}
