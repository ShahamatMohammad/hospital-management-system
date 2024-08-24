package shahamat.hospitalmanagementsystem.disease;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shahamat.hospitalmanagementsystem.exception.CustomException;
import shahamat.hospitalmanagementsystem.patient.Patient;
import shahamat.hospitalmanagementsystem.patient.PatientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;
    private final PatientRepository patientRepository;

    // Save or update a disease using DiseaseDTO
    public Disease save(DiseaseDTO diseaseDTO) {
        Disease disease = new Disease();
        disease.setName(diseaseDTO.getName());
        disease.setDescription(diseaseDTO.getDescription());
        disease.setSeverity(diseaseDTO.getSeverity());

        if (diseaseDTO.getPatientIds() != null) {
            List<Patient> patients = patientRepository.findAllById(diseaseDTO.getPatientIds());
            disease.setPatients(patients);
        }

        return diseaseRepository.save(disease);
    }

    // Other methods remain the same as before
    public Disease findById(Long id) {
        return diseaseRepository.findById(id).orElseThrow(() ->
                new CustomException("Disease not found", HttpStatus.NOT_FOUND, 2006));
    }

    public List<Disease> findAll() {
        return diseaseRepository.findAll();
    }

    public List<Disease> findByName(String name) {
        return diseaseRepository.findByName(name);
    }

    public List<Disease> findBySeverity(String severity) {
        return diseaseRepository.findBySeverity(severity);
    }

    public void deleteById(Long id) {
        if (diseaseRepository.existsById(id)) {
            diseaseRepository.deleteById(id);
        } else {
            throw new CustomException("Disease not found", HttpStatus.NOT_FOUND, 2006);
        }
    }

    public List<Disease> findByPatient(Long patientId) {
        return diseaseRepository.findByPatients_Id(patientId);
    }

    public Disease addDiseaseToPatient(Long diseaseId, Long patientId) {
        Disease disease = findById(diseaseId);
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found", HttpStatus.NOT_FOUND, 2005));
        disease.getPatients().add(patient);
        return diseaseRepository.save(disease);
    }

    public Disease removeDiseaseFromPatient(Long diseaseId, Long patientId) {
        Disease disease = findById(diseaseId);
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found", HttpStatus.NOT_FOUND, 2005));
        disease.getPatients().remove(patient);
        return diseaseRepository.save(disease);
    }
}
