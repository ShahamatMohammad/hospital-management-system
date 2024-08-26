package shahamat.hospitalmanagementsystem.hospital;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shahamat.hospitalmanagementsystem.secretary.Secretary;

import java.util.List;

@RestController
@RequestMapping("/hospitals")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping
    public ResponseEntity<Hospital> createHospital(@RequestBody HospitalDTO hospitalDTO) {
        Hospital hospital = hospitalService.save(hospitalDTO);
        return new ResponseEntity<>(hospital, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable Long id) {
        return new ResponseEntity<>(hospitalService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        return new ResponseEntity<>(hospitalService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Hospital>> getHospitalsByName(@RequestParam String name) {
        return new ResponseEntity<>(hospitalService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/location")
    public ResponseEntity<List<Hospital>> getHospitalsByLocation(@RequestParam String location) {
        return new ResponseEntity<>(hospitalService.findByLocation(location), HttpStatus.OK);
    }

    @GetMapping("/capacity")
    public ResponseEntity<List<Hospital>> getHospitalsByCapacity(@RequestParam int capacity) {
        return new ResponseEntity<>(hospitalService.findByCapacity(capacity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable Long id) {
        hospitalService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{hospitalId}/patients/{patientId}")
    public ResponseEntity<String> addPatientToHospital(@PathVariable Long hospitalId, @PathVariable Long patientId) {
        String resultMessage = hospitalService.addPatientToHospital(hospitalId, patientId);
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }

    @PostMapping("/{hospitalId}/employees/{employeeId}")
    public ResponseEntity<String> addEmployeeToHospital(@PathVariable Long hospitalId, @PathVariable Long employeeId) {
        String message = hospitalService.addEmployeeToHospital(hospitalId, employeeId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/{hospitalId}/secretaries/{secretaryId}")
    public ResponseEntity<String> addSecretaryToHospital(@PathVariable Long hospitalId, @PathVariable Long secretaryId) {
        return new ResponseEntity<>(hospitalService.addSecretaryToHospital(hospitalId, secretaryId), HttpStatus.OK);
    }

    @DeleteMapping("/patients/{patientId}")
    public ResponseEntity<String> removePatientFromHospital(@PathVariable Long patientId) {
        String resultMessage = hospitalService.removePatientFromHospital(patientId);
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }


    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> removeEmployeeFromHospital(@PathVariable Long employeeId) {
        String message = hospitalService.removeEmployeeFromHospital(employeeId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/secretaries/{secretaryId}")
    public ResponseEntity<String> removeSecretaryFromHospital(@PathVariable Long secretaryId) {
        return new ResponseEntity<>(hospitalService.removeSecretaryFromHospital(secretaryId), HttpStatus.OK);
    }
}
