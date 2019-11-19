package com.team4.domain.allocation;

import com.team4.domain.division.Director;
import com.team4.domain.division.Division;
import com.team4.domain.division.DivisionRepository;
import com.team4.domain.member.*;
import com.team4.domain.parkinglot.ContactPerson;
import com.team4.domain.parkinglot.ParkingCategory;
import com.team4.domain.parkinglot.ParkingLot;
import com.team4.domain.parkinglot.ParkingLotRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

@DataJpaTest
class AllocationRepositoryTest {

    private final AllocationRepository allocationRepository;
    private final MemberRepository memberRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final DivisionRepository divisionRepository;

    private Allocation allocation1;
    private Allocation allocation2;
    private Allocation allocation3;
    private Allocation allocation4;
    private Member member1;
    private Member member2;
    private Member member3;
    private Member member4;
    private Division division;
    private ParkingLot parkingLot1;
    private ParkingLot parkingLot2;
    private ParkingLot parkingLot3;
    private ParkingLot parkingLot4;


    @Autowired
    public AllocationRepositoryTest(AllocationRepository allocationRepository,
                                    MemberRepository memberRepository,
                                    ParkingLotRepository parkingLotRepository,
                                    DivisionRepository divisionRepository) {

        this.allocationRepository = allocationRepository;
        this.memberRepository = memberRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.divisionRepository = divisionRepository;
    }

    @BeforeEach
    void setUp() {

        createAndSaveMembers();
        createAndSaveDivision();

        final Division divisionFinal = division;

        createAndSaveParkinglot(divisionFinal);
        createAndSaveAllocatations();
    }

    private void createAndSaveDivision() {
        division = divisionRepository.save( new Division("Awesome division", "Old division name..", new Director("Niels", "Delestinne"), null));
    }

    private void createAndSaveMembers() {
        member1 = new Member(new Name("Elvis", "Presley"), new Address("Main Boulevard", "69", "9999", "USA"), "0123456789", "elvis.presley@yahoo.com", new LicensePlate("KING888", "USA"));
        member2 = new Member(new Name("Tom", "ST"), new Address("TWwilsonlaan", "2c", "3010", "Belgium"), "0123456589", "tom.st@yahoo.com", new LicensePlate("qween999", "Belgium"));
        member3 = new Member(new Name("Nick", "L.M."), new Address("sintmaartensdal", "34", "3000", "Belgium"), "0123458589", "nick.lm@yahoo.com", new LicensePlate("tette89", "Belgium"));
        member4 = new Member(new Name("test", "test"), new Address("sintmaartensdal", "34", "3000", "udfizf"), "0123458589", "nefzick.lm@yahoo.com", new LicensePlate("tezefztte89", "Belfzefgium"));
        member1 = memberRepository.save(member1);
        member2 = memberRepository.save(member2);
        member3 = memberRepository.save(member3);
        member4 = memberRepository.save(member4);
    }

    private void createAndSaveParkinglot(Division divisionFinal) {
        parkingLot1 = parkingLotRepository.save(new ParkingLot("pk1", ParkingCategory.UNDERGROUND_BUILDING, 5,
                new ContactPerson("tom.st@g.be", "0496209967", null,
                        new com.team4.domain.parkinglot.Address("elfnovemberlaan", "82", "3000, Leuven")), divisionFinal));
        parkingLot2 = parkingLotRepository.save(new ParkingLot("pk2", ParkingCategory.ABOVE_GROUND_MEMBER, 7,
                new ContactPerson("nick.st@g.be", "0496858585", null,
                        new com.team4.domain.parkinglot.Address("groove street", "69", "3010, Kessel-lo")), divisionFinal));
        parkingLot3 = parkingLotRepository.save(new ParkingLot("pk3", ParkingCategory.ABOVE_GROUND_MEMBER, 8,
                new ContactPerson("un.known@g.be", "0496878285", null,
                        new com.team4.domain.parkinglot.Address("brusselsesteenweg", "78", "3010, Kessel-lo")), divisionFinal));
        parkingLot4 = parkingLotRepository.save(new ParkingLot("pk4", ParkingCategory.ABOVE_GROUND_MEMBER, 9,
                new ContactPerson("un.knrown@g.be", "0496878285", null,
                        new com.team4.domain.parkinglot.Address("jbksdbv", "78", "3010, dssdc")), divisionFinal));

    }

