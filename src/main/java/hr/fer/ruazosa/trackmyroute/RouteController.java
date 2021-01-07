package hr.fer.ruazosa.trackmyroute;

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
    @ResponseBody
    public List<Route> getRouteList(@RequestParam Long user_id) {
        return routeService.getRouteList(user_id);
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

        if(user == null) {
            body.put("error", "Invalid username or password");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
        route.setUser(user);
        routeService.saveRoute(route);
        return new ResponseEntity<Object>(route, HttpStatus.OK);


    }
}
