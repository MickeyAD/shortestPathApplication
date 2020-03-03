package gr.university.thesis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


// Station class
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Station {

    // Generated id of each station
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "proximity_id")
    private Proximity proximity;

    // Name of each station
    @Column
    private String name;

    // Geographical coordinates of each station
    @Column
    private BigDecimal latitude;
    @Column
    private BigDecimal longitude;

    // Boolean which determines whether a station has been visited or not.
    @Transient
    private boolean visited;

    // List of adjacent edges of each vertex
    @Transient
    @EqualsAndHashCode.Exclude
    private List<Leg> adjacentLegs;

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
