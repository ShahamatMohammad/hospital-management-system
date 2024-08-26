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

    // Associate patient with a hospital and return a message
    public String addPatientToHospital(Long hospitalId, Long patientId) {
        Hospital hospital = findById(hospitalId); // Assume findById() is a method to retrieve Hospital by id
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found", HttpStatus.NOT_FOUND, 2005));

        patient.setHospital(hospital);
        patientRepository.save(patient);

        return "Patient, " + patient.getFirstName() + " " + patient.getLastName() + " has been successfully added to hospital " + hospital.getName() + ".";
    }


    // Associate employee with a hospital
    public String addEmployeeToHospital(Long hospitalId, Long employeeId) {
        Hospital hospital = findById(hospitalId);
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomException("Employee not found", HttpStatus.NOT_FOUND, 2007));
        employee.setHospital(hospital);
        employeeRepository.save(employee);
        return employee.getFirstName() + " " + employee.getLastName() + " has been assigned to hospital: " + hospital.getName();
    }


    // Associate secretary with a hospital
    public String addSecretaryToHospital(Long hospitalId, Long secretaryId) {
        Hospital hospital = findById(hospitalId);
        Secretary secretary = secretaryRepository.findById(secretaryId)
                .orElseThrow(() -> new CustomException("Secretary not found", HttpStatus.NOT_FOUND, 2008));
        secretary.setHospital(hospital);
        secretaryRepository.save(secretary);
        return secretary.getFirstName() + " " +  secretary.getLastName() +  " has been associate with : " + secretary.getHospital().getName();}

    // Remove patient from hospital
    public String removePatientFromHospital(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found", HttpStatus.NOT_FOUND, 2005));

        if (patient.getHospital() == null) {
            throw new CustomException("Patient is not associated with any hospital", HttpStatus.BAD_REQUEST, 2006);
        }

        String hospitalName = patient.getHospital().getName();
        patient.setHospital(null);
        patientRepository.save(patient);

        return "Patient, " + patient.getFirstName() + " " + patient.getLastName() + " has been successfully removed from hospital " + hospitalName + ".";
    }


    // Remove employee from hospital
    public String removeEmployeeFromHospital(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new CustomException("Employee not found", HttpStatus.NOT_FOUND, 2007));

        String hospitalName = employee.getHospital() != null ? employee.getHospital().getName() : "No hospital";

        employee.setHospital(null);
        employeeRepository.save(employee);

        return "Employee, " + employee.getFirstName() + " " + employee.getLastName() + " has been removed from hospital: " + hospitalName;
    }


    // Remove secretary from hospital
    public String removeSecretaryFromHospital(Long secretaryId) {
        Secretary secretary = secretaryRepository.findById(secretaryId)
                .orElseThrow(() -> new CustomException("Secretary not found", HttpStatus.NOT_FOUND, 2008));

        // Check if the secretary is not associated with any hospital
        if (secretary.getHospital() == null) {
            throw new CustomException(secretary.getFirstName() + " " +  secretary.getLastName() +" is not associated with any hospital", HttpStatus.BAD_REQUEST, 2009);
        }

        String hospitalName = secretary.getHospital().getName();
        secretary.setHospital(null);
        secretaryRepository.save(secretary);

        return "Secretary, " + secretary.getFirstName() + " " +  secretary.getLastName() +  " has been removed from: " + hospitalName;
    }

}
