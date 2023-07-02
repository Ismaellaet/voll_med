package med.voll.api.doctor;

public record ListDoctorResponse(String name, String email, String crm, Specialty specialty) {
    public ListDoctorResponse(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
