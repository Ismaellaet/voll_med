package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String state;
    private String number;
    private String complement;

    public Address(CreateAddressRequest data) {
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.zipcode = data.zipcode();
        this.city = data.city();
        this.state = data.state();
        this.number = data.number();
        this.complement = data.complement();
    }
}
