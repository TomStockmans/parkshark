package com.team4.domain.allocation;

import com.team4.domain.division.Director;
import com.team4.domain.division.Division;
import com.team4.domain.division.DivisionRepository;
import com.team4.domain.member.*;
import com.team4.domain.parkinglot.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase
class AllocationRepositoryTest {

    private final AllocationRepository allocationRepository;
    private final MemberRepository memberRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final DivisionRepository divisionRepository;

    private Allocation allocation1;
    private Allocation allocation2;
    private Allocation allocation3;


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
        Member member1 = new Member(new Name("Elvis", "Presley"), new Address("Main Boulevard", "69", "9999", "USA"), "0123456789", "elvis.presley@yahoo.com", new LicensePlate("KING888", "USA"));
        Member member2 = new Member(new Name("Tom", "ST"), new Address("TWwilsonlaan", "2c", "3010", "Belgium"), "0123456589", "tom.st@yahoo.com", new LicensePlate("qween999", "Belgium"));
        Member member3 = new Member(new Name("Nick", "L.M."), new Address("sintmaartensdal", "34", "3000", "Belgium"), "0123458589", "nick.lm@yahoo.com", new LicensePlate("tette89", "Belgium"));
        member1 = memberRepository.save(member1);
        member2 = memberRepository.save(member2);
        member3 = memberRepository.save(member3);

        Division division = new Division("Awesome division", "Old division name..", new Director("Niels", "Delestinne"),null);
        division = divisionRepository.save(division);
        final Division divisionFinal = division;

        ParkingLot parkingLot1 = new ParkingLot("pk1", ParkingCategory.UNDERGROUND_BUILDING, 5,
                new ContactPerson("tom.st@g.be", "0496209967", null,
                        new Adress("elfnovemberlaan", "82", "3000, Leuven")),divisionFinal);
        ParkingLot parkingLot2 = new ParkingLot("pk2", ParkingCategory.ABOVE_GROUND_MEMBER, 7,
                new ContactPerson("nick.st@g.be", "0496858585", null,
                        new Adress("groove street", "69", "3010, Kessel-lo")),divisionFinal);
        ParkingLot parkingLot3 = new ParkingLot("pk3", ParkingCategory.ABOVE_GROUND_MEMBER, 8,
                new ContactPerson("un.known@g.be", "0496878285", null,
                        new Adress("brusselsesteenweg", "78", "3010, Kessel-lo")),divisionFinal);
        parkingLot1 = parkingLotRepository.save(parkingLot1);
        parkingLot2 = parkingLotRepository.save(parkingLot2);
        parkingLot3 = parkingLotRepository.save(parkingLot3);

        allocation1 = allocationRepository.save(new Allocation(member2, parkingLot2));
        allocation2 = allocationRepository.save(new Allocation(member1, parkingLot1));
        allocation3 = allocationRepository.save(new Allocation(member3, parkingLot3));

    }

    @Test
    void whenAskedForAllAllocations_ThenAllAllocationsAreGivenInTheCorrectOrder() {
        List<Allocation> allocations = allocationRepository.findByOrderByStartTimeAsc();
        Assertions.assertTrue(allocations.size() == 3);
        Assertions.assertEquals(allocation1, allocations.get(0));
        Assertions.assertEquals(allocation2, allocations.get(1));
        Assertions.assertEquals(allocation3, allocations.get(2));

    }
}