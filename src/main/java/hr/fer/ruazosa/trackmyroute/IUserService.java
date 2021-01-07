package hr.fer.ruazosa.trackmyroute;

import java.util.List;

public interface IUserService {
    User registerUser(User user);
    boolean checkUsernameUnique(User user);
    User loginUser(UserBasic userBasic);
    User getUserByUsernameAndPassword(UserBasic userBasic);

}
