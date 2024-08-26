package shahamat.hospitalmanagementsystem.hospital;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
