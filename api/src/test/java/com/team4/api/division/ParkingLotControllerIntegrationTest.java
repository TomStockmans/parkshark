package com.team4.api.division;

import com.team4.api.parkinglot.*;
import com.team4.domain.division.Director;
import com.team4.domain.division.Division;
import com.team4.domain.division.DivisionRepository;
import com.team4.domain.parkinglot.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingLotControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private DivisionRepository divisionRepository;

    @Test
    void createParkingLotTest() {

        Division division = divisionRepository.save(new Division("Awesome division", "Old division name..", new Director("Niels", "Delestinne"), null));
        CreateParkingLotDto createParkingLotDto = new CreateParkingLotDto(
                "pk1",
                ParkingCategory.UNDERGROUND_BUILDING,
                7,
                new CreateParkingLotDto.ContactPerson(
                        "tom.st@m.be",
                        "0496209967",
                        "016258179",
                        new CreateParkingLotDto.ContactPerson.Adress(
                                "elfnovemberlaan",
                                "82",
                                "3010, Kessel-lo"
                        )

                ),
                division.getId()
        );

        SingleParkingLotDtoResponse parkingLotResponseDtos = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .body(createParkingLotDto)
                .port(port)
                .post(ParkingLotController.PARKING_LOT_CONTROLLER_RESOURCE_URL)
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(SingleParkingLotDtoResponse.class);
    }

    @Test
    void getAllParkingLotsTest() {
         ParkingLotResponseDto[] parkingLotResponseDtos = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .get(ParkingLotController.PARKING_LOT_CONTROLLER_RESOURCE_URL)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ParkingLotResponseDto[].class);
    }

    @Test
    void getASingleParkingLotTest() {

        Division division = new Division("Awesome division", "Old division name..", new Director("Niels", "Delestinne"),null);
        division = divisionRepository.save(division);
        ParkingLot parkingLot = new ParkingLot("pk3", ParkingCategory.ABOVE_GROUND_MEMBER, 8,
                new ContactPerson("unknown.st@g.be", "0496858585", null,
                        new Adress("groove street", "96", "3010, Kessel-lo")), division);
        parkingLotRepository.save(parkingLot);

        SingleParkingLotDtoResponse singleParkingLotDto = ParkingLotMapper.objectToSingleParkingLotDtoResponse(parkingLot);

        SingleParkingLotDtoResponse singleParkingLotDtoResponse = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(port)
                .get(ParkingLotController.PARKING_LOT_CONTROLLER_RESOURCE_URL + "/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(SingleParkingLotDtoResponse.class);

        Assertions.assertEquals(singleParkingLotDto, singleParkingLotDtoResponse);
    }
}