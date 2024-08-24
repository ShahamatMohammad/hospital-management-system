package shahamat.hospitalmanagementsystem.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shahamat.hospitalmanagementsystem.exception.CustomException;
import shahamat.hospitalmanagementsystem.hospital.Hospital;
import shahamat.hospitalmanagementsystem.hospital.HospitalRepository;
import shahamat.hospitalmanagementsystem.patient.Patient;
import shahamat.hospitalmanagementsystem.patient.PatientRepository;
import shahamat.hospitalmanagementsystem.secretary.SecretaryRepository;

import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;
    private final SecretaryRepository secretaryRepository;

    // Save or update a doctor using DoctorDTO
    public Doctor save(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor.setLastName(doctorDTO.getLastName());
        doctor.setSpecialty(doctorDTO.getSpecialty());
        doctor.setPhone(doctorDTO.getPhone());
        doctor.setEmail(doctorDTO.getEmail());

        if (doctorDTO.getHospitalIds() != null) {
            List<Hospital> hospitals = hospitalRepository.findAllById(doctorDTO.getHospitalIds());
            doctor.setHospitals(hospitals);
        }

        if (doctorDTO.getPatientIds() != null) {
            List<Patient> patients = patientRepository.findAllById(doctorDTO.getPatientIds());
            doctor.setPatients(patients);
        }

        if (doctorDTO.getSecretaryId() != null) {
            doctor.setSecretary(secretaryRepository.findById(doctorDTO.getSecretaryId())
                    .orElseThrow(() -> new CustomException("Secretary not found", HttpStatus.NOT_FOUND, 2006)));
        }

        return doctorRepository.save(doctor);
    }

    // Other methods remain the same as before
    @Transactional
    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElseThrow(() ->
                new CustomException("Doctor not found", HttpStatus.NOT_FOUND, 2003));
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public List<Doctor> findBySpecialty(String specialty) {
        return doctorRepository.findBySpecialty(specialty);
    }

    public void deleteById(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
        } else {
            throw new CustomException("Doctor not found", HttpStatus.NOT_FOUND, 2003);
        }
    }

    public Doctor addDoctorToHospital(Long doctorId, Long hospitalId) {
        Doctor doctor = findById(doctorId);
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new CustomException("Hospital not found", HttpStatus.NOT_FOUND, 2004));
        doctor.getHospitals().add(hospital);
        return doctorRepository.save(doctor);
    }

    public Doctor removeDoctorFromHospital(Long doctorId, Long hospitalId) {
        Doctor doctor = findById(doctorId);
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new CustomException("Hospital not found", HttpStatus.NOT_FOUND, 2004));
        doctor.getHospitals().remove(hospital);
        return doctorRepository.save(doctor);
    }

    public List<Doctor> findDoctorsByHospital(Long hospitalId) {
        return doctorRepository.findByHospitals_Id(hospitalId);
    }

    public Doctor addPatientToDoctor(Long doctorId, Long patientId) {
        Doctor doctor = findById(doctorId);
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found", HttpStatus.NOT_FOUND, 2005));
        doctor.getPatients().add(patient);
        return doctorRepository.save(doctor);
    }

    public Doctor removePatientFromDoctor(Long doctorId, Long patientId) {
        Doctor doctor = findById(doctorId);
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found", HttpStatus.NOT_FOUND, 2005));
        doctor.getPatients().remove(patient);
        return doctorRepository.save(doctor);
    }
}
