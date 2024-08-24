package shahamat.hospitalmanagementsystem.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByRole(String role);
    List<Employee> findByDepartment(String department);
    List<Employee> findByHospital_Id(Long hospitalId);
}
