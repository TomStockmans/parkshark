package com.team4.api.division;

import com.team4.domain.division.DivisionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class DivisionControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DivisionControllerExceptionHandler.class);

    @ExceptionHandler(DivisionException.class)
    protected void divisionException(DivisionException ex, HttpServletResponse response) throws IOException {
        LOGGER.info(ex.getMessage(), ex);
        response.sendError(BAD_REQUEST.value(), ex.getMessage());
    }
}
