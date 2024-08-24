package shahamat.hospitalmanagementsystem.disease;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    List<Disease> findByName(String name);
    List<Disease> findBySeverity(String severity);
    List<Disease> findByPatients_Id(Long patientId);
}
