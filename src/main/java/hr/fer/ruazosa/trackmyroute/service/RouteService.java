package hr.fer.ruazosa.trackmyroute.service;

import java.util.List;

import hr.fer.ruazosa.trackmyroute.model.Route;
import hr.fer.ruazosa.trackmyroute.model.RouteLocation;
import hr.fer.ruazosa.trackmyroute.model.User;
import hr.fer.ruazosa.trackmyroute.repository.RouteLocationRepository;
import hr.fer.ruazosa.trackmyroute.repository.RouteRepository;
import hr.fer.ruazosa.trackmyroute.repository.UserRepository;
import hr.fer.ruazosa.trackmyroute.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RouteService implements IRouteService {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RouteLocationRepository routeLocationRepository;

    @Override
    public User loginUser(User user)
    {
        List<User> loggedUserList = userRepository.findByUserNameAndPassword(user.getUsername(), user.getPassword());
        if (loggedUserList.isEmpty()) {
            return null;
        }
        return userRepository.findByUserNameAndPassword(user.getUsername(), user.getPassword()).get(0);

    }


    @Override
    public List<Route> getRouteList(Long user_id) {
        List<Route> routeList = routeRepository.findAllByUserId(user_id);
        if (routeList.isEmpty()) {
            return null;
        }
        return routeRepository.findAllByUserId(user_id);
    }

    @Override
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route deleteRoute(Route route) {
        routeRepository.delete(route);
        List<RouteLocation> routeLocations = routeLocationRepository.findAllByRouteId(route.getId());
        for (RouteLocation rl: routeLocations) {
            routeLocationRepository.delete(rl);
        }
        return route;
    }


}
