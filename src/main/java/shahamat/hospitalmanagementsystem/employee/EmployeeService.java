package shahamat.hospitalmanagementsystem.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shahamat.hospitalmanagementsystem.exception.CustomException;
import shahamat.hospitalmanagementsystem.hospital.Hospital;
import shahamat.hospitalmanagementsystem.hospital.HospitalRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final HospitalRepository hospitalRepository;

    // Save or update an employee using EmployeeDTO
    public Employee save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setPosition(employeeDTO.getPosition());
        employee.setPhone(employeeDTO.getPhone());
        employee.setEmail(employeeDTO.getEmail());
        employee.setRole(employeeDTO.getRole());
        employee.setDepartment(employeeDTO.getDepartment());

        if (employeeDTO.getHospitalId() != null) {
            Hospital hospital = hospitalRepository.findById(employeeDTO.getHospitalId())
                    .orElseThrow(() -> new CustomException("Hospital not found", HttpStatus.NOT_FOUND, 2004));
            employee.setHospital(hospital);
        }

        return employeeRepository.save(employee);
    }

    // Find employee by ID
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() ->
                new CustomException("Employee not found", HttpStatus.NOT_FOUND, 2007));
    }

    // Find all employees
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // Find employees by role
    public List<Employee> findByRole(String role) {
        return employeeRepository.findByRole(role);
    }

    // Find employees by department
    public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartment(department);
    }

    // Delete employee by ID
    public void deleteById(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new CustomException("Employee not found", HttpStatus.NOT_FOUND, 2007);
        }
    }

    // Find employees by hospital
    public List<Employee> findByHospital(Long hospitalId) {
        return employeeRepository.findByHospital_Id(hospitalId);
    }

    // Associate employee with a hospital
    public Employee addEmployeeToHospital(Long employeeId, Long hospitalId) {
        Employee employee = findById(employeeId);
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new CustomException("Hospital not found", HttpStatus.NOT_FOUND, 2004));
        employee.setHospital(hospital);
        return employeeRepository.save(employee);
    }

    // Remove employee from a hospital
    public Employee removeEmployeeFromHospital(Long employeeId) {
        Employee employee = findById(employeeId);
        employee.setHospital(null);
        return employeeRepository.save(employee);
    }
}
