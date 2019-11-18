package com.team4.api.division;

import com.team4.api.parkinglot.ParkingLotController;
import com.team4.api.parkinglot.ParkingLotResponseDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingLotControllerTest {
    @LocalServerPort
    private int port;

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
}