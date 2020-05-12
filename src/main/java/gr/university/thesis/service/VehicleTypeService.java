package gr.university.thesis.service;

import gr.university.thesis.entity.VehicleType;
import gr.university.thesis.exception.ResourceExistsException;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.VehicleTypeRepository;
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
public class VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    public ResponseEntity<VehicleType> addVehicleType(VehicleType vehicleType) {
        try {
            VehicleType newVehicleType = new VehicleType();
            newVehicleType.setName(vehicleType.getName());
            vehicleTypeRepository.save(newVehicleType);
            return new ResponseEntity<>(newVehicleType, HttpStatus.CREATED);
        } catch (ResourceExistsException resourceExistsException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<VehicleType> updateVehicleType(int id, VehicleType vehicleTypeDetails) {
        try {
            Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(id);
            VehicleType currentVehicleType = vehicleType.get();
            currentVehicleType.setName(vehicleTypeDetails.getName());
            vehicleTypeRepository.save(currentVehicleType);
            return new ResponseEntity<>(currentVehicleType, HttpStatus.CREATED);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<VehicleType> findVehicleTypeById(int id) {
        try {
            Optional<VehicleType> vehicleTypeOptional = vehicleTypeRepository.findById(id);
            VehicleType vehicleType = vehicleTypeOptional.get();
            return new ResponseEntity<>(vehicleType, HttpStatus.OK);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<VehicleType>> findAllVehicleTypes() {
        try {
            List<VehicleType> vehicleTypeList = new ArrayList<>();
            vehicleTypeRepository.findAll().forEach(vehicleTypeList::add);
            if (!vehicleTypeList.isEmpty()) {
                return new ResponseEntity<>(vehicleTypeList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteVehicleTypeById(int id) {
        try {
            vehicleTypeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllVehicleTypes() {
        try {
            vehicleTypeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
