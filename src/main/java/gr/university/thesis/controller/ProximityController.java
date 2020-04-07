package gr.university.thesis.controller;

import gr.university.thesis.entity.Proximity;
import gr.university.thesis.service.ProximityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProximityController {

    @Autowired
    private ProximityService proximityService;

    @PostMapping("/addProximity")
    public Proximity addProximity(@ModelAttribute Proximity proximity) {
        return proximityService.addProximity(proximity);
    }

    @PostMapping("/updateProximity")
    public Proximity updateProximity(@ModelAttribute Proximity proximity) {
        return proximityService.updateProximity(proximity);
    }

    @GetMapping("/viewProximity/{id}")
    public Proximity viewProximity(@PathVariable int id) {
        return proximityService.findProximityById(id);
    }

    @GetMapping("/viewAllProximities")
    public List<Proximity> viewAllProximities() {
        return proximityService.findAllProximities();
    }

    @DeleteMapping("/deleteProximity/{id}")
    public void deleteProximity(@PathVariable int id) {
        proximityService.deleteProximityById(id);
    }

    @DeleteMapping("/deleteAllProximities")
    public void deleteAllProximities() {
        proximityService.deleteAllProximities();
    }
}
