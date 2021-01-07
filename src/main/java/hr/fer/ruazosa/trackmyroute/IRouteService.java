package hr.fer.ruazosa.trackmyroute;

import java.util.List;

public interface IRouteService {

    List<Route> getRouteList(Long user_id);
    Route saveRoute(Route route);
    User loginUser(User user);

}
