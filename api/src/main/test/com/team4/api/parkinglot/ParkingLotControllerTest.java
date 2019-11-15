package com.team4.api.parkinglot;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ParkingLotControllerTest {

    @Test
    void getAllParkingLotsTest() {
         ParkingLotResponseDto[] parkingLotResponseDtos = RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .port(8080)
                .get(ParkingLotController.PARKING_LOT_CONTROLLER_RESOURCE_URL)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(ParkingLotResponseDto[].class);
    }
}