package gr.university.thesis.controller;

import gr.university.thesis.dto.StationDetailsDTO;
import gr.university.thesis.entity.Station;
import gr.university.thesis.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StationController {

    @Autowired
    private StationService stationService;

    @PostMapping("/addStation")
    public Station addStation(@ModelAttribute Station station) {
        return stationService.addStation(station);
    }

    @PostMapping("/updateStation")
    public Station updateStation(@ModelAttribute Station station) {
        return stationService.updateStation(station);
    }

    @GetMapping("/viewStation/{id}")
    public Station viewStation(@PathVariable int id) {
        return stationService.findStationById(id);
    }

    @GetMapping("/viewAllStations")
    public List<Station> viewAllStations() {
        return stationService.findAllStations();
    }

    @DeleteMapping("/deleteStation/{id}")
    public void deleteStation(@PathVariable int id) {
        stationService.deleteStationById(id);
    }

    @DeleteMapping("/deleteAllStations")
    public void deleteAllStations() {
        stationService.deleteAllStations();
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

        return stationService.findShortestPathTimeCost(station, station2);
    }


}

