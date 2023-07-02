package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.patient.CreatePatientRequest;
import med.voll.api.patient.ListPatientResponse;
import med.voll.api.patient.Patient;
import med.voll.api.patient.PatientRepository;
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
        return repository.findAll(pagination).map(ListPatientResponse::new);
    }
}
