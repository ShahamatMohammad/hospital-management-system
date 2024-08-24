package shahamat.hospitalmanagementsystem.hospital;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Hospital> addPatientToHospital(@PathVariable Long hospitalId, @PathVariable Long patientId) {
        return new ResponseEntity<>(hospitalService.addPatientToHospital(hospitalId, patientId), HttpStatus.OK);
    }

    @PostMapping("/{hospitalId}/employees/{employeeId}")
    public ResponseEntity<Hospital> addEmployeeToHospital(@PathVariable Long hospitalId, @PathVariable Long employeeId) {
        return new ResponseEntity<>(hospitalService.addEmployeeToHospital(hospitalId, employeeId), HttpStatus.OK);
    }

    @PostMapping("/{hospitalId}/secretaries/{secretaryId}")
    public ResponseEntity<Hospital> addSecretaryToHospital(@PathVariable Long hospitalId, @PathVariable Long secretaryId) {
        return new ResponseEntity<>(hospitalService.addSecretaryToHospital(hospitalId, secretaryId), HttpStatus.OK);
    }

    @DeleteMapping("/{hospitalId}/patients/{patientId}")
    public ResponseEntity<Hospital> removePatientFromHospital(@PathVariable Long hospitalId, @PathVariable Long patientId) {
        return new ResponseEntity<>(hospitalService.removePatientFromHospital(hospitalId, patientId), HttpStatus.OK);
    }

    @DeleteMapping("/{hospitalId}/employees/{employeeId}")
    public ResponseEntity<Hospital> removeEmployeeFromHospital(@PathVariable Long hospitalId, @PathVariable Long employeeId) {
        return new ResponseEntity<>(hospitalService.removeEmployeeFromHospital(hospitalId, employeeId), HttpStatus.OK);
    }

    @DeleteMapping("/{hospitalId}/secretaries/{secretaryId}")
    public ResponseEntity<Hospital> removeSecretaryFromHospital(@PathVariable Long hospitalId, @PathVariable Long secretaryId) {
        return new ResponseEntity<>(hospitalService.removeSecretaryFromHospital(hospitalId, secretaryId), HttpStatus.OK);
    }
}
