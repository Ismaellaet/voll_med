package med.voll.api.domain.doctor;

import med.voll.api.domain.address.UpdateAddressRequest;

public record UpdateDoctorRequest (
    String name,
    String phone,
    UpdateAddressRequest address
) {
}
