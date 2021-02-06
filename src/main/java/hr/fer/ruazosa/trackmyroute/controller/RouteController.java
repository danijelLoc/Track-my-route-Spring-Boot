package hr.fer.ruazosa.trackmyroute.controller;

import hr.fer.ruazosa.trackmyroute.model.Route;
import hr.fer.ruazosa.trackmyroute.model.UserBasic;
import hr.fer.ruazosa.trackmyroute.service.RouteService;
import hr.fer.ruazosa.trackmyroute.model.User;
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
public class RouteController {
    @Autowired
    private RouteService routeService;

    // shape /routes?user_id=1
    @GetMapping("/routes")
    public ResponseEntity<Object> getRouteList(@RequestParam Long user_id) {
        List<Route> routes = routeService.getRouteList(user_id);
        Map<String, Object> body = new LinkedHashMap<>();
        if (routes == null) {
            body.put("error", "no routes found");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
        } else {
            body.put("routes", routes);
            return new ResponseEntity<Object>(body, HttpStatus.OK);
        }
    }


    @PostMapping("/saveRoute")
    public ResponseEntity<Object> saveRoute(@RequestBody Route route) {
        // validation
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Route>> violations = validator.validate(route);

        Map<String, Object> body = new LinkedHashMap<>();
        for (ConstraintViolation<Route> violation : violations) {
            body.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        if (!body.isEmpty()) {
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }

        User user = routeService.loginUser(route.getUser());
        body.put("user", user);

        if (user == null) {
            body.put("error", "Invalid username or password");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
        route.setUser(user);
        Route properRoute = routeService.saveRoute(route);
        return new ResponseEntity<Object>(properRoute, HttpStatus.OK);
    }

    @PostMapping("/deleteRoute")
    public ResponseEntity<Object> deleteRoute(@RequestParam Long route_id) {
        Map<String, Object> body = new LinkedHashMap<>();
        if (!routeService.getById(route_id).isEmpty()) {
            Route route = routeService.getById(route_id).get(0);
            routeService.deleteById(route_id);
            return new ResponseEntity<Object>(route, HttpStatus.OK);
        } else {
            body.put("error", "Non existent route with id:" + String.valueOf(route_id));
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
