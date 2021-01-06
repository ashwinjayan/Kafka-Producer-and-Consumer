package com.ashwin.kafkaproducer.Exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ErrorDetails {

    private String message;
    private String detail;
    private HttpStatus httpStatus;
    private ZonedDateTime timeStamp;

    public ErrorDetails(String message, String detail, HttpStatus httpStatus, ZonedDateTime timeStamp) {
        this.message = message;
        this.detail = detail;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }

    public ErrorDetails() {
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }
}
