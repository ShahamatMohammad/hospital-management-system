package shahamat.hospitalmanagementsystem.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import shahamat.hospitalmanagementsystem.doctor.Doctor;
import shahamat.hospitalmanagementsystem.patient.Patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorAndAppointmentDate(Doctor doctor, LocalDate appointmentDate);
    List<Appointment> findByDoctorAndAppointmentDateAndTimeSlot(Doctor doctor, LocalDate appointmentDate, LocalTime timeSlot);
    List<Appointment> findByPatient(Patient patient);
}
