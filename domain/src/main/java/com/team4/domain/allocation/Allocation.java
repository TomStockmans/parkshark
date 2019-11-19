package com.team4.domain.allocation;

import com.team4.domain.member.Member;
import com.team4.domain.parkinglot.ParkingLot;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    public Allocation stopAllocation(){
        if (stopTime != null){
            throw new AllocationException("Stop allocation failed: allocation has already been stopped");
        }
        this.stopTime = LocalDateTime.now();
        return this;
    }
}
