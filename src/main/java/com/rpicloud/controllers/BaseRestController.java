package com.rpicloud.controllers;

import com.rpicloud.exceptions.MongoConnectionException;
import com.rpicloud.models.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by kaspernissen on 09/02/2016.
 */


class BaseRestController {

    @ExceptionHandler(value = MongoConnectionException.class)
    public ResponseEntity<ErrorMessage> mongoConnectionException(Exception exception, WebRequest request) {
        ErrorMessage error = new ErrorMessage(exception.getMessage(), exception.getStackTrace().toString(), request.getContextPath(), HttpStatus.SERVICE_UNAVAILABLE.value());
        return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
}