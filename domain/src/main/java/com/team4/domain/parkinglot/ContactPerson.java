package com.team4.domain.parkinglot;

import javax.persistence.*;
import java.util.regex.Pattern;

@Entity
@Table(name = "CONTACTPERSON")
public class ContactPerson {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTACT_PERSON_SEQ")
    @SequenceGenerator(sequenceName = "contact_person_seq", allocationSize = 1, name = "CONTACT_PERSON_SEQ")
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
            throw new ParkingLotException("email is not valid");
        }
        this.email = email;
    }

    private void setMobileAndTelephoneNumber(String mobilphoneNumber, String telephoneNumber) {
        if ((mobilphoneNumber == null || mobilphoneNumber.trim().isEmpty())
                && (telephoneNumber == null || telephoneNumber.trim().isEmpty())) {
            throw new ParkingLotException("you have to provide a mobile or telephone number");
        }
        this.mobilePhoneNumber = mobilphoneNumber;
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public long getId() {
        return id;
    }

    public Adress getAdress() {
        return adress;
    }
}
