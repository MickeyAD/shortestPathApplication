package gr.university.thesis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

// Graph class represents a map/graph upon which stations exist and connect to each other.
@Data
@AllArgsConstructor
public class Graph {

    // Set of stations (vertices)
    private Set<Station> stations;

    // Set of legs (edges)
    private Set<Leg> legs;

    // Empty constructor
    public Graph() {
        new HashSet<Station>();
        new HashSet<Leg>();
    }

}
