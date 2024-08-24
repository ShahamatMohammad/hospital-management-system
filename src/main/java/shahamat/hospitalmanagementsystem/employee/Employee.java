package shahamat.hospitalmanagementsystem.employee;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import shahamat.hospitalmanagementsystem.hospital.Hospital;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String position;
    private String phone;
    private String email;
    private String role;
    private String department;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
