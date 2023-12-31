package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;

public record DoctorDetailsResponse(Long id, String name, String email, String phone, String crm, Specialty specialty, Address address) {
    public DoctorDetailsResponse(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress());
    }
}
