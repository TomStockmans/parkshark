package com.team4.api.division;

import com.team4.domain.division.Director;
import com.team4.domain.division.Division;
import com.team4.service.division.DivisionService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DivisionControllerIntegrationTest {

    @Autowired
    private DivisionService divisionService;
    private Division division;

    @BeforeEach
    void setUp() {
        division = new Division("Awesome division", "Old division name..", new Director("Niels", "Delestinne"));
        divisionService.createDivision(division);
    }

    @Test
    void getAllBooks_thenReturnBooks() {
        List divisionDtos =
                RestAssured
                        .given()
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
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
        DivisionDto divisionDto = new DivisionDto("Another division", "We used to have a boring name", new DirectorDto("Jan", "Janssens"));

        DivisionDto created =
                RestAssured
                        .given()
                        .body(divisionDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
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
}