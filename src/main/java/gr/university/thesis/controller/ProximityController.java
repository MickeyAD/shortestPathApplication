package gr.university.thesis.controller;

import gr.university.thesis.entity.Proximity;
import gr.university.thesis.service.ProximityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProximityController {

    @Autowired
    private ProximityService proximityService;

    @PostMapping("/proximity/add")
    public ResponseEntity<Proximity> addProximity(@RequestBody Proximity proximity) {
        return proximityService.addProximity(proximity);
    }

    @PostMapping("/proximity/update/{id}")
    public ResponseEntity<Proximity> updateProximity(@PathVariable int id, @RequestBody Proximity proximity) {
        return proximityService.updateProximity(id, proximity);
    }

    @GetMapping("/proximity/view/{id}")
    public ResponseEntity<Proximity> viewProximity(@PathVariable int id) {
        return proximityService.findProximityById(id);
    }

    @GetMapping("/proximity/viewAll")
    public ResponseEntity<List<Proximity>> viewAllProximities() {
        return proximityService.findAllProximities();
    }

    @DeleteMapping("/proximity/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProximity(@PathVariable int id) {
        return proximityService.deleteProximityById(id);
    }

    @DeleteMapping("/proximity/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllProximities() {
        return proximityService.deleteAllProximities();
    }
}
