package shahamat.hospitalmanagementsystem.disease;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diseases")
@RequiredArgsConstructor
public class DiseaseController {

    private final DiseaseService diseaseService;

    @PostMapping
    public ResponseEntity<Disease> createDisease(@RequestBody DiseaseDTO diseaseDTO) {
        Disease disease = diseaseService.save(diseaseDTO);
        return new ResponseEntity<>(disease, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disease> getDiseaseById(@PathVariable Long id) {
        return new ResponseEntity<>(diseaseService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Disease>> getAllDiseases() {
        return new ResponseEntity<>(diseaseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Disease>> getDiseasesByName(@RequestParam String name) {
        return new ResponseEntity<>(diseaseService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/severity")
    public ResponseEntity<List<Disease>> getDiseasesBySeverity(@RequestParam String severity) {
        return new ResponseEntity<>(diseaseService.findBySeverity(severity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisease(@PathVariable Long id) {
        diseaseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<List<Disease>> getDiseasesByPatient(@PathVariable Long patientId) {
        return new ResponseEntity<>(diseaseService.findByPatient(patientId), HttpStatus.OK);
    }

    @PostMapping("/{diseaseId}/patients/{patientId}")
    public ResponseEntity<Disease> addDiseaseToPatient(@PathVariable Long diseaseId, @PathVariable Long patientId) {
        return new ResponseEntity<>(diseaseService.addDiseaseToPatient(diseaseId, patientId), HttpStatus.OK);
    }

    @DeleteMapping("/{diseaseId}/patients/{patientId}")
    public ResponseEntity<Disease> removeDiseaseFromPatient(@PathVariable Long diseaseId, @PathVariable Long patientId) {
        return new ResponseEntity<>(diseaseService.removeDiseaseFromPatient(diseaseId, patientId), HttpStatus.OK);
    }
}
