package gr.university.thesis.service;

import gr.university.thesis.entity.Proximity;
import gr.university.thesis.exception.ResourceExistsException;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.ProximityRepository;
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
public class ProximityService {

    @Autowired
    private ProximityRepository proximityRepository;

    public ResponseEntity<Proximity> addProximity(Proximity proximity) {
        try {
            Proximity newProximity = new Proximity();
            newProximity.setProximityStations(proximity.getProximityStations());
            proximityRepository.save(newProximity);
            return new ResponseEntity<>(newProximity, HttpStatus.CREATED);
        } catch (ResourceExistsException resourceExistsException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Proximity> updateProximity(int id, Proximity proximityDetails) {
        try {
            Optional<Proximity> proximity = proximityRepository.findById(id);
            Proximity currentProximity = proximity.get();
            currentProximity.setProximityStations(proximityDetails.getProximityStations());
            proximityRepository.save(currentProximity);
            return new ResponseEntity<>(currentProximity, HttpStatus.CREATED);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Proximity> findProximityById(int id) {
        try {
            Optional<Proximity> proximityOptional = proximityRepository.findById(id);
            Proximity proximity = proximityOptional.get();
            return new ResponseEntity<>(proximity, HttpStatus.OK);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Proximity>> findAllProximities() {
        try {
            List<Proximity> proximityList = new ArrayList<>();
            proximityRepository.findAll().forEach(proximityList::add);
            if (!proximityList.isEmpty()) {
                return new ResponseEntity<>(proximityList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteProximityById(int id) {
        try {
            proximityRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllProximities() {
        try {
            proximityRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
