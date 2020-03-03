package gr.university.thesis.repository;

import gr.university.thesis.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    Station findStationById(Integer id);
}
