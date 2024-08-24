package shahamat.hospitalmanagementsystem.secretary;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;
import shahamat.hospitalmanagementsystem.doctor.Doctor;
import shahamat.hospitalmanagementsystem.hospital.Hospital;

@Entity
@Data
@NoArgsConstructor
public class Secretary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private int yearsOfExperience;

    public Secretary(String firstName, String lastName, String phone, String email, int yearsOfExperience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.yearsOfExperience = yearsOfExperience;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Override
    public String toString() {
        return "Secretary{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", yearsOfExperience=" + yearsOfExperience +
                ", doctor=" + (doctor != null ? doctor.getFirstName() + " " + doctor.getLastName() : "null") +
                '}';
    }


}
