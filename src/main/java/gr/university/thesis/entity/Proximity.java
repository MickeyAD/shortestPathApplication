package gr.university.thesis.entity;

import javax.persistence.*;

@Entity
public class Proximity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Station source;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Station destination;

    @Column
    private int totalDistance;

    @Column
    private int walkingTime;

    public Proximity() {}

    public Proximity(Station source, Station destination, int totalDistance) {
        this.source = source;
        this.destination = destination;
        this.totalDistance = totalDistance;
        this.walkingTime = 0;
    }

    public Station getSource() {
        return source;
    }

    public void setSource(Station source) {
        this.source = source;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public int getTotalDistance() { return totalDistance; }

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
