package hr.fer.ruazosa.trackmyroute;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RouteService implements IRouteService {

    @Autowired
    private RouteRepository routeRepository;
    private UserRepository userRepository;

    @Override
    public User loginUser(User user)
    {
        List<User> loggedUserList = userRepository.findByUserNameAndPassword("Pero", "1234");
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
    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }
        /*//List<Route> routeList = routeRepository.findAllByUserId(user_id);
        //routeList.add(route);
        Integer id = Math.toIntExact(user_id);
        Optional<User> u = userRepository.findById(id);
        /*if (userList.isEmpty()) {
            return null;
        }
        route.setUser(u.get());
        routeRepository.save(route);
        return route;
    }*/
}
