package com.team4.domain.parkinglot;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.regex.Pattern;

@Entity
@Table(name = "CONTACTPERSON")
public class ContactPerson {


    @Id
    private long id;
    private String email;
    private String mobilePhoneNumber;
    private String telephoneNumber;

    @Embedded
    private Adress adress;


    public ContactPerson(String email, String mobilePhoneNumber, String telephoneNumber, Adress adress) {
        setEmail(email);
        setMobileAndTelephoneNumber(mobilePhoneNumber, telephoneNumber);
        this.adress = adress;
    }

    public ContactPerson() {
    }
    private void setEmail(String email) {
        if (!Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE).matcher(email).find()) {
            throw new DomainException("email is not valid");
        }
        this.email = email;
    }

    private void setMobileAndTelephoneNumber(String mobilphoneNumber, String telephoneNumber) {
        if ((mobilphoneNumber == null || mobilphoneNumber.trim().isEmpty())
                && (telephoneNumber == null || telephoneNumber.trim().isEmpty())) {
            throw new DomainException("you have to provide a mobile or telephone number");
        }
        this.mobilePhoneNumber = mobilphoneNumber;
        this.telephoneNumber = telephoneNumber;
    }
}
