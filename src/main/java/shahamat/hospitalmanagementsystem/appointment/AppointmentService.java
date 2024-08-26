package shahamat.hospitalmanagementsystem.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shahamat.hospitalmanagementsystem.doctor.Doctor;
import shahamat.hospitalmanagementsystem.doctor.DoctorRepository;
import shahamat.hospitalmanagementsystem.exception.CustomException;
import shahamat.hospitalmanagementsystem.patient.Patient;
import shahamat.hospitalmanagementsystem.patient.PatientRepository;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public Appointment createAppointment(Long doctorId, Long patientId, LocalDate date, LocalTime timeSlot) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new CustomException("Doctor not found", HttpStatus.NOT_FOUND, 404));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found", HttpStatus.NOT_FOUND, 404));

        // Check if the time slot is already taken or violates the 15-minute interval rule
        Optional<Appointment> conflictingAppointment = appointmentRepository
                .findByDoctorAndAppointmentDate(doctor, date).stream()
                .filter(appointment -> isConflict(appointment.getTimeSlot(), timeSlot))
                .findFirst();

        if (conflictingAppointment.isPresent()) {
            throw new CustomException("Time slot is already taken or is too close to another appointment", HttpStatus.CONFLICT, 409);
        }

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(date);
        appointment.setTimeSlot(timeSlot);
        appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED);

        return appointmentRepository.save(appointment);
    }

    private boolean isConflict(LocalTime existingTimeSlot, LocalTime newTimeSlot) {
        // Calculate the difference in minutes between the existing time slot and the new time slot
        long minutesDifference = existingTimeSlot.until(newTimeSlot, ChronoUnit.MINUTES);
        return Math.abs(minutesDifference) < 15;
    }

    public List<Appointment> getAppointmentsByDateAndDoctor(Long doctorId, LocalDate date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new CustomException("Doctor not found", HttpStatus.NOT_FOUND, 404));
        return appointmentRepository.findByDoctorAndAppointmentDate(doctor, date);
    }

    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new CustomException("Appointment not found", HttpStatus.NOT_FOUND, 404));
        appointment.setStatus(Appointment.AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new CustomException("Patient not found", HttpStatus.NOT_FOUND, 404));
        return appointmentRepository.findByPatient(patient);
    }
}
