package com.team4.domain.parkinglot;

import com.team4.domain.division.Division;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PARKINGLOT")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARKINGLOT_SEQ")
    @SequenceGenerator(sequenceName = "parkinglot_seq", allocationSize = 1, name = "PARKINGLOT_SEQ")
    private long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private ParkingCategory parkingCategory;
    private int capacity;

    @ManyToOne(cascade = CascadeType.ALL)
    private ContactPerson contactPerson;

    @ManyToOne()
    @NotNull
    @JoinColumn(name = "DIVISION_ID")
    private Division division;

    public ParkingLot(String name, ParkingCategory parkingCategory, int capacity, ContactPerson contactPerson, Division division) {
        this.name = name;
        this.parkingCategory = parkingCategory;
        this.capacity = capacity;
        this.contactPerson = contactPerson;
        this.division = division;
    }

    public ParkingLot() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public ParkingCategory getParkingCategory() {

        return parkingCategory;
    }

    public Division getDivision() {
        return division;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parkingCategory=" + parkingCategory +
                ", capacity=" + capacity +
                ", contactPerson=" + contactPerson +
                '}';
    }

}
