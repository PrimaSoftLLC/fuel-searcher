package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.controller.exception.NoSuchEntityException;
import com.aurorasoft.fuelsearcher.controller.exception.NotValidSpecificationException;
import com.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static java.lang.String.join;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public final class ControllerExceptionHandler {
    private static final String TEMPLATE_MESSAGE_NOT_VALID_SPECIFICATION = "Not valid properties: %s";
    private static final String DELIMITER_SPECIFICATION_PROPERTIES = ", ";

    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleException(final NoSuchEntityException exception) {
        return createResponseEntity(
                exception,
                Exception::getMessage,
                NOT_FOUND
        );
    }

    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleException(final NotValidSpecificationException exception) {
        return createResponseEntity(
                exception,
                ControllerExceptionHandler::findMessage,
                NOT_ACCEPTABLE
        );
    }

    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleException(final Exception exception) {
        return createResponseEntity(
                exception,
                Exception::getMessage,
                INTERNAL_SERVER_ERROR
        );
    }

    private static <E extends Exception> ResponseEntity<RestErrorResponse> createResponseEntity(
            final E exception,
            final ExceptionMessageExtractor<E> messageExtractor,
            final HttpStatus httpStatus
    ) {
        final String message = messageExtractor.extract(exception);
        final RestErrorResponse errorResponse = createErrorResponse(message, httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    private static RestErrorResponse createErrorResponse(final String message, final HttpStatus httpStatus) {
        final LocalDateTime currentDateTime = now();
        return new RestErrorResponse(httpStatus, message, currentDateTime);
    }

    private static String findMessage(final NotValidSpecificationException exception) {
        final SpecificationValidatingResult validatingResult = exception.getValidatingResult();
        final String separatedFailedPropertyNames = findSeparatedFailedPropertyNames(validatingResult);
        return TEMPLATE_MESSAGE_NOT_VALID_SPECIFICATION.formatted(separatedFailedPropertyNames);
    }

    private static String findSeparatedFailedPropertyNames(final SpecificationValidatingResult validatingResult) {
        final List<String> failedPropertyNames = validatingResult.findFailedPropertyNames();
        return join(DELIMITER_SPECIFICATION_PROPERTIES, failedPropertyNames);
    }

    private record RestErrorResponse(HttpStatus httpStatus,
                                     String message,
                                     @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH-mm-ss") LocalDateTime dateTime) {
    }

    @FunctionalInterface
    private interface ExceptionMessageExtractor<E extends Exception> {
        String extract(final E exception);
    }
}
