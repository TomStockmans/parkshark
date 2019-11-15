package com.team4.domain.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
@AutoConfigureTestDatabase
class ParkingLotRepositoryIntegrationTest {

    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotRepositoryIntegrationTest(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    @Test
    void whenAddingNewCorrectParkingLot_parkingLotIsAdded() {
        Assertions.assertDoesNotThrow(() -> parkingLotRepository.save(new ParkingLot("plaatsen", ParkingCategory.UNDERGROUND_BUILDING, 5,
                new ContactPerson("tom.st@g.be", "0496209967", null,
                        new Adress("elfnovemberlaan", "82", "3010, Leuven"))))
        );
    }


}