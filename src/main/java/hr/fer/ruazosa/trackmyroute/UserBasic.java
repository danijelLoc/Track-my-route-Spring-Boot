package hr.fer.ruazosa.trackmyroute;

import javax.validation.constraints.NotBlank;

public class UserBasic {
    public String username;
    public String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
