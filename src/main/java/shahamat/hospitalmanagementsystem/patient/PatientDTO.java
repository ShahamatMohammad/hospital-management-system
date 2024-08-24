package shahamat.hospitalmanagementsystem.patient;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private int age;
}
