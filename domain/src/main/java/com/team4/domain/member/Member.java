package com.team4.domain.member;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table
public class Member implements Person {

    @Id
    @Column(name = "ID")
    @SequenceGenerator(name = "m_seq", sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "m_seq")
    private long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME")),
            @AttributeOverride(name = "lastName", column = @Column(name = "LAST_NAME"))
    })
    private Name name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetName", column = @Column(name = "STREET")),
            @AttributeOverride(name = "houseNumber", column = @Column(name = "HOUSE_NUMBER")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "ZIP")),
            @AttributeOverride(name = "country", column = @Column(name = "COUNTRY"))
    })
    private Address address;

    @Column(name = "PHONENUMBER")
    private String phoneNumber;

    @Column(name = "EMAIL")
    private String email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "plateNumber", column = @Column(name = "PLATE_NUMBER")),
            @AttributeOverride(name = "issuingCountry", column = @Column(name = "ISSUING_COUNTRY"))
    })
    private LicensePlate licensePlate;

    @Column(name = "REGISTRATION_DATE")
    private LocalDate registrationDate;

    @Column(name = "MEMBERSHIP_LEVEL")
    private MembershipLevel membershipLevel;

    public Member() {}

    public Member(Name name, Address address, String phoneNumber, String email, LicensePlate licensePlate) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.licensePlate = licensePlate;
        this.registrationDate = LocalDate.now();
        this.membershipLevel = MembershipLevel.BRONZE;
    }

    public Member(Name name, Address address, String phoneNumber, String email, LicensePlate licensePlate, MembershipLevel membershipLevel) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.licensePlate = licensePlate;
        this.registrationDate = LocalDate.now();
        this.membershipLevel = membershipLevel;
    }

    public long getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", licensePlate=" + licensePlate +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
