package gr.university.thesis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    //Generated id of each transportation vehicle
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    //Name of each vertex
    @Column
    private String name;

    //Description of each transportation line
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "vehicleType_id")
    private VehicleType vehicleType;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicleRoute")
    private List<Edge> route;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehicleSchedule")
    private List<Schedule> timeline;

}
