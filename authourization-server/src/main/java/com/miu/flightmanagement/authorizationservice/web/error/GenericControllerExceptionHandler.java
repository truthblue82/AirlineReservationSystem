package com.miu.flightmanagement.authorizationservice.web.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GenericControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserAlreadyExistException.class})
    protected ResponseEntity<Object> handleUserExistingError(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "User has been registered!", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {UserNotExistException.class})
    protected ResponseEntity<Object> handleUserNotExistingError(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "User is not present!", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
