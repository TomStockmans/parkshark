package com.team4.domain.allocation;

import com.team4.domain.member.Member;
import com.team4.domain.parkinglot.ParkingLot;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ALLOCATION")
public class Allocation {

    @Id
    @SequenceGenerator(name = "allocation_seq", sequenceName = "ALLOCATION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allocation_seq")
    private long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PARKINGLOT_ID")
    private ParkingLot parkingLot;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @Column(name = "STOP_TIME")
    private LocalDateTime stopTime;

    public Allocation(Member member, ParkingLot parkingLot) {
        this.member = member;
        this.parkingLot = parkingLot;
        this.startTime = LocalDateTime.now();
    }

    public Allocation() {
    }

    public long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Allocation that = (Allocation) o;
        return id == that.id &&
                Objects.equals(member, that.member) &&
                Objects.equals(parkingLot, that.parkingLot) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(stopTime, that.stopTime);
    }
}