    private void createAndSaveAllocatations() {

        Clock clock = Clock.fixed(Instant.parse("2019-12-22T10:15:30.00Z"), ZoneId.of("UTC"));
        allocation1 = allocationRepository.save(new Allocation(member2, parkingLot2,clock));
        clock = Clock.fixed(Instant.parse("2019-12-23T10:15:30.00Z"), ZoneId.of("UTC"));
        allocation2 = allocationRepository.save(new Allocation(member1, parkingLot1, clock));
        allocation2.stopAllocation();
        clock = Clock.fixed(Instant.parse("2019-12-24T10:15:30.00Z"), ZoneId.of("UTC"));
        allocation3 = allocationRepository.save(new Allocation(member3, parkingLot3, clock));
        clock = Clock.fixed(Instant.parse("2019-12-25T10:15:30.00Z"), ZoneId.of("UTC"));
        allocation4 = allocationRepository.save(new Allocation(member4, parkingLot4,clock));
        allocation4.stopAllocation();
    }

    @Test
    void whenAskedForAllAllocations_ThenAllAllocationsAreGivenInTheCorrectOrder() {

        List<Allocation> allocations = allocationRepository.findBy(PageRequest.of(0, 10, Sort.by("startTime").ascending()));
        allocations.forEach(a -> System.out.println("var: " + a));
        Assertions.assertEquals(4, allocations.size());
        Assertions.assertEquals(allocation1, allocations.get(0));
        Assertions.assertEquals(allocation2, allocations.get(1));
        Assertions.assertEquals(allocation3, allocations.get(2));
        Assertions.assertEquals(allocation4, allocations.get(3));


    }

    @Test
    void whenAskedForAllAllocationsAndSortingDescending_ThenAllAllocationsAreGivenInTheCorrectOrder() {

        List<Allocation> allocations = allocationRepository.findBy(PageRequest.of(0, 10, Sort.by("startTime").descending()));
        allocations.forEach(a -> System.out.println("var: " + a));
        Assertions.assertEquals(4, allocations.size());
        Assertions.assertEquals(allocation4, allocations.get(0));
        Assertions.assertEquals(allocation3, allocations.get(1));
        Assertions.assertEquals(allocation2, allocations.get(2));
        Assertions.assertEquals(allocation1, allocations.get(3));

    }

    @Test
    void whenAskedForActiveAllocations_ThenAllAllocationsAreGivenInTheCorrectOrder() {
        List<Allocation> allocations = allocationRepository.findByStopTimeNull(PageRequest.of(0, 10, Sort.by("startTime").ascending()));
        Assertions.assertEquals(2, allocations.size());
        Assertions.assertEquals(allocation1, allocations.get(0));
        Assertions.assertEquals(allocation3, allocations.get(1));

    }

    @Test
    void whenAskedForActiveAllocationsAndSortingIsDescending_ThenAllAllocationsAreGivenInTheCorrectOrder() {
        List<Allocation> allocations = allocationRepository.findByStopTimeNull(PageRequest.of(0, 10, Sort.by("startTime").descending()));
        Assertions.assertEquals(2, allocations.size());
        Assertions.assertEquals(allocation3, allocations.get(0));
        Assertions.assertEquals(allocation1, allocations.get(1));
    }

    @Test
    void whenAskedForInActiveAllocations_ThenAllAllocationsAreGivenInTheCorrectOrder() {
        List<Allocation> allocations = allocationRepository.findByStopTimeNotNull(PageRequest.of(0, 10, Sort.by("startTime").ascending()));
        Assertions.assertEquals(2, allocations.size());
        Assertions.assertEquals(allocation2, allocations.get(0));
        Assertions.assertEquals(allocation4, allocations.get(1));

    }

    @Test
    void whenAskedForInActiveAllocationsAndSortingIsDescending_ThenAllAllocationsAreGivenInTheCorrectOrder() {
        List<Allocation> allocations = allocationRepository.findByStopTimeNotNull(PageRequest.of(0, 10, Sort.by("startTime").descending()));
        Assertions.assertEquals(2, allocations.size());
        Assertions.assertEquals(allocation4, allocations.get(0));
        Assertions.assertEquals(allocation2, allocations.get(1));
    }
}