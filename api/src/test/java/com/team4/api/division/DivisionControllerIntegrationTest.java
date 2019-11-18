package com.team4.api.division;

import com.team4.domain.division.Director;
import com.team4.domain.division.Division;
import com.team4.service.division.DivisionService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DivisionControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private DivisionService divisionService;
    private Division division;

    @BeforeEach
    void setUp() {
        division = new Division("Awesome division", "Old division name..", new Director("Niels", "Delestinne"));
        divisionService.createDivision(division);
    }

    @Test
    void getAllDivisions_thenReturnDivisions() {
        List divisionDtos =
                RestAssured
                        .given()
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .get("/division")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(List.class);

        assertThat(divisionDtos).isNotEmpty();
    }

    @Test
    void createDivision_givenCorrectDto_thenCreateDivisionAndAddToRepository() {
        CreateDivisionDto divisionDto = new CreateDivisionDto("Another division", "We used to have a boring name", new DirectorDto("Jan", "Janssens"));

        DivisionDto created =
                RestAssured
                        .given()
                        .body(divisionDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/division")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(DivisionDto.class);

        assertThat(created.name).isEqualTo(divisionDto.name);
        assertThat(created.originalName).isEqualTo(divisionDto.originalName);
        assertThat(created.director.firstName).isEqualTo(divisionDto.director.firstName);
        assertThat(created.director.lastName).isEqualTo(divisionDto.director.lastName);
    }

    @Test
    void getDivisionById_givenCorrectId_thenReturnDivisionDto() {
        DivisionDto foundDivision =
                RestAssured
                        .given()
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .get("/division/" + division.getId())
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(DivisionDto.class);

        assertThat(division.getName()).isEqualTo(foundDivision.name);
        assertThat(division.getOriginalName()).isEqualTo(foundDivision.originalName);
        assertThat(division.getDirector().getFirstName()).isEqualTo(foundDivision.director.firstName);
        assertThat(division.getDirector().getLastName()).isEqualTo(foundDivision.director.lastName);
    }
}