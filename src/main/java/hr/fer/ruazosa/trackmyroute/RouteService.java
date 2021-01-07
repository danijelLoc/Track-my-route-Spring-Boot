package hr.fer.ruazosa.trackmyroute;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RouteService implements IRouteService {

    @Autowired
    private RouteRepository routeRepository;



    @Override
    public List<Route> getRouteList(Long user_id) {
        List<Route> routeList = routeRepository.findAllByUserId(user_id);
        if (routeList.isEmpty()) {
            return null;
        }
        return routeRepository.findAllByUserId(user_id);
    }

    @Override
    public Route saveRoute(Route route, Long user_id) {
        List<Route> routeList = routeRepository.findAllByUserId(user_id);
        routeList.add(route);
        return route;
    }
}
