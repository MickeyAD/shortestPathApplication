package gr.university.thesis.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Leg class representing a weighted value (e.g. distance, time, etc.) between two vertices.
@Entity
@Data
@NoArgsConstructor
public class Leg {

    // Generated id of each leg
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // First end of a leg called source
    @ManyToOne
    @JoinColumn(name = "source_id")
    private Station source;

    // Second end of a leg called destination
    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Station destination;

    // Distance cost of each leg
    @Column
    private int distanceCost;

    // Time cost of each leg
    @Column
    private int timeCost;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicleRoute;

    public Leg(Station source, Station destination, int distanceCost, int timeCost) {
        this.source = source;
        this.destination = destination;
        this.distanceCost = distanceCost;
        this.timeCost = timeCost;

    }
}
