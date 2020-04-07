package gr.university.thesis.service;

import gr.university.thesis.entity.Vehicle;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private VehicleRepository vehicleRepository;

    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Vehicle findVehicleById(Integer id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        if (vehicle.isPresent()) {
            return vehicle.get();
        } else {
            throw new ResourceNotFoundException("Could not find vehicle with id: " + id);
        }
    }

    public List<Vehicle> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    public void deleteVehicleById(Integer id) {
        vehicleRepository.deleteById(id);
    }

    public void deleteAllVehicles() {
        vehicleRepository.deleteAll();
    }

}
