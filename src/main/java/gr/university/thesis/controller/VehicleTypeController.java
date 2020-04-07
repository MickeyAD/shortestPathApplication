package gr.university.thesis.controller;

import gr.university.thesis.entity.VehicleType;
import gr.university.thesis.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @PostMapping("/addVehicleType")
    public VehicleType addVehicleType(@ModelAttribute VehicleType vehicleType) {
        return vehicleTypeService.addVehicleType(vehicleType);
    }

    @PostMapping("/updateVehicleType")
    public VehicleType updateVehicleType(@ModelAttribute VehicleType vehicleType) {
        return vehicleTypeService.updateVehicleType(vehicleType);
    }

    @GetMapping("/viewVehicleType/{id}")
    public VehicleType viewVehicleType(@PathVariable int id) {
        return vehicleTypeService.findVehicleTypeById(id);
    }

    @GetMapping("/viewAllVehicleTypes")
    public List<VehicleType> viewAllVehicleTypes() {
        return vehicleTypeService.findAllVehicleTypes();
    }

    @DeleteMapping("/deleteVehicleType/{id}")
    public void deleteVehicleType(@PathVariable int id) {
        vehicleTypeService.deleteVehicleTypeById(id);
    }

    @DeleteMapping("/deleteAllVehicleTypes")
    public void deleteAllVehicleTypes() {
        vehicleTypeService.deleteAllVehicleTypes();
    }
}
