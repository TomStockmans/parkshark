package com.team4.api.member;

import com.team4.api.member.dto.CreateMemberDto;
import com.team4.api.member.dto.MemberDto;
import com.team4.domain.member.Address;
import com.team4.domain.member.LicensePlate;
import com.team4.domain.member.Member;
import com.team4.domain.member.Name;
import com.team4.service.member.MemberService;
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
class MemberControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private MemberService memberService;
    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member(new Name("fra", "cresc"), new Address("Via Roma", "100", "1090", "Italy"), "029954583", "fc.cresc@gmail.com", new LicensePlate("test", "test"));
        memberService.registerMember(member);
    }

    @Test
    void getAllMembers_thenReturnMembers() {
        List members =
                RestAssured
                        .given()
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .get("members")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.OK.value())
                        .extract()
                        .as(List.class);

        assertThat(members).isNotEmpty();
    }

    @Test
    void getMemberById_givenExistingId_thenReturnCorrespondingMember() {
        MemberDto memberDto = RestAssured
                .given()
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .get("/members/" + member.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract().body().as(MemberDto.class);
        assertThat(member.getId()).isEqualTo(memberDto.getId());
    }

    @Test
    void registerMember_givenCorrectDto_thenCreateMember() {
        CreateMemberDto memberDto = new CreateMemberDto(new Name("Fran", "Cash"), new Address("Via Roma", "100", "1090", "Italy"), new LicensePlate("test", "test"), "029954583", "test.test@test.com", "GOLD");
        MemberDto newMember =
                RestAssured
                        .given()
                        .body(memberDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/members")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(MemberDto.class);

        assertThat(newMember.getName().equals(memberDto.getName()));
    }
}