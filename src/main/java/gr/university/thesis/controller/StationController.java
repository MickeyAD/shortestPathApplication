package gr.university.thesis.controller;

import gr.university.thesis.entity.Station;
import gr.university.thesis.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StationController {

    @Autowired
    private StationService stationService;

    @PostMapping("/station/add")
    public ResponseEntity<Station> addStation(@Valid @RequestBody Station station) {
        return stationService.addStation(station);
    }

    @PutMapping("/station/update/{id}")
    public ResponseEntity<Station> updateStation(@PathVariable int id, @Valid @RequestBody Station station) {
        return stationService.updateStation(id, station);
    }

    @GetMapping("/station/view/{id}")
    public ResponseEntity<Station> viewStation(@PathVariable int id) {
        return stationService.findStationById(id);
    }

    @GetMapping("/station/viewAll")
    public ResponseEntity<List<Station>> viewAllStations() {
        return stationService.findAllStations();
    }

    @DeleteMapping("/station/delete/{id}")
    public ResponseEntity<HttpStatus> deleteStation(@PathVariable int id) {
        return stationService.deleteStationById(id);
    }

    @DeleteMapping("/station/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllStations() {
        return stationService.deleteAllStations();
    }

//    @GetMapping("/{firstStationId}/{secondStationId}")
//    public StationDetailsDTO inputFromUser(@PathVariable int firstStationId,
//                                           @PathVariable int secondStationId) {
//        return stationService.findTwoStations(firstStationId, secondStationId);
//    }

}

