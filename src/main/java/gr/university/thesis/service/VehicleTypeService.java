package gr.university.thesis.service;

import gr.university.thesis.entity.VehicleType;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class VehicleTypeService {

    private VehicleTypeRepository vehicleTypeRepository;

    public VehicleType addVehicleType(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    public VehicleType updateVehicleType(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    public VehicleType findVehicleTypeById(Integer id) {
        Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(id);
        if (vehicleType.isPresent()) {
            return vehicleType.get();
        } else {
            throw new ResourceNotFoundException("Could not find vehicle type with id: " + id);
        }
    }

    public List<VehicleType> findAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }

    public void deleteVehicleTypeById(Integer id) {
        vehicleTypeRepository.deleteById(id);
    }

    public void deleteAllVehicleTypes() {
        vehicleTypeRepository.deleteAll();
    }
}
