package hr.fer.ruazosa.trackmyroute.service;

import hr.fer.ruazosa.trackmyroute.model.User;
import hr.fer.ruazosa.trackmyroute.model.UserBasic;
import hr.fer.ruazosa.trackmyroute.repository.UserRepository;
import hr.fer.ruazosa.trackmyroute.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean checkUsernameUnique(User user) {
        List userList = userRepository.findByUserName(user.getUsername());
        if (userList.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public User loginUser(UserBasic userBasic) {
        List<User> loggedUserList = userRepository.findByUserNameAndPassword(userBasic.username, userBasic.password);
        if (loggedUserList.isEmpty()) {
            return null;
        }
        return userRepository.findByUserNameAndPassword(userBasic.username, userBasic.password).get(0);
    }

    @Override
    public User getUserByUsernameAndPassword(UserBasic userBasic) {
        List<User> loggedUserList = userRepository.findByUserNameAndPassword(userBasic.username,userBasic.password);
        if (loggedUserList.isEmpty()) {
            return null;
        }
        return userRepository.findByUserNameAndPassword(userBasic.username,userBasic.password).get(0);
    }




}
