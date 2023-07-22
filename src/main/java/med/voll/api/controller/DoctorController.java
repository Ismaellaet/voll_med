package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createDoctor(@RequestBody @Valid CreateDoctorRequest request, UriComponentsBuilder uriBuilder) {
        Doctor doctor = new Doctor(request);
        repository.save(doctor);
        URI uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetailsResponse(doctor));
    }

    @GetMapping
    public Page<ListDoctorResponse> listDoctor(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(ListDoctorResponse::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDoctor(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailsResponse(doctor));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateDoctor(@PathVariable Long id, @RequestBody @Valid UpdateDoctorRequest request) {
        var doctor = repository.getReferenceById(id);
        doctor.update(request);
        return ResponseEntity.ok(new DoctorDetailsResponse(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity inactivateDoctor(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.inactivate();
        return ResponseEntity.noContent().build();
    }
}
