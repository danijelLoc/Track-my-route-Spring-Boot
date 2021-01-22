package hr.fer.ruazosa.trackmyroute.service;

import hr.fer.ruazosa.trackmyroute.model.Route;
import hr.fer.ruazosa.trackmyroute.model.RouteLocation;
import hr.fer.ruazosa.trackmyroute.model.User;

import java.util.List;

public interface IRouteService {

    List<Route> getRouteList(Long user_id);

    Route saveRoute(Route route);

    User loginUser(User user);

    Route deleteRoute(Route route);

    List<Route> getById(Long route_id);
}
