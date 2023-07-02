package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.CreateDoctorRequest;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRepository;
import med.voll.api.doctor.ListDoctorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void createDoctor(@RequestBody @Valid CreateDoctorRequest request) {
        repository.save(new Doctor(request));
    }

    @GetMapping
    public Page<ListDoctorResponse> listDoctor(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return repository.findAll(pagination).map(ListDoctorResponse::new);
    }
}
