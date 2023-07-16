package med.voll.api.doctor;

import med.voll.api.address.UpdateAddressRequest;

public record UpdateDoctorRequest (
    String name,
    String phone,
    UpdateAddressRequest address
) {
}
