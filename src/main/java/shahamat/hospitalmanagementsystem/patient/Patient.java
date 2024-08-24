package shahamat.hospitalmanagementsystem.patient;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import shahamat.hospitalmanagementsystem.disease.Disease;
import shahamat.hospitalmanagementsystem.doctor.Doctor;
import shahamat.hospitalmanagementsystem.hospital.Hospital;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private int age;

    @ManyToMany
    @JoinTable(
            name = "doctor_patient",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors;

    @ManyToMany
    @JoinTable(
            name = "patient_disease",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private List<Disease> diseases;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
