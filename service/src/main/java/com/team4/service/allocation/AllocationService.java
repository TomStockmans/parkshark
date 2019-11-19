package com.team4.service.allocation;

import com.team4.domain.allocation.*;
import com.team4.domain.member.Member;
import com.team4.domain.member.MembershipLevel;
import com.team4.domain.parkinglot.ParkingLot;
import com.team4.service.member.MemberService;
import com.team4.service.parkinglot.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllocationService {
    private final AllocationRepository allocationRepository;
    private final MemberService memberService;
    private final ParkingLotService parkingLotService;

    @Autowired
    public AllocationService(AllocationRepository allocationRepository, MemberService memberService, ParkingLotService parkingLotService) {
        this.allocationRepository = allocationRepository;
        this.memberService = memberService;
        this.parkingLotService = parkingLotService;
    }

    public Allocation startAllocation(long memberId, String licensePlateNumber, long parkingLotId) {
        Member member = memberService.getMemberById(memberId);
        if (memberHasActiveParking(memberId)){
            throw new AllocationException("Start allocation failed: member with id " + memberId + " already has an active allocation");
        }
        if (!isLicensePlateValid(licensePlateNumber, member)) {
            throw new AllocationException("Start allocation failed: given license plate number does not match member");
        }
        ParkingLot parkingLot = parkingLotService.getById(parkingLotId);
        if (!isParkingLotAvailable(parkingLot)){
            throw new AllocationException("Start allocation failed: no available space");
        }
        Allocation allocation = new Allocation(member, parkingLot);
        return allocationRepository.save(allocation);
    }

    private boolean isLicensePlateValid(String licensePlateNumber, Member member) {
        return member.getMembershipLevel() == MembershipLevel.GOLD || member.getLicensePlate().getPlateNumber().equals(licensePlateNumber);
    }

    private boolean memberHasActiveParking(long memberId) {
        int activeAllocations = allocationRepository.findAll(
                Specification.where(AllocationRepository.hasMemberId(memberId))
                        .and(AllocationRepository.isActive()))
                .size();
        return activeAllocations != 0;
    }

    private boolean isParkingLotAvailable(ParkingLot parkingLot){
        int activeAllocations = allocationRepository.findAll(
                Specification.where(AllocationRepository.hasParkingLotId(parkingLot.getId()))
                .and(AllocationRepository.isActive()))
                .size();
        return activeAllocations < parkingLot.getCapacity();
    }


    public List<Allocation> getAllAllocations(int start, int limit, AllocationFilter allocationFilter, OrderFilter orderFilter) {

        Pageable pageable = PageRequest.of(start, limit);

        if(orderFilter.equals(OrderFilter.DESC)){
            pageable = PageRequest.of(start,limit, Sort.by("startTime").descending());
        }

        switch (allocationFilter) {
            case ACTIVE:
                return allocationRepository.findByStopTimeNull(pageable);
            case STOPPED:
                return allocationRepository.findByStopTimeNotNull(pageable);
            default:
                return allocationRepository.findBy(pageable);

        }
    }

    public Allocation stopAllocation(long allocationId){
        var allocation = allocationRepository.findById(allocationId);
        if (allocation.isEmpty()){
            throw new AllocationException("No allocation found with id: " + allocationId);
        }
        return allocation.get().stopAllocation();
    }

}
