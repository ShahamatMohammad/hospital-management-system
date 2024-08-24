package shahamat.hospitalmanagementsystem.secretary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecretaryRepository extends JpaRepository<Secretary, Long> {
    List<Secretary> findByFirstName(String name);
    List<Secretary> findByYearsOfExperienceGreaterThanEqual(int years);
    List<Secretary> findByHospital_Id(Long hospitalId);
}
