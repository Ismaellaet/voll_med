package med.voll.api.patient;

public record ListPatientResponse(String name, String email, String cpf) {
    public ListPatientResponse(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
