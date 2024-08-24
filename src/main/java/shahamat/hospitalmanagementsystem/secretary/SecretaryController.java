package shahamat.hospitalmanagementsystem.secretary;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secretaries")
@RequiredArgsConstructor
public class SecretaryController {

    private final SecretaryService secretaryService;

    @PostMapping
    public ResponseEntity<SecretaryDTO> createSecretary(@RequestBody SecretaryDTO secretaryDTO) {
        return new ResponseEntity<>(secretaryService.save(secretaryDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecretaryDTO> getSecretaryById(@PathVariable Long id) {
        return new ResponseEntity<>(secretaryService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SecretaryDTO>> getAllSecretaries() {
        return new ResponseEntity<>(secretaryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<SecretaryDTO>> getSecretariesByName(@RequestParam String name) {
        return new ResponseEntity<>(secretaryService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/experience")
    public ResponseEntity<List<SecretaryDTO>> getSecretariesByExperience(@RequestParam int years) {
        return new ResponseEntity<>(secretaryService.findByYearsOfExperience(years), HttpStatus.OK);
    }

    @GetMapping("/hospital/{hospitalId}")
    public ResponseEntity<List<SecretaryDTO>> getSecretariesByHospitalId(@PathVariable Long hospitalId) {
        return new ResponseEntity<>(secretaryService.findByHospitalId(hospitalId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecretary(@PathVariable Long id) {
        secretaryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{secretaryId}/hospitals/{hospitalId}")
    public ResponseEntity<SecretaryDTO> addSecretaryToHospital(@PathVariable Long secretaryId, @PathVariable Long hospitalId) {
        return new ResponseEntity<>(secretaryService.addSecretaryToHospital(secretaryId, hospitalId), HttpStatus.OK);
    }

    @PostMapping("/{secretaryId}/doctors/{doctorId}")
    public ResponseEntity<SecretaryDTO> addSecretaryToDoctor(@PathVariable Long secretaryId, @PathVariable Long doctorId) {
        return new ResponseEntity<>(secretaryService.addSecretaryToDoctor(secretaryId, doctorId), HttpStatus.OK);
    }

    @DeleteMapping("/{secretaryId}/hospitals/{hospitalId}")
    public ResponseEntity<SecretaryDTO> removeSecretaryFromHospital(@PathVariable Long secretaryId, @PathVariable Long hospitalId) {
        return new ResponseEntity<>(secretaryService.removeSecretaryFromHospital(secretaryId, hospitalId), HttpStatus.OK);
    }

    @DeleteMapping("/{secretaryId}/doctors/{doctorId}")
    public ResponseEntity<SecretaryDTO> removeDoctorFromSecretary(@PathVariable Long secretaryId, @PathVariable Long doctorId) {
        return new ResponseEntity<>(secretaryService.removeDoctorFromSecretary(secretaryId, doctorId), HttpStatus.OK);
    }
}
