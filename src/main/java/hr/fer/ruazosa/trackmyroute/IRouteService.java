package hr.fer.ruazosa.trackmyroute;

import java.util.List;

public interface IRouteService {

    List<RouteBasic> getRouteList(Long user_id);
    Route saveRoute(Route route, Long user_id);
}
