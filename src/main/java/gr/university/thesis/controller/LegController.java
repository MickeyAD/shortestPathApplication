package gr.university.thesis.controller;

import gr.university.thesis.entity.Leg;
import gr.university.thesis.service.LegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LegController {

    @Autowired
    private LegService legService;

    @PostMapping("/addLeg")
    public Leg addLeg(@ModelAttribute Leg leg) {
        return legService.addLeg(leg);
    }

    @PostMapping("/updateLeg")
    public Leg updateLeg(@ModelAttribute Leg leg) {
        return legService.updateLeg(leg);
    }

    @GetMapping("/viewLeg/{id}")
    public Leg viewLeg(@PathVariable int id) {
        return legService.findLegById(id);
    }

    @GetMapping("/viewAllLegs")
    public List<Leg> viewAllLegs() {
        return legService.findAllLegs();
    }

    @DeleteMapping("/deleteLeg/{id}")
    public void deleteLeg(@PathVariable int id) {
        legService.deleteLegById(id);
    }

    @DeleteMapping("/deleteAllLegs")
    public void deleteAllLegs() {
        legService.deleteAllLegs();
    }

}
