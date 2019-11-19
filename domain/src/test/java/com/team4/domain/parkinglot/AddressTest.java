package com.team4.domain.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressTest {
    @Test
    void whenPostalCodeIsInvalid_exceptionIsThrown() {
        Assertions.assertThrows(ParkingLotException.class, () -> new Address("elfnovemberlaan", "82", "azerty"));
        Assertions.assertThrows(ParkingLotException.class, () -> new Address("elfnovemberlaan", "82", "3010,leuven"));
        Assertions.assertThrows(ParkingLotException.class, () -> new Address("elfnovemberlaan", "82", "3010leuven"));

    }

    @Test
    void whenPostalCodeIsValid_noExceptionIsThrown() {
        Assertions.assertDoesNotThrow(() -> new Address("elfnovemberlaan", "82", "3010, Kessel-lo"));
        Assertions.assertDoesNotThrow(() -> new Address("elfnovemberlaan", "82", "3000, Leuven"));
        Assertions.assertDoesNotThrow(() -> new Address("elfnovemberlaan", "82", "1000, Brussel"));

    }
}