package com.anaclarissi.todolist_spring.service.exceptions;

public class TaskValidationException extends RuntimeException {

    public TaskValidationException(String message) {

        super(message);

    }

}
