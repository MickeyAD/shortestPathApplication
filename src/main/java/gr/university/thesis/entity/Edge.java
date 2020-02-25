package gr.university.thesis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//Edge class representing the distance between two vertices
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Edge {

    //Generated id of each edge.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //First end of an edge called source
    @ManyToOne
    @JoinColumn(name = "source_id")
    private Station source;

    //Second end of an edge called destination
    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Station destination;

    //Distance cost of each edge
    @Column
    private int distanceCost;

    //Time cost of each edge
    @Column
    private int timeCost;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicleRoute;

}
