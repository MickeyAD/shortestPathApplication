package gr.university.thesis.service;

import gr.university.thesis.entity.Proximity;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.ProximityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProximityService {

    private ProximityRepository proximityRepository;

    public Proximity addProximity(Proximity proximity) {
        return proximityRepository.save(proximity);
    }

    public Proximity updateProximity(Proximity proximity) {
        return proximityRepository.save(proximity);
    }

    public Proximity findProximityById(Integer id) {
        Optional<Proximity> proximity = proximityRepository.findById(id);
        if (proximity.isPresent()) {
            return proximity.get();
        } else {
            throw new ResourceNotFoundException("Could not find proximity with id: " + id);
        }
    }

    public List<Proximity> findAllProximities() {
        return proximityRepository.findAll();
    }

    public void deleteProximityById(Integer id) {
        proximityRepository.deleteById(id);
    }

    public void deleteAllProximities() {
        proximityRepository.deleteAll();
    }

}
