package gr.university.thesis.controller;

import gr.university.thesis.entity.Vehicle;
import gr.university.thesis.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/vehicle/add")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle);
    }

    @PutMapping("/vehicle/update/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable int id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    @GetMapping("/vehicle/view/{id}")
    public ResponseEntity<Vehicle> viewVehicle(@PathVariable int id) {
        return vehicleService.findVehicleById(id);
    }

    @GetMapping("/vehicle/viewAll")
    public ResponseEntity<List<Vehicle>> viewAllVehicles() {
        return vehicleService.findAllVehicles();
    }

    @DeleteMapping("/vehicle/delete/{id}")
    public ResponseEntity<HttpStatus> deleteVehicle(@PathVariable int id) {
        return vehicleService.deleteVehicleById(id);
    }

    @DeleteMapping("/vehicle/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllVehicles() {
        return vehicleService.deleteAllVehicles();
    }
}
