package shahamat.hospitalmanagementsystem.disease;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import shahamat.hospitalmanagementsystem.patient.Patient;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Disease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String severity;

    @ManyToMany(mappedBy = "diseases")
    private List<Patient> patients;
}
