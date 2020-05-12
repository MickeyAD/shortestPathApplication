package gr.university.thesis.dto;

import gr.university.thesis.entity.Station;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Graph class represents a map/graph upon which stations exist and connect to each other.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Graph {

    // Set of stations (vertices)
    List<Station> stationsList;

}
