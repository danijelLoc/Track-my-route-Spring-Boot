package hr.fer.ruazosa.trackmyroute;

import java.util.Date;

public class RouteBasic {

    public Long route_id;
    public String name;
    public Date date;
    public User user_id;

    public Long getRoute_id() {return route_id;}
    public String getName() {return name;}
    public Date getDate() {return date;}
    public User getUser_id() {return user_id;}


}
