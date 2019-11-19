package com.team4.service.allocation;

import com.team4.domain.allocation.*;
import com.team4.domain.division.Director;
import com.team4.domain.division.Division;
import com.team4.domain.member.*;
import com.team4.domain.parkinglot.ContactPerson;
import com.team4.domain.parkinglot.ParkingCategory;
import com.team4.domain.parkinglot.ParkingLot;
import com.team4.service.division.DivisionService;
import com.team4.service.member.MemberService;
import com.team4.service.parkinglot.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@Service
public class AllocationService {
    private final AllocationRepository allocationRepository;
    private final MemberService memberService;
    private final ParkingLotService parkingLotService;
    private final DivisionService divisionService;



    @Autowired
    public AllocationService(AllocationRepository allocationRepository, MemberService memberService, ParkingLotService parkingLotService, DivisionService divisionService) {
        this.allocationRepository = allocationRepository;
        this.memberService = memberService;
        this.parkingLotService = parkingLotService;
        this.divisionService = divisionService;

        addTestData();
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
        int activeAllocations = allocationRepository.findAllByStopTimeNullAndMember_Id(memberId).size();
        return activeAllocations != 0;
    }

    private boolean isParkingLotAvailable(ParkingLot parkingLot){
        int activeAllocations = allocationRepository.findAllByStopTimeNullAndParkingLot_Id(parkingLot.getId()).size();
        return activeAllocations < parkingLot.getCapacity();
    }


    public List<Allocation> getAllAllocations(int start, int limit, AllocationFilter allocationFilter, OrderFilter orderFilter) {

        Pageable pageable = PageRequest.of(start, limit, Sort.by("startTime").ascending());

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
        allocation.get().stopAllocation();
        return allocationRepository.save(allocation.get());
    }

    public List<Allocation> getByMemberId(long id){
        Member member = memberService.getMemberById(id);
        return allocationRepository.findAllByMemberIs(member);
    }

    private void addTestData() {
        final Division divisionFinal = divisionService.createDivision( new Division("Awesome division", "Old division name..", new Director("Niels", "Delestinne"), null));
        Member member1 = new Member(new Name("Elvis", "Presley"), new Address("Main Boulevard", "69", "9999", "USA"), "0123456789", "elvis.presley@yahoo.com", new LicensePlate("KING888", "USA"));
        Member member2 = new Member(new Name("Tom", "ST"), new Address("TWwilsonlaan", "2c", "3010", "Belgium"), "0123456589", "tom.st@yahoo.com", new LicensePlate("qween999", "Belgium"));
        Member member3 = new Member(new Name("Nick", "L.M."), new Address("sintmaartensdal", "34", "3000", "Belgium"), "0123458589", "nick.lm@yahoo.com", new LicensePlate("tette89", "Belgium"));
        Member member4 = new Member(new Name("test", "test"), new Address("sintmaartensdal", "34", "3000", "udfizf"), "0123458589", "nefzick.lm@yahoo.com", new LicensePlate("tezefztte89", "Belfzefgium"));
        member2 = memberService.registerMember(member2);
        member1 = memberService.registerMember(member1);
        member3 = memberService.registerMember(member3);
        member4 = memberService.registerMember(member4);
        ParkingLot parkingLot1 = parkingLotService.addNewParkingLot(new ParkingLot("pk1", ParkingCategory.UNDERGROUND_BUILDING, 5,
                new ContactPerson("tom.st@g.be", "0496209967", null,
                        new com.team4.domain.parkinglot.Address("elfnovemberlaan", "82", "3000, Leuven")), divisionFinal));
        ParkingLot parkingLot2 = parkingLotService.addNewParkingLot(new ParkingLot("pk2", ParkingCategory.ABOVE_GROUND_MEMBER, 7,
                new ContactPerson("nick.st@g.be", "0496858585", null,
                        new com.team4.domain.parkinglot.Address("groove street", "69", "3010, Kessel-lo")), divisionFinal));
        ParkingLot parkingLot3 = parkingLotService.addNewParkingLot(new ParkingLot("pk3", ParkingCategory.ABOVE_GROUND_MEMBER, 8,
                new ContactPerson("un.known@g.be", "0496878285", null,
                        new com.team4.domain.parkinglot.Address("brusselsesteenweg", "78", "3010, Kessel-lo")), divisionFinal));
        ParkingLot parkingLot4 = parkingLotService.addNewParkingLot(new ParkingLot("pk4", ParkingCategory.ABOVE_GROUND_MEMBER, 9,
                new ContactPerson("un.knrown@g.be", "0496878285", null,
                        new com.team4.domain.parkinglot.Address("jbksdbv", "78", "3010, dssdc")), divisionFinal));
        Clock clock = Clock.fixed(Instant.parse("2019-12-22T10:15:30.00Z"), ZoneId.of("UTC"));
        allocationRepository.save(new Allocation(member2, parkingLot2,clock));
        clock = Clock.fixed(Instant.parse("2019-12-23T10:15:30.00Z"), ZoneId.of("UTC"));
        allocationRepository.save(new Allocation(member1, parkingLot1, clock)).stopAllocation();
        clock = Clock.fixed(Instant.parse("2019-12-24T10:15:30.00Z"), ZoneId.of("UTC"));
        allocationRepository.save(new Allocation(member3, parkingLot3, clock)).stopAllocation();
        clock = Clock.fixed(Instant.parse("2019-12-25T10:15:30.00Z"), ZoneId.of("UTC"));
        allocationRepository.save(new Allocation(member4, parkingLot4,clock)).stopAllocation();
    }
}
