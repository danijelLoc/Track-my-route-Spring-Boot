package hr.fer.ruazosa.trackmyroute.controller;

import hr.fer.ruazosa.trackmyroute.model.Route;
import hr.fer.ruazosa.trackmyroute.model.RouteLocation;
import hr.fer.ruazosa.trackmyroute.model.User;
import hr.fer.ruazosa.trackmyroute.service.RouteLocationService;
import hr.fer.ruazosa.trackmyroute.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class RouteLocationController {

    @Autowired
    private RouteLocationService routeLocationService;
    @Autowired
    private RouteService routeService;

    // shape /routeLocations?route_id=1
    @GetMapping("/routeLocations")
    public ResponseEntity<Object> getRouteList(@RequestParam Long route_id) {
        List<RouteLocation> routeLocations = routeLocationService.getRouteLocationList(route_id);
        Map<String, Object> body = new LinkedHashMap<>();
        if (routeLocations == null) {
            body.put("error", "no route locations found");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
        } else {
            body.put("routes", routeLocations);
            return new ResponseEntity<Object>(body, HttpStatus.OK);
        }
    }

    @PostMapping("/saveRouteLocations")
    public ResponseEntity<Object> saveRouteLocations(@RequestParam Long route_id, @RequestBody List<RouteLocation> routeLocations) {
        // validation
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<List<RouteLocation>>> violations = validator.validate(routeLocations);

        Map<String, Object> body = new LinkedHashMap<>();
        for (ConstraintViolation<List<RouteLocation>> violation : violations) {
            body.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        if (!body.isEmpty()) {
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
        List<Route> routesByID = routeService.getById(route_id);
        if (routesByID.isEmpty()) {
            body.put("error", "route id not valid");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        } else {
            Route route = routesByID.get(0);
            boolean flag = routeLocationService.saveRouteLocations(routeLocations, route);
            if (flag == true) {
                return new ResponseEntity<Object>(routeLocations, HttpStatus.OK);
            } else {
                body.put("error", "list is empty");
                return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
            }
        }
    }
}
