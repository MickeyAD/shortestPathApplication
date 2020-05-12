package gr.university.thesis.controller;

import gr.university.thesis.entity.Leg;
import gr.university.thesis.service.LegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LegController {

    @Autowired
    private LegService legService;

    @PostMapping("/leg/add")
    public ResponseEntity<Leg> addLeg(@RequestBody Leg leg) {
        return legService.addLeg(leg);
    }

    @PutMapping("/leg/update")
    public ResponseEntity<Leg> updateLeg(@PathVariable int id, @RequestBody Leg leg) {
        return legService.updateLeg(id, leg);
    }

    @GetMapping("/leg/view/{id}")
    public ResponseEntity<Leg> viewLeg(@PathVariable int id) {
        return legService.findLegById(id);
    }

    @GetMapping("/leg/viewAll")
    public ResponseEntity<List<Leg>> viewAllLegs() {
        return legService.findAllLegs();
    }

    @DeleteMapping("/leg/delete/{id}")
    public ResponseEntity<HttpStatus> deleteLeg(@PathVariable int id) {
        return legService.deleteLegById(id);
    }

    @DeleteMapping("/leg/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllLegs() {
        return legService.deleteAllLegs();
    }

}
