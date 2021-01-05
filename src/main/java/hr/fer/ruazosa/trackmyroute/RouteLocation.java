package hr.fer.ruazosa.trackmyroute;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
    private Float latitude;

    @Column(name = "longitude")
    @NotNull(message = "longitude cannot be null")
    private Float longitude;

    @Column(name = "moment")
    @NotNull(message = "moment cannot be null")
    private Date moment;

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

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Date getMoment() { return moment; }

    public void setMoment(Date moment) { this.moment = moment; }

}
