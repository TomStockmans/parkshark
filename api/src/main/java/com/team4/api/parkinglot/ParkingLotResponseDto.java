package com.team4.api.parkinglot;

public class ParkingLotResponseDto {

    public long id;
    public String name;
    public int capacity;
    public String contact_email;
    public String telephone;
    public String mobilePhone;

    public ParkingLotResponseDto(long id, String name, int capacity, String contact_email, String telephone, String mobilePhone) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.contact_email = contact_email;
        this.telephone = telephone;
        this.mobilePhone = mobilePhone;
    }
}
