package com.team4.api;

import com.team4.domain.allocation.AllocationException;
import com.team4.domain.division.DivisionException;
import com.team4.domain.member.MemberException;
import com.team4.domain.parkinglot.ParkingLotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(AllocationException.class)
    protected void allocationException(AllocationException ex, HttpServletResponse response) throws IOException {
        LOGGER.info(ex.getMessage(), ex);
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(MemberException.class)
    protected void memberException(MemberException ex, HttpServletResponse response) throws IOException {
        LOGGER.info(ex.getMessage(), ex);
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(DivisionException.class)
    protected void divisionException(DivisionException ex, HttpServletResponse response) throws IOException {
        LOGGER.info(ex.getMessage(), ex);
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }

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
