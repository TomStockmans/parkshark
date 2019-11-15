package com.team4.domain.division;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class DivisionRepositoryIntegrationTest {

    private DivisionRepository divisionRepository;

    @Autowired
    public DivisionRepositoryIntegrationTest(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    @Test
    void saveDivision_givenADivision_whenSaved_thenExistsInRepository() {
        Division division = new Division("Best division", "Just a division", new Director("Niels", "Delestinne"));
        Division saved = divisionRepository.save(division);
        Assertions.assertEquals(division, saved);
    }
}