package shahamat.hospitalmanagementsystem.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor doctor = doctorService.save(doctorDTO);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return new ResponseEntity<>(doctorService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/specialty")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialty(@RequestParam String specialty) {
        return new ResponseEntity<>(doctorService.findBySpecialty(specialty), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{doctorId}/hospitals/{hospitalId}")
    public ResponseEntity<Doctor> addDoctorToHospital(@PathVariable Long doctorId, @PathVariable Long hospitalId) {
        return new ResponseEntity<>(doctorService.addDoctorToHospital(doctorId, hospitalId), HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}/hospitals/{hospitalId}")
    public ResponseEntity<Doctor> removeDoctorFromHospital(@PathVariable Long doctorId, @PathVariable Long hospitalId) {
        return new ResponseEntity<>(doctorService.removeDoctorFromHospital(doctorId, hospitalId), HttpStatus.OK);
    }

    @GetMapping("/hospitals/{hospitalId}")
    public ResponseEntity<List<Doctor>> getDoctorsByHospital(@PathVariable Long hospitalId) {
        return new ResponseEntity<>(doctorService.findDoctorsByHospital(hospitalId), HttpStatus.OK);
    }

    @PostMapping("/{doctorId}/patients/{patientId}")
    public ResponseEntity<Doctor> addPatientToDoctor(@PathVariable Long doctorId, @PathVariable Long patientId) {
        return new ResponseEntity<>(doctorService.addPatientToDoctor(doctorId, patientId), HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}/patients/{patientId}")
    public ResponseEntity<Doctor> removePatientFromDoctor(@PathVariable Long doctorId, @PathVariable Long patientId) {
        return new ResponseEntity<>(doctorService.removePatientFromDoctor(doctorId, patientId), HttpStatus.OK);
    }
}
