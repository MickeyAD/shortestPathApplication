package gr.university.thesis.controller;

import gr.university.thesis.entity.VehicleType;
import gr.university.thesis.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @PostMapping("/vehicleType/add")
    public ResponseEntity<VehicleType> addVehicleType(@RequestBody VehicleType vehicleType) {
        return vehicleTypeService.addVehicleType(vehicleType);
    }

    @PutMapping("/vehicleType/update")
    public ResponseEntity<VehicleType> updateVehicleType(@PathVariable int id, @RequestBody VehicleType vehicleType) {
        return vehicleTypeService.updateVehicleType(id, vehicleType);
    }

    @GetMapping("/vehicleType/view/{id}")
    public ResponseEntity<VehicleType> viewVehicleType(@PathVariable int id) {
        return vehicleTypeService.findVehicleTypeById(id);
    }

    @GetMapping("/vehicleType/viewAll")
    public ResponseEntity<List<VehicleType>> viewAllVehicleTypes() {
        return vehicleTypeService.findAllVehicleTypes();
    }

    @DeleteMapping("/vehicleType/delete/{id}")
    public ResponseEntity<HttpStatus> deleteVehicleType(@PathVariable int id) {
        return vehicleTypeService.deleteVehicleTypeById(id);
    }

    @DeleteMapping("/vehicleType/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllVehicleTypes() {
        return vehicleTypeService.deleteAllVehicleTypes();
    }
}
