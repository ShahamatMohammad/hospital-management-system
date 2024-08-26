package shahamat.hospitalmanagementsystem.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/reserve")
    public ResponseEntity<Appointment> reserveAppointment(
            @RequestParam Long doctorId,
            @RequestParam Long patientId,
            @RequestParam String date,
            @RequestParam String time
    ) {
        LocalDate appointmentDate = LocalDate.parse(date);
        LocalTime timeSlot = LocalTime.parse(time);
        Appointment appointment = appointmentService.createAppointment(doctorId, patientId, appointmentDate, timeSlot);
        return new ResponseEntity<>(appointment, HttpStatus.CREATED);
    }

    @GetMapping("/doctor/{doctorId}/date/{date}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDateAndDoctor(
            @PathVariable Long doctorId,
            @PathVariable String date
    ) {
        LocalDate appointmentDate = LocalDate.parse(date);
        List<Appointment> appointments = appointmentService.getAppointmentsByDateAndDoctor(doctorId, appointmentDate);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable Long patientId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByPatient(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }
}
