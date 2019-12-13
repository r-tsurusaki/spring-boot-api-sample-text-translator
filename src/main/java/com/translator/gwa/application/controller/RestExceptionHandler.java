package com.translator.gwa.application.controller;

import com.translator.gwa.application.resources.TextTranslatorResponse;
import lombok.extern.slf4j.Slf4j;
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

        TextTranslatorResponse response = TextTranslatorResponse.builder()
                .message(String.join(",", errorMessages))
                .requestId(this.getRequestId(request))
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

        TextTranslatorResponse response = TextTranslatorResponse.builder()
                .message(exception.getMessage())
                .requestId(this.getRequestId(request))
                .build();

        return super.handleExceptionInternal(
                exception, response, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * Get requestId.
     *
     * @param request WebRequest Object.
     * @return requestId
     */
    @SuppressWarnings("ConstantConditions")
    private String getRequestId(WebRequest request) {

        return request.getAttribute("requestId", 0).toString();
    }
}
