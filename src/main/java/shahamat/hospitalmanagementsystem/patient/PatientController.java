package shahamat.hospitalmanagementsystem.patient;

import lombok.RequiredArgsConstructor;
import shahamat.hospitalmanagementsystem.patient.PatientDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody PatientDTO patientDTO) {
        return new ResponseEntity<>(patientService.save(patientDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return new ResponseEntity<>(patientService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Patient>> getPatientsByName(@RequestParam String name) {
        return new ResponseEntity<>(patientService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/age")
    public ResponseEntity<List<Patient>> getPatientsByAge(@RequestParam int age) {
        return new ResponseEntity<>(patientService.findByAge(age), HttpStatus.OK);
    }

    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<List<Patient>> getPatientsByHospitalId(@PathVariable Long hospitalId) {
        return new ResponseEntity<>(patientService.findByHospitalId(hospitalId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{patientId}/doctors/{doctorId}")
    public ResponseEntity<Patient> addDoctorToPatient(@PathVariable Long patientId, @PathVariable Long doctorId) {
        return new ResponseEntity<>(patientService.addDoctorToPatient(patientId, doctorId), HttpStatus.OK);
    }

    @PostMapping("/{patientId}/hospitals/{hospitalId}")
    public ResponseEntity<Patient> addPatientToHospital(@PathVariable Long patientId, @PathVariable Long hospitalId) {
        return new ResponseEntity<>(patientService.addPatientToHospital(patientId, hospitalId), HttpStatus.OK);
    }

    @PostMapping("/{patientId}/diseases/{diseaseId}")
    public ResponseEntity<Patient> addDiseaseToPatient(@PathVariable Long patientId, @PathVariable Long diseaseId) {
        return new ResponseEntity<>(patientService.addDiseaseToPatient(patientId, diseaseId), HttpStatus.OK);
    }

    @DeleteMapping("/{patientId}/doctors/{doctorId}")
    public ResponseEntity<Patient> removeDoctorFromPatient(@PathVariable Long patientId, @PathVariable Long doctorId) {
        return new ResponseEntity<>(patientService.removeDoctorFromPatient(patientId, doctorId), HttpStatus.OK);
    }

    @DeleteMapping("/{patientId}/diseases/{diseaseId}")
    public ResponseEntity<Patient> removeDiseaseFromPatient(@PathVariable Long patientId, @PathVariable Long diseaseId) {
        return new ResponseEntity<>(patientService.removeDiseaseFromPatient(patientId, diseaseId), HttpStatus.OK);
    }
}
