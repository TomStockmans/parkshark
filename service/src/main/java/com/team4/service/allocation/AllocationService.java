package com.team4.service.allocation;

import com.team4.domain.allocation.Allocation;
import com.team4.domain.allocation.AllocationException;
import com.team4.domain.allocation.AllocationRepository;
import com.team4.domain.member.Member;
import com.team4.domain.parkinglot.ParkingLot;
import com.team4.service.member.MemberService;
import com.team4.service.parkinglot.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
        if (!member.getLicensePlate().getPlateNumber().equals(licensePlateNumber)) {
            //TODO: Add check: plates can be different when membership level is gold
            throw new AllocationException("Start allocation failed: given license plate number does not match member");
        }
        ParkingLot parkingLot = parkingLotService.getById(parkingLotId);
        if (!isParkingLotAvailable(parkingLot)){
            throw new AllocationException("Start allocation failed: no available space");
        }
        Allocation allocation = new Allocation(member, parkingLot);
        return allocationRepository.save(allocation);
    }

    private boolean isParkingLotAvailable(ParkingLot parkingLot){
        int activeAllocations = allocationRepository.findAll(
                Specification.where(AllocationRepository.hasParkingLotId(parkingLot.getId()))
                .and(AllocationRepository.isActive())).size();
        if (activeAllocations < parkingLot.getCapacity()){
            return true;
        }
        return false;
    }

}
