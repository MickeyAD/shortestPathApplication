package gr.university.thesis.service;

import gr.university.thesis.entity.Leg;
import gr.university.thesis.exception.ResourceExistsException;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.LegRepository;
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
public class LegService {

    @Autowired
    private LegRepository legRepository;

    public ResponseEntity<Leg> addLeg(Leg leg) {
        try {
            Leg newLeg = new Leg();
            newLeg.setSource(leg.getSource());
            newLeg.setDestination(leg.getDestination());
            newLeg.setDistanceCost(leg.getDistanceCost());
            newLeg.setTimeCost(leg.getTimeCost());
            legRepository.save(newLeg);
            return new ResponseEntity<>(newLeg, HttpStatus.CREATED);
        } catch (ResourceExistsException resourceExistsException) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<Leg> updateLeg(int id, Leg legDetails) {
        try {
            Optional<Leg> leg = legRepository.findById(id);
            Leg currentLeg = leg.get();
            currentLeg.setSource(legDetails.getSource());
            currentLeg.setDestination(legDetails.getDestination());
            currentLeg.setDistanceCost(legDetails.getDistanceCost());
            currentLeg.setTimeCost(legDetails.getTimeCost());
            legRepository.save(currentLeg);
            return new ResponseEntity<>(currentLeg, HttpStatus.CREATED);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Leg> findLegById(int id) {
        try {
            Optional<Leg> legOptional = legRepository.findById(id);
            Leg leg = legOptional.get();
            return new ResponseEntity<>(leg, HttpStatus.OK);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Leg>> findAllLegs() {
        try {
            List<Leg> legList = new ArrayList<>();
            legRepository.findAll().forEach(legList::add);
            if (!legList.isEmpty()) {
                return new ResponseEntity<>(legList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<HttpStatus> deleteLegById(int id) {
        try {
            legRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteAllLegs() {
        try {
            legRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}