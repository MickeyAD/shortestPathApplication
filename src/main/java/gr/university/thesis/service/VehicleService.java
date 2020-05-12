package gr.university.thesis.service;

import gr.university.thesis.entity.Vehicle;
import gr.university.thesis.exception.ResourceExistsException;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public ResponseEntity<Vehicle> addVehicle(Vehicle vehicle) {
        try {
            Vehicle newVehicle = new Vehicle();
            newVehicle.setName(vehicle.getName());
            newVehicle.setDescription(vehicle.getDescription());
            vehicleRepository.save(newVehicle);
            return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
        } catch (ResourceExistsException resourceExistsException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Vehicle> updateVehicle(int id, Vehicle vehicleDetails) {
        try {
            Optional<Vehicle> vehicle = vehicleRepository.findById(id);
            Vehicle currentVehicle = vehicle.get();
            currentVehicle.setName(vehicleDetails.getName());
            currentVehicle.setDescription(vehicleDetails.getDescription());
            vehicleRepository.save(currentVehicle);
            return new ResponseEntity<>(currentVehicle, HttpStatus.CREATED);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Vehicle> findVehicleById(int id) {
        try {
            Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
            Vehicle vehicle = vehicleOptional.get();
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Vehicle>> findAllVehicles() {
        try {
            List<Vehicle> vehicleList = new ArrayList<>();
            vehicleRepository.findAll().forEach(vehicleList::add);
            if (!vehicleList.isEmpty()) {
                return new ResponseEntity<>(vehicleList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteVehicleById(int id) {
        try {
            vehicleRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllVehicles() {
        try {
            vehicleRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
