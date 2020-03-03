package gr.university.thesis.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Proximity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "proximity")
    private List<Station> proximityStations;

    @Column
    private int totalDistance;

    @Column
    private int walkingTime;

    public Proximity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Station> getProximityStations() {
        return proximityStations;
    }

    public void setProximityStations(List<Station> proximityStations) {
        this.proximityStations = proximityStations;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getWalkingTime() {
        return walkingTime;
    }

    public void setWalkingTime(int walkingTime) {
        //A pre-determined distance threshold value.
        if (this.getTotalDistance() >= 500) {
            this.walkingTime = walkingTime;
        } else {
            System.out.println("Not recommended to walk distance above 500 meters!");
        }
    }
}
