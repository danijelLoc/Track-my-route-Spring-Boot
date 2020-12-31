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
import java.util.Map;
import java.util.Set;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<Object> registerUser(@RequestBody User user) {
        // validation
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        Map<String, Object> body = new LinkedHashMap<>();
        for (ConstraintViolation<User> violation : violations) {
            body.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        if (!body.isEmpty()) {
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        }
        //  check if user exists
        if (userService.checkUsernameUnique(user)) {
            userService.registerUser(user);
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Username already taken", HttpStatus.CONFLICT);
        }
//        return new ResponseEntity<Object>(user, HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<Object> loginUserShort(@RequestBody UserBasic userBasic) {
        return loginUserFunction(userBasic);
}
//    @PostMapping("/loginUser")
//    public ResponseEntity<Object> loginUser(@RequestBody User user) {
//        return loginUserFunction(user);
//    }

    public ResponseEntity<Object> loginUserFunction(UserBasic userBasic) {
        // validation
        if (userBasic == null) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", "no user JSON object in body");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        } else if (userBasic.getUsername().isEmpty() || userBasic.getPassword().isEmpty()) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("error", "username or password parameters are empty");
            return new ResponseEntity<Object>(body, HttpStatus.NOT_ACCEPTABLE);
        } else {
            Map<String, Object> body = new LinkedHashMap<>();
            User loggedUser = userService.loginUser(userBasic);
            body.put("user", loggedUser);
            if (loggedUser != null) {
                return new ResponseEntity<Object>(body, HttpStatus.OK);
            } else {
                body = new LinkedHashMap<>();
                body.put("error", "no user found");
                return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
            }
        }

    }
}
