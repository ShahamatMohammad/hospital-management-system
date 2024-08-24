package shahamat.hospitalmanagementsystem.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shahamat.hospitalmanagementsystem.disease.Disease;
import shahamat.hospitalmanagementsystem.disease.DiseaseRepository;
import shahamat.hospitalmanagementsystem.doctor.Doctor;
import shahamat.hospitalmanagementsystem.doctor.DoctorRepository;
import shahamat.hospitalmanagementsystem.exception.CustomException;
import shahamat.hospitalmanagementsystem.hospital.Hospital;
import shahamat.hospitalmanagementsystem.hospital.HospitalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final DiseaseRepository diseaseRepository;

    // Save or update a patient using PatientDTO
    public Patient save(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setPhone(patientDTO.getPhone());
        patient.setEmail(patientDTO.getEmail());
        patient.setAddress(patientDTO.getAddress());
        patient.setAge(patientDTO.getAge());
        return patientRepository.save(patient);
    }

    // Find patient by ID
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElseThrow(() ->
                new CustomException("Patient not found", HttpStatus.NOT_FOUND, 2005));
    }

    // Find all patients
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    // Find patients by name
    public List<Patient> findByName(String name) {
        return patientRepository.findByLastName(name);
    }

    // Find patients by age
    public List<Patient> findByAge(int age) {
        return patientRepository.findByAge(age);
    }

    // Find patients by hospital ID
    public List<Patient> findByHospitalId(Long hospitalId) {
        return patientRepository.findByHospital_Id(hospitalId);
    }

    // Delete patient by ID
    public void deleteById(Long id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        } else {
            throw new CustomException("Patient not found", HttpStatus.NOT_FOUND, 2005);
        }
    }

    // Associate patient with a doctor
    public Patient addDoctorToPatient(Long patientId, Long doctorId) {
        Patient patient = findById(patientId);
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new CustomException("Doctor not found", HttpStatus.NOT_FOUND, 2006));
        patient.getDoctors().add(doctor);
        return patientRepository.save(patient);
    }

    // Associate patient with a hospital
    public Patient addPatientToHospital(Long patientId, Long hospitalId) {
        Patient patient = findById(patientId);
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new CustomException("Hospital not found", HttpStatus.NOT_FOUND, 2004));
        patient.setHospital(hospital);
        return patientRepository.save(patient);
    }

    // Associate patient with a disease
    public Patient addDiseaseToPatient(Long patientId, Long diseaseId) {
        Patient patient = findById(patientId);
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new CustomException("Disease not found", HttpStatus.NOT_FOUND, 2009));
        patient.getDiseases().add(disease);
        return patientRepository.save(patient);
    }

    // Remove doctor from patient
    public Patient removeDoctorFromPatient(Long patientId, Long doctorId) {
        Patient patient = findById(patientId);
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new CustomException("Doctor not found", HttpStatus.NOT_FOUND, 2006));
        patient.getDoctors().remove(doctor);
        return patientRepository.save(patient);
    }

    // Remove disease from patient
    public Patient removeDiseaseFromPatient(Long patientId, Long diseaseId) {
        Patient patient = findById(patientId);
        Disease disease = diseaseRepository.findById(diseaseId)
                .orElseThrow(() -> new CustomException("Disease not found", HttpStatus.NOT_FOUND, 2009));
        patient.getDiseases().remove(disease);
        return patientRepository.save(patient);
    }
}
