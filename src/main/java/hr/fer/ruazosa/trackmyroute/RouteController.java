package hr.fer.ruazosa.trackmyroute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RouteController {
    @Autowired
    private IRouteService routeService;

    // shape /routes?user_id=1
    @GetMapping("/routes")
    @ResponseBody
    public List<Route> getRouteList(@RequestParam Long user_id) {
        return routeService.getRouteList(user_id);
    }


    @PostMapping("/saveRoute")
    public ResponseEntity<Object> saveRoute(@RequestParam Long user_id, @RequestBody Route route) {
        //validation
        if (route == null) {
            Map<String,Object> body = new LinkedHashMap<>();
            body.put("error", "no route JSON object in body");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        } else if (route.getName().isEmpty()) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", "route name is empty");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        /*} else if (route.getUserId().getId().equals(user_id)) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", "this route is not from this user");
            return new ResponseEntity<Object>(body, HttpStatus.CONFLICT);*/
        } else {
            Map<String, Object> body = new LinkedHashMap<>();
            routeService.saveRoute(route, user_id);
            body.put("user", route);
            return new ResponseEntity<Object>(body, HttpStatus.OK);
        }
    }

    @PostMapping("/deleteRoute")
    public ResponseEntity<Object> deleteRoute(@RequestParam Long user_id, @RequestBody Route route) {
        //validation
        List<Route> routeList = routeService.getRouteList(user_id);
        if (route == null) {
            Map<String,Object> body = new LinkedHashMap<>();
            body.put("error", "no route JSON object in body");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        } else if (routeList == null) {
            Map<String,Object> body = new LinkedHashMap<>();
            body.put("error", "route doesn't exist");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        } else if (routeList.contains(route)) {
            Map<String,Object> body = new LinkedHashMap<>();
            body.put("error", "route doesn't exist");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        } else {
            Map<String, Object> body = new LinkedHashMap<>();
            routeService.deleteRoute(route, user_id);
            body.put("user", route);
            return new ResponseEntity<Object>(body, HttpStatus.OK);
        }
    }
}
