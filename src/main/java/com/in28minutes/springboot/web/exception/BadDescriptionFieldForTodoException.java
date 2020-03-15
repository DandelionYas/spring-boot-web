package com.in28minutes.springboot.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
* This exception is only defined to see the impact of TodoErrorHandler
* If we don't have TodoErrorHandler handling response will be managed by default ResponseEntityExceptionHandler
* But in this case Response status will be overwritten in the TodoErrorHandler by @ResponseStatus
*/
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BadDescriptionFieldForTodoException extends RuntimeException {
}