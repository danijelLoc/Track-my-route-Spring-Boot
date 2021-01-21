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
    public ResponseEntity<Object> deleteRoute(@RequestBody Route route) {
        // validation
        // SIDE NOTE and time saver... in postman id of the route is required, difference from save method
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

        // route json contains basicUser without id, when converting to User it is given new id
        // true user
        User user = routeService.loginUser(route.getUser());
        body.put("user", user);

        if (user == null) {
            body.put("error", "Invalid username or password");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }

        route.setUser(user);

        List<Route> routeList = routeService.getRouteList(user.getId());
        if (routeList == null) {
            body.put("error", "Route doesn't exist, list empty");
            System.out.println("Route doesn't exist, list empty");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
        }
        if (!(routeList.contains(route))) {
            body.put("error", "Route doesn't exist");
            System.out.println("error, Route doesn't exist" + String.valueOf(route.getId()));
            return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
        }


        routeService.deleteRoute(route);
        return new ResponseEntity<Object>(route, HttpStatus.OK);
    }
}
