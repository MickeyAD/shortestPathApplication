package gr.university.thesis.dto;

import gr.university.thesis.entity.Station;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDetailsDTO {

    Station station1;
    Station station2;

    Date now;

    public StationDetailsDTO(Station station1, Station station2) {
        this.station1 = station1;
        this.station2 = station2;
        this.now = new Date();
    }
}
