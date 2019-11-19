package com.team4.domain.allocation;

import com.team4.domain.member.Member;
import com.team4.domain.parkinglot.ParkingLot;

import javax.persistence.*;
import java.time.Clock;
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

    public Allocation(Member member, ParkingLot parkingLot, Clock clock) {
        this.member = member;
        this.parkingLot = parkingLot;
        this.startTime = LocalDateTime.now(clock);
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
                member.equals(that.member) &&
                parkingLot.equals(that.parkingLot) &&
                startTime.equals(that.startTime) &&
                stopTime.equals(that.stopTime);
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "id=" + id +
                ", member=" + member +
                ", parkingLot=" + parkingLot +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                '}';
    }

    public Allocation stopAllocation(){
        this.stopTime = LocalDateTime.now();
        return this;

    }
}
