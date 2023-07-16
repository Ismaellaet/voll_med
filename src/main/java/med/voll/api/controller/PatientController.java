package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createPatient(@RequestBody @Valid CreatePatientRequest request, UriComponentsBuilder uriBuilder) {
        Patient patient = new Patient(request);
        repository.save(patient);
        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailsResponse(patient));
    }

    @GetMapping
    public Page<ListPatientResponse> listPatient(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(ListPatientResponse::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updatePatient(@PathVariable Long id, @RequestBody @Valid UpdatePatientRequest request) {
        Patient patient = repository.getReferenceById(id);
        patient.update(request);
        return ResponseEntity.ok(new PatientDetailsResponse(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inactivatePatient(@PathVariable Long id) {
        Patient patient = repository.getReferenceById(id);
        patient.inactivate();
        return ResponseEntity.noContent().build();
    }
}
