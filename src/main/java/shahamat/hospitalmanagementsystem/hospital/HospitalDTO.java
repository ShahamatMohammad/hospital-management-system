package shahamat.hospitalmanagementsystem.hospital;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HospitalDTO {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String location;
    private int capacity;
}
