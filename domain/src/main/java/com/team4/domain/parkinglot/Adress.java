package com.team4.domain.parkinglot;

import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class Adress {
    //The address consists of a street name, street number, and a postal code
    private String street;
    private String streetNr;
    private String postalCode;


    public Adress(String street, String streetNr, String postalCode) {
        this.street = street;
        this.streetNr = streetNr;
        setPostalCode(postalCode);
    }
    public Adress() {
    }

    public void setPostalCode(String postalCode) {
        if (postalCode == null || !Pattern.compile("^[0-9]{4}, .+", Pattern.CASE_INSENSITIVE).matcher(postalCode).find()) {
            throw new DomainException("invalid postal code");
        }
        this.postalCode = postalCode;
    }
}
