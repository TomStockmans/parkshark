package com.team4.api.allocation;

import com.team4.domain.allocation.Allocation;
import com.team4.domain.division.Director;
import com.team4.domain.division.Division;
import com.team4.domain.member.LicensePlate;
import com.team4.domain.member.Member;
import com.team4.domain.member.MemberException;
import com.team4.domain.member.Name;
import com.team4.domain.parkinglot.Address;
import com.team4.domain.parkinglot.ContactPerson;
import com.team4.domain.parkinglot.ParkingCategory;
import com.team4.domain.parkinglot.ParkingLot;
import com.team4.service.allocation.AllocationService;
import com.team4.service.division.DivisionService;
import com.team4.service.member.MemberService;
import com.team4.service.parkinglot.ParkingLotService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AllocationControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private AllocationService allocationService;
    private MemberService memberService;
    private DivisionService divisionService;
    private ParkingLotService parkingLotService;
    private Division division;
    private Member member;
    private ParkingLot parkingLot;

    @Autowired
    public AllocationControllerIntegrationTest(
            AllocationService allocationService,
            MemberService memberService,
            DivisionService divisionService,
            ParkingLotService parkingLotService) {
        this.allocationService = allocationService;
        this.memberService = memberService;
        this.divisionService = divisionService;
        this.parkingLotService = parkingLotService;
    }

    @BeforeEach
    void setUp() {
        division = new Division("Awesome division", "Old division name..", new Director("Niels", "Delestinne"), null);
        parkingLot = new ParkingLot("pk1", ParkingCategory.UNDERGROUND_BUILDING, 5,
                new ContactPerson("tom.st@g.be", "0496209967", null,
                        new Address("elfnovemberlaan", "82", "3000, Leuven")), division);
        member = new Member(new Name("Elvis", "Presley"), new com.team4.domain.member.Address("Main Boulevard", "69", "9999", "USA"), "0123456789", "elvis.presley@yahoo.com", new LicensePlate("KING888", "USA"));
        divisionService.createDivision(division);
        memberService.registerMember(member);
        parkingLotService.addNewParkingLot(parkingLot);
    }

    @Test
    void startAllocation_givenCorrectParameters_thenStartAllocation() {
        CreateAllocationDto createAllocationDtoDto = new CreateAllocationDto(
                member.getId(),
                "KING888",
                parkingLot.getId()
        );

        AllocationDto allocationDto =
                RestAssured
                        .given()
                        .body(createAllocationDtoDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/allocations")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(AllocationDto.class);

        assertThat(allocationDto.memberId).isEqualTo(member.getId());
        assertThat(allocationDto.parkingLotId).isEqualTo(parkingLot.getId());
        assertThat(allocationDto.startTime).isNotNull();
        assertThat(allocationDto.stopTime).isNull();
    }

    @Test
    void startAllocation_givenInvalidMemberId_thenThrowException() {
        assertThatThrownBy(() -> {
            allocationService.startAllocation(999,"KING888", parkingLot.getId());
        }).isInstanceOf(MemberException.class);
    }

    @Test
    void stopAllocation_givenCorrectId_thenStopAllocation() {
        Allocation allocation = allocationService.startAllocation(
                member.getId(),
                member.getLicensePlate().getPlateNumber(),
                parkingLot.getId()
        );

        assertThat(allocation.getStopTime()).isNull();

        AllocationDto allocationDto =
                RestAssured
                        .given()
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/allocations/" + allocation.getId())
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(AllocationDto.class);

        assertThat(allocationDto.stopTime).isNotNull();
    }
}