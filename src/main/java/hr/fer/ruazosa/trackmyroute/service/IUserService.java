package hr.fer.ruazosa.trackmyroute.service;

import hr.fer.ruazosa.trackmyroute.model.User;
import hr.fer.ruazosa.trackmyroute.model.UserBasic;

public interface IUserService {
    User registerUser(User user);
    boolean checkUsernameUnique(User user);
    User loginUser(UserBasic userBasic);
    User getUserByUsernameAndPassword(UserBasic userBasic);

}
