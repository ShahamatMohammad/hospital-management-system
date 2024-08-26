package shahamat.hospitalmanagementsystem.secretary;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
public class SecretaryDTO {

    @JsonIgnore
    private Long id; // Exclude this field from JSON serialization

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private int yearsOfExperience;

    @JsonIgnore
    private Long doctorId;  // Storing only the ID of the doctor

    @JsonIgnore
    private Long hospitalId;  // Storing only the ID of the hospital
}
