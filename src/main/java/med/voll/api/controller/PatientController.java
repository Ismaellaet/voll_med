package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void createPatient(@RequestBody @Valid CreatePatientRequest request) {
        repository.save(new Patient(request));
    }

    @GetMapping
    public Page<ListPatientResponse> listPatient(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(ListPatientResponse::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public void updatePatient(@PathVariable Long id, @RequestBody @Valid UpdatePatientRequest request) {
        var patient = repository.getReferenceById(id);
        patient.update(request);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void inactivatePatient(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.inactivate();
    }
}
