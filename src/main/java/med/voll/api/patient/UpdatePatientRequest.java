package med.voll.api.patient;

import med.voll.api.address.UpdateAddressRequest;

public record UpdatePatientRequest (
        String name,
        String phone,
        UpdateAddressRequest address
) {
}
