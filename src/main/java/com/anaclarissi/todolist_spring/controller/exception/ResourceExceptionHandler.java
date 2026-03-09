package com.anaclarissi.todolist_spring.controller.exception;


import com.anaclarissi.todolist_spring.service.exceptions.ResourceNotFoundException;
import com.anaclarissi.todolist_spring.service.exceptions.TaskValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {

        String error = "Reource Not Found";

        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(standardError);

    }

    @ExceptionHandler(TaskValidationException.class)
    public ResponseEntity<StandardError> validationError(TaskValidationException e, HttpServletRequest request) {

        String error = "Validation Error";

        HttpStatus status = HttpStatus.BAD_REQUEST;

        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(standardError);

    }

}
