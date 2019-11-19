package com.team4.domain.parkinglot;

import com.team4.domain.division.Director;
import com.team4.domain.division.Division;
import com.team4.domain.division.DivisionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase
class ParkingLotRepositoryTest {
    private final ParkingLotRepository parkingLotRepository;
    private final DivisionRepository divisionRepository;

    @Autowired
    public ParkingLotRepositoryTest(ParkingLotRepository parkingLotRepository, DivisionRepository divisionRepository) {
        this.parkingLotRepository = parkingLotRepository;
        this.divisionRepository = divisionRepository;
    }

    @Test
    void whenAddingNewCorrectParkingLot_parkingLotIsAdded() {
        Division division = new Division("Awesome division", "Old division name..", new Director("Niels", "Delestinne"),null);

        division = divisionRepository.save(division);
        final Division divisionFinal = division;
        Assertions.assertDoesNotThrow(() -> parkingLotRepository.save(new ParkingLot("plaatsen", ParkingCategory.UNDERGROUND_BUILDING, 5,
                new ContactPerson("tom.st@g.be", "0496209967", null,
                        new Address("elfnovemberlaan", "82", "3010, Leuven")), divisionFinal))
        );
    }

    @Test
    void whenFindAllIsTrigger_AListIsReturned() {

        Division division = new Division("Awesome division", "Old division name..", new Director("Niels", "Delestinne"),null);

        division = divisionRepository.save(division);
        final Division divisionFinal = division;

        parkingLotRepository.save(new ParkingLot("pk1", ParkingCategory.UNDERGROUND_BUILDING, 5,
                new ContactPerson("tom.st@g.be", "0496209967", null,
                        new Address("elfnovemberlaan", "82", "3000, Leuven")), divisionFinal));
        parkingLotRepository.save(new ParkingLot("pk2", ParkingCategory.ABOVE_GROUND_MEMBER, 7,
                new ContactPerson("nick.st@g.be", "0496858585", null,
                        new Address("groove street", "69", "3010, Kessel-lo")), divisionFinal));
        Assertions.assertTrue(parkingLotRepository.findAll().size() == 2);
    }
}