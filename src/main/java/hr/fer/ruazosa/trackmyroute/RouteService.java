package hr.fer.ruazosa.trackmyroute;

import java.util.ArrayList;
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

        /*List<RouteBasic> routeBasicList = new ArrayList<>();
        for (Route r: routeList) {
            RouteBasic rb = new RouteBasic();
            rb.name = r.getName();
            rb.date = r.getDate();
            rb.route_id = r.getId();
            rb.user_id = r.getUserId();
            routeBasicList.add(rb);
        }
        return routeBasicList;*/
        return routeRepository.findAllByUserId(user_id);
    }

    @Override
    public Route saveRoute(Route route, Long user_id) {
        routeRepository.save(route);
        return route;
    }

    @Override
    public Route deleteRoute(Route route, Long user_id) {
        routeRepository.delete(route);
        return route;
    }
}
