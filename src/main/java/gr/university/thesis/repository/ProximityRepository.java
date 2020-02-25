package gr.university.thesis.repository;

import gr.university.thesis.entity.Proximity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProximityRepository extends JpaRepository<Proximity, Integer> {
}
