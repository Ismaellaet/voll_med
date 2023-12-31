package med.voll.api.domain.patient;

public record ListPatientResponse(Long id, String name, String email, String cpf) {
    public ListPatientResponse(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
