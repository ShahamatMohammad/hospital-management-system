package shahamat.hospitalmanagementsystem.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    List<Hospital> findByName(String name);
    List<Hospital> findByLocation(String location);
    List<Hospital> findByCapacityGreaterThanEqual(int capacity);
}
