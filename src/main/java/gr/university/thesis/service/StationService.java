package gr.university.thesis.service;

import gr.university.thesis.entity.Station;
import gr.university.thesis.exception.ResourceNotFoundException;
import gr.university.thesis.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    public List<Station> findStations() {
        return stationRepository.findAll();
    }

    public Station findStationById(Integer id) throws ResourceNotFoundException {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isPresent()) {
            return station.get();
        } else {
            throw new ResourceNotFoundException("Could not find station with id: " + id);
        }
    }

//    public Station createStation(Station station) {
//        return stationRepository.save(station);
//    }
//
//    public void deleteStationById(Integer id) {
//        stationRepository.deleteById(id);
//    }
//
//    public void deleteAllStations() {
//        stationRepository.deleteAll();
//    }
//
//    public Optional<Station> findStationById(Integer id) {
//        return stationRepository.findById(id);
//    }
//
//    public Station updateStation(Station station) {
//        return stationRepository.save(station);
//    }
}
