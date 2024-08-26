package shahamat.hospitalmanagementsystem.hospital;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import shahamat.hospitalmanagementsystem.doctor.Doctor;
import shahamat.hospitalmanagementsystem.employee.Employee;
import shahamat.hospitalmanagementsystem.patient.Patient;
import shahamat.hospitalmanagementsystem.secretary.Secretary;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String name;
    private String address;
    private String phone;
    private String email;
    private String location;
    private int capacity;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Patient> patients;

    @ManyToMany(mappedBy = "hospitals")
    @JsonIgnore
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "hospital", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Employee> employees;

    @OneToMany(mappedBy = "hospital")
    @JsonIgnore
    private List<Secretary> secretaries;
}
