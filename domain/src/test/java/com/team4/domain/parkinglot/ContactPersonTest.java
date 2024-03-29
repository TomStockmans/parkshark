package com.team4.domain.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactPersonTest {

    @Test
    void whenEmailIsNotValid_ExceptionIsThrown() {

        Assertions.assertThrows(ParkingLotException.class, () -> {
            new ContactPerson("emailst.be", "0496209967", "016258989"
                    , new Address("straat", "56", "3000, Leuven"));
        });
        Assertions.assertThrows(ParkingLotException.class, () -> {
            new ContactPerson("email@stbe", "0496209967", "016258989"
                    , new Address("straat", "56", "3000, Leuven"));
        });
        Assertions.assertThrows(ParkingLotException.class, () -> {
            new ContactPerson("email@.be", "0496209967", "016258989"
                    , new Address("straat", "56", "3000, Leuven"));
        });

    }

    @Test
    void whenEmailIsValid_thenEmailIsRegistered() {
        Assertions.assertDoesNotThrow(() -> {
            new ContactPerson("email@test.be", "0496209967", "016258989"
                    , new Address("straat", "56", "3000, Leuven"));
        });
    }

    @Test
    void whenNoPhoneOrMobilePhoneIsGiven_thenExceptionIsThrown() {
        Assertions.assertThrows(ParkingLotException.class, () -> {
            new ContactPerson("email@st.be", null, null
                    , new Address("straat", "56", "3000, Leuven"));
        });
        Assertions.assertThrows(ParkingLotException.class, () -> {
            new ContactPerson("email@st.be", "", null
                    , new Address("straat", "56", "3000, Leuven"));
        });
        Assertions.assertThrows(ParkingLotException.class, () -> {
            new ContactPerson("email@st.be", null, ""
                    , new Address("straat", "56", "3000, Leuven"));
        });
        Assertions.assertThrows(ParkingLotException.class, () -> {
            new ContactPerson("email@st.be", "  ", ""
                    , new Address("straat", "56", "3000, Leuven"));
        });
    }

    @Test
    void whenPhoneOrMobilePhoneIsGiven_thenNoExceptionIsThrown() {
        Assertions.assertDoesNotThrow(() -> {
            new ContactPerson("email@st.be", "049620365489", null
                    , new Address("straat", "56", "3000, Leuven"));
        });
        Assertions.assertDoesNotThrow(() -> {
            new ContactPerson("email@st.be", null, "016258979"
                    , new Address("straat", "56", "3000, Leuven"));
        });
        Assertions.assertDoesNotThrow(() -> {
            new ContactPerson("email@st.be", "049620365489", ""
                    , new Address("straat", "56", "3000, Leuven"));
        });
        Assertions.assertDoesNotThrow(() -> {
            new ContactPerson("email@st.be", "", "016258979"
                    , new Address("straat", "56", "3000, Leuven"));
        });
        Assertions.assertDoesNotThrow(() -> {
            new ContactPerson("email@st.be", "049620365489", "016258979"
                    , new Address("straat", "56", "3000, Leuven"));
        });
    }

}