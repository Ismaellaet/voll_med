package med.voll.api.domain.address;

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

    public void update(UpdateAddressRequest data) {
        if (data != null) {
            setStreet(data.street());
            setNeighborhood(data.neighborhood());
            setZipcode(data.zipcode());
            setCity(data.city());
            setState(data.state());
            setNumber(data.number());
            setComplement(data.complement());
        }
    }

    public void setStreet(String street) {
        if (street != null) {
            this.street = street;
        }
    }

    public void setNeighborhood(String neighborhood) {
        if (neighborhood != null) {
            this.neighborhood = neighborhood;
        }
    }

    public void setZipcode(String zipcode) {
        if (zipcode != null) {
            this.zipcode = zipcode;
        }
    }

    public void setCity(String city) {
        if (city != null) {
            this.city = city;
        }
    }

    public void setState(String state) {
        if (state != null) {
            this.state = state;
        }
    }

    public void setNumber(String number) {
        if (number != null) {
            this.number = number;
        }
    }

    public void setComplement(String complement) {
        if (complement != null) {
            this.complement = complement;
        }
    }
}
