package shahamat.hospitalmanagementsystem.patient;

import shahamat.hospitalmanagementsystem.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByLastName(String name);
    List<Patient> findByAge(int age);
    List<Patient> findByHospital_Id(Long hospitalId);
}
