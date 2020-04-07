package gr.university.thesis.service;

import gr.university.thesis.entity.Leg;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.LegRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LegService {

    @Autowired
    private LegRepository legRepository;

    public Leg addLeg(Leg leg) {
        return legRepository.save(leg);
    }

    public Leg updateLeg(Leg leg) {
        return legRepository.save(leg);
    }

    public Leg findLegById(Integer id) throws ResourceNotFoundException {
        Optional<Leg> leg = legRepository.findById(id);
        if (leg.isPresent()) {
            return leg.get();
        } else {
            throw new ResourceNotFoundException("Could not find leg with id: " + id);
        }
    }

    public List<Leg> findAllLegs() {
        return legRepository.findAll();
    }

    public void deleteLegById(int id) {
        legRepository.deleteById(id);
    }

    public void deleteAllLegs() {
        legRepository.deleteAll();
    }

}