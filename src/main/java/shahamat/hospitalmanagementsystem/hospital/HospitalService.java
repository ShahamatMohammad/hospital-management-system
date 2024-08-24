package shahamat.hospitalmanagementsystem.hospital;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shahamat.hospitalmanagementsystem.employee.Employee;
import shahamat.hospitalmanagementsystem.employee.EmployeeRepository;
import shahamat.hospitalmanagementsystem.exception.CustomException;
import shahamat.hospitalmanagementsystem.patient.Patient;
import shahamat.hospitalmanagementsystem.patient.PatientRepository;
import shahamat.hospitalmanagementsystem.secretary.Secretary;
import shahamat.hospitalmanagementsystem.secretary.SecretaryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final PatientRepository patientRepository;
    private final EmployeeRepository employeeRepository;
    private final SecretaryRepository secretaryRepository;

    // Save or update a hospital using HospitalDTO
    public Hospital save(HospitalDTO hospitalDTO) {
        Hospital hospital = new Hospital();
        hospital.setName(hospitalDTO.getName());
        hospital.setAddress(hospitalDTO.getAddress());
        hospital.setPhone(hospitalDTO.getPhone());
        hospital.setEmail(hospitalDTO.getEmail());
        hospital.setLocation(hospitalDTO.getLocation());
        hospital.setCapacity(hospitalDTO.getCapacity());
        return hospitalRepository.save(hospital);
    }

    // Find hospital by ID
    public Hospital findById(Long id) {
        return hospitalRepository.findById(id).orElseThrow(() ->
                new CustomException("Hospital not found", HttpStatus.NOT_FOUND, 2004));
    }

    // Find all hospitals
    public List<Hospital> findAll() {
        return hospitalRepository.findAll();
    }

    // Find hospitals by name
    public List<Hospital> findByName(String name) {
        return hospitalRepository.findByName(name);
    }

    // Find hospitals by location
    public List<Hospital> findByLocation(String location) {
        return hospitalRepository.findByLocation(location);
    }

    // Find hospitals by capacity
    public List<Hospital> findByCapacity(int capacity) {
        return hospitalRepository.findByCapacityGreaterThanEqual(capacity);
    }

    // Delete hospital by ID
    public void deleteById(Long id) {
        if (hospitalRepository.existsById(id)) {
            hospitalRepository.deleteById(id);
        } else {
            throw new CustomException("Hospital not found", HttpStatus.NOT_FOUND, 2004);
        }
    }

    // Associate patient with a hospital
    public Hospital addPatientToHospital(Long hospitalId, Long patientId) {
        Hospital hospital = findById(hospitalId);
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found", HttpStatus.NOT_FOUND, 2005));
        patient.setHospital(hospital);
        patientRepository.save(patient);
        return hospital;
    }

    // Associate employee with a hospital
    public Hospital addEmployeeToHospital(Long hospitalId, Long employeeId) {
        Hospital hospital = findById(hospitalId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomException("Employee not found", HttpStatus.NOT_FOUND, 2007));
        employee.setHospital(hospital);
        employeeRepository.save(employee);
        return hospital;
    }

    // Associate secretary with a hospital
    public Hospital addSecretaryToHospital(Long hospitalId, Long secretaryId) {
        Hospital hospital = findById(hospitalId);
        Secretary secretary = secretaryRepository.findById(secretaryId)
                .orElseThrow(() -> new CustomException("Secretary not found", HttpStatus.NOT_FOUND, 2008));
        secretary.setHospital(hospital);
        secretaryRepository.save(secretary);
        return hospital;
    }

    // Remove patient from hospital
    public Hospital removePatientFromHospital(Long hospitalId, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found", HttpStatus.NOT_FOUND, 2005));
        patient.setHospital(null);
        patientRepository.save(patient);
        return findById(hospitalId);
    }

    // Remove employee from hospital
    public Hospital removeEmployeeFromHospital(Long hospitalId, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomException("Employee not found", HttpStatus.NOT_FOUND, 2007));
        employee.setHospital(null);
        employeeRepository.save(employee);
        return findById(hospitalId);
    }

    // Remove secretary from hospital
    public Hospital removeSecretaryFromHospital(Long hospitalId, Long secretaryId) {
        Secretary secretary = secretaryRepository.findById(secretaryId)
                .orElseThrow(() -> new CustomException("Secretary not found", HttpStatus.NOT_FOUND, 2008));
        secretary.setHospital(null);
        secretaryRepository.save(secretary);
        return findById(hospitalId);
    }
}
