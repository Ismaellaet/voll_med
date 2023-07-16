package med.voll.api.domain.doctor;

public record ListDoctorResponse(Long id, String name, String email, String crm, Specialty specialty) {
    public ListDoctorResponse(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
