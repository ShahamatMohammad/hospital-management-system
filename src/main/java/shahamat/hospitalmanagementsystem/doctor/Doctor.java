package shahamat.hospitalmanagementsystem.doctor;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import shahamat.hospitalmanagementsystem.hospital.Hospital;
import shahamat.hospitalmanagementsystem.patient.Patient;
import shahamat.hospitalmanagementsystem.secretary.Secretary;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
//@ToString(exclude = "secretary")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String specialty;
    private String phone;
    private String email;

    @ManyToMany(mappedBy = "doctors")
    private List<Patient> patients;

    @ManyToMany
    @JoinTable(
            name = "doctor_hospital",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "hospital_id")
    )
    private List<Hospital> hospitals;

    @OneToOne(mappedBy = "doctor", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Secretary secretary;

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", secretary=" + secretary +
                '}';
    }
}
