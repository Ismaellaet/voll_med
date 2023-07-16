package med.voll.api.domain.patient;

import med.voll.api.domain.address.UpdateAddressRequest;

public record UpdatePatientRequest (
        String name,
        String phone,
        UpdateAddressRequest address
) {
}
