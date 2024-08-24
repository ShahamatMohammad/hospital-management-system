package shahamat.hospitalmanagementsystem.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String position;
    private String phone;
    private String email;
    private String role;
    private String department;
    private Long hospitalId;
}
