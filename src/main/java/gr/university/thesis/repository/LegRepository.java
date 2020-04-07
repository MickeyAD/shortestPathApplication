package gr.university.thesis.repository;

import gr.university.thesis.entity.Leg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegRepository extends JpaRepository<Leg, Integer> {
}
