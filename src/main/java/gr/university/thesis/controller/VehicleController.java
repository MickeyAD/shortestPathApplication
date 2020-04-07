package gr.university.thesis.controller;

import gr.university.thesis.entity.Vehicle;
import gr.university.thesis.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/addVehicle")
    public Vehicle addVehicle(@ModelAttribute Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @PostMapping("/updateVehicle")
    public Vehicle updateVehicle(@ModelAttribute Vehicle vehicle) {
        return vehicleService.updateVehicle(vehicle);
    }

    @GetMapping("/viewVehicle/{id}")
    public Vehicle viewVehicle(@PathVariable int id) {
        return vehicleService.findVehicleById(id);
    }

    @GetMapping("/viewAllVehicles")
    public List<Vehicle> viewAllVehicles() {
        return vehicleService.findAllVehicles();
    }

    @DeleteMapping("/deleteVehicle/{id}")
    public void deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicleById(id);
    }

    @DeleteMapping("/deleteAllVehicles")
    public void deleteAllVehicles() {
        vehicleService.deleteAllVehicles();
    }
}
