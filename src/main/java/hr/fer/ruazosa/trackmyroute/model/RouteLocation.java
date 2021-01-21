package hr.fer.ruazosa.trackmyroute.model;

import hr.fer.ruazosa.trackmyroute.model.Route;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="route_locations")

public class RouteLocation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "route_location_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="route_id")
    private Route route;

    @Column(name = "latitude")
    @NotNull(message = "latitude cannot be null")
    private Double latitude;

    @Column(name = "longitude")
    @NotNull(message = "longitude cannot be null")
    private Double longitude;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Route getRoute() { return route; }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


}
