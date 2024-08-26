package shahamat.hospitalmanagementsystem.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import shahamat.hospitalmanagementsystem.hospital.Hospital;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @JsonIgnore
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_id")
    @JsonIgnore
    private Hospital hospital;

    @Override
    public String toString() {
        return "Employee{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", department='" + department + '\'' +
                ", hospital=" + (hospital != null ? hospitalToString(hospital) : "null") + // Custom method to display all hospital details
                '}';
    }

    private String hospitalToString(Hospital hospital) {
        return "Hospital{" +
                ", name='" + hospital.getName() + '\'' +
                ", address='" + hospital.getAddress() + '\'' +
                ", phone='" + hospital.getPhone() + '\'' +
                ", email='" + hospital.getEmail() + '\'' +
                ", location='" + hospital.getLocation() + '\'' +
                ", capacity=" + hospital.getCapacity() +
                '}';
    }
}
