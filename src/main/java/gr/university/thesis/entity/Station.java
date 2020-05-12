package gr.university.thesis.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @JsonIgnore
    private Proximity proximity;

    // Name of each station
    @Column
    @NotBlank
    private String name;

    // Geographical coordinates of each station
    @Column
    @NotBlank
    private BigDecimal latitude;

    @Column
    @NotBlank
    private BigDecimal longitude;

    // Boolean which determines whether a station has been visited or not.
    @Transient
    @JsonIgnore
    private boolean visited;

    // List of adjacent edges of each vertex
    @Transient
    @EqualsAndHashCode.Exclude
    @JsonBackReference
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
