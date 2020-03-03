package gr.university.thesis.controller;

import gr.university.thesis.dto.StationDetailsDTO;
import gr.university.thesis.entity.Station;
import gr.university.thesis.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class APIController {

    @Autowired
    private StationService stationService;

    @GetMapping("/AllStations")
    public List<Station> test() {
        return stationService.findAllStations();
    }

    @GetMapping("/{firstStationId}/{secondStationId}")
    public StationDetailsDTO inputFromUser(@PathVariable int firstStationId,
                                           @PathVariable int secondStationId) {
        return stationService.findTwoStations(firstStationId, secondStationId);
    }

    @GetMapping("/calculate/{firstStationId}/{secondStationId}")
    public double inputFromUser2(@PathVariable int firstStationId,
                                 @PathVariable int secondStationId) {
        Station station = new Station();
        station.setId(firstStationId);

        Station station2 = new Station();
        station2.setId(secondStationId);

        return stationService.dijktraAlgorithmTimeCost(station, station2);
    }
}

