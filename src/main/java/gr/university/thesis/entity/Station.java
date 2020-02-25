package gr.university.thesis.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


//Station class represents all vertices in the graph
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {

    //Generated id of each station
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //Name of each station
    @Column
    private String name;

    //Geographical coordinates of each station
    @Column
    private double latitude;
    @Column
    private double longitude;

    //Boolean which determines whether a station has been visited or not.
    @Transient
    private boolean visited;

    //List of adjacent edges of each vertex
    @Transient
    @EqualsAndHashCode.Exclude private List<Edge> adjacentEdges;

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
