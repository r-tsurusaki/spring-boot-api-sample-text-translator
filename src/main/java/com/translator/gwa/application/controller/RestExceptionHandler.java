package com.translator.gwa.application.controller;

import com.translator.gwa.application.resources.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * ${inheritDoc}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrorList = exception.getBindingResult().getFieldErrors();

        List<String> errorMessages = new ArrayList<>();

        for (FieldError fieldError : fieldErrorList) {
            log.error(fieldError.getDefaultMessage());
            errorMessages.add(fieldError.getDefaultMessage());
        }

        ErrorResponse response = ErrorResponse.builder()
                .xTrack(this.getXTrack())
                .message(String.join(",", errorMessages))
                .build();

        return super.handleExceptionInternal(exception, response, headers, status, request);
    }

    /**
     * Handle All Exception.
     *
     * @param exception Exception object.
     * @param request   WebRequest object.
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) {

        log.error(exception.getMessage());

        ErrorResponse response = ErrorResponse.builder()
                .xTrack(this.getXTrack())
                .message(exception.getMessage())
                .build();

        return super.handleExceptionInternal(
                exception, response, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * Get X-Track.
     *
     * @return X-Track Str.
     */
    private String getXTrack() {
        return MDC.get("X-Track");
    }
}
