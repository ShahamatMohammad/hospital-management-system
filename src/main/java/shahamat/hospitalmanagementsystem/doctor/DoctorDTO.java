package shahamat.hospitalmanagementsystem.doctor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DoctorDTO {
    private String firstName;
    private String lastName;
    private String specialty;
    private String phone;
    private String email;
    private List<Long> hospitalIds;
    private List<Long> patientIds;
    private Long secretaryId;
}
