package com.team4.domain.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

    @Test
    void whenFindAllIsTrigger_AListIsReturned() {
        parkingLotRepository.save(new ParkingLot("pk1", ParkingCategory.UNDERGROUND_BUILDING, 5,
                new ContactPerson("tom.st@g.be", "0496209967", null,
                        new Adress("elfnovemberlaan", "82", "3000, Leuven"))));
        parkingLotRepository.save(new ParkingLot("pk2", ParkingCategory.ABOVE_GROUND_MEMBER, 7,
                new ContactPerson("nick.st@g.be", "0496858585", null,
                        new Adress("groove street", "69", "3010, Kessel-lo"))));
        Assertions.assertTrue(parkingLotRepository.findAll().size() == 2 );
    }
}