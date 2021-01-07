package hr.fer.ruazosa.trackmyroute;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u where u.username = ?1 and u.password = ?2")
    List<User> findByUserNameAndPassword(String userName, String password);

    @Query("SELECT u FROM User u where u.username = ?1")
    List<User> findByUserName(String userName);

    @Query("SELECT r FROM Route r where r.user_id.user_id = ?1")
    List<RouteBasic> findRouteBasicByUserId(Long user_id);

    @Query("SELECT r FROM Route r where r.user_id.user_id = ?1")
    List<Route> findRouteByUserId(Long user_id);
}
