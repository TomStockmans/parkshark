package com.team4.api.parkinglot;

import com.team4.domain.parkinglot.ParkingLotException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class ParkingLotExceptionHandler {

    private ResponseEntity<String> error(
            final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(message, httpStatus);
    }

    @ExceptionHandler(ParkingLotException.class)
    public ResponseEntity<String> parkingLotException(final ParkingLotException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> anyException(final Exception e) {
        return error(new IllegalArgumentException("bad request, try again"), HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    }

}
