package com.team4.domain.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AdressTest {
    @Test
    void whenPostalCodeIsInvalid_exceptionIsThrown() {
        Assertions.assertThrows(ParkingLotException.class, () -> new Adress("elfnovemberlaan","82","azerty"));
        Assertions.assertThrows(ParkingLotException.class, () -> new Adress("elfnovemberlaan","82","3010,leuven"));
        Assertions.assertThrows(ParkingLotException.class, () -> new Adress("elfnovemberlaan","82","3010leuven"));

    }

    @Test
    void whenPostalCodeIsvalid_noExceptionIsThrown() {
        Assertions.assertDoesNotThrow( () -> new Adress("elfnovemberlaan","82","3010, Kessel-lo"));
        Assertions.assertDoesNotThrow( () -> new Adress("elfnovemberlaan","82","3000, Leuven"));
        Assertions.assertDoesNotThrow( () -> new Adress("elfnovemberlaan","82","1000, Brussel"));

    }
}