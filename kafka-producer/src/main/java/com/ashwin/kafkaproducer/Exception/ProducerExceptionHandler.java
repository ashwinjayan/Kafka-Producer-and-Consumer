package com.ashwin.kafkaproducer.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

@ControllerAdvice
public class ProducerExceptionHandler {

    @ExceptionHandler(UnreachableProducer.class)
    public ResponseEntity<Object> handleUnreachableProducer(UnreachableProducer e, WebRequest req) {
        HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
        ErrorDetails errorDetails = new ErrorDetails(
            e.getMessage(),
                req.getDescription(false),
                httpStatus,
                ZonedDateTime.now(TimeZone.getDefault().toZoneId())
        );

        return new ResponseEntity<>(errorDetails, httpStatus);
    }
}
