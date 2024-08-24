package shahamat.hospitalmanagementsystem.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.save(employeeDTO);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/role")
    public ResponseEntity<List<Employee>> getEmployeesByRole(@RequestParam String role) {
        return new ResponseEntity<>(employeeService.findByRole(role), HttpStatus.OK);
    }

    @GetMapping("/department")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@RequestParam String department) {
        return new ResponseEntity<>(employeeService.findByDepartment(department), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hospitals/{hospitalId}")
    public ResponseEntity<List<Employee>> getEmployeesByHospital(@PathVariable Long hospitalId) {
        return new ResponseEntity<>(employeeService.findByHospital(hospitalId), HttpStatus.OK);
    }

    @PostMapping("/{employeeId}/hospitals/{hospitalId}")
    public ResponseEntity<Employee> addEmployeeToHospital(@PathVariable Long employeeId, @PathVariable Long hospitalId) {
        return new ResponseEntity<>(employeeService.addEmployeeToHospital(employeeId, hospitalId), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}/hospitals")
    public ResponseEntity<Employee> removeEmployeeFromHospital(@PathVariable Long employeeId) {
        return new ResponseEntity<>(employeeService.removeEmployeeFromHospital(employeeId), HttpStatus.OK);
    }
}
