package com.aurorasoft.fuelsearcher.controller;

import com.aurorasoft.fuelsearcher.controller.exception.NoSuchEntityException;
import com.aurorasoft.fuelsearcher.controller.exception.NotValidSpecificationException;
import com.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingResult;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static com.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

public final class ControllerExceptionHandlerTest {
    private static final String FIELD_NAME_ERROR_RESPONSE_HTTP_STATUS = "httpStatus";
    private static final String FIELD_NAME_ERROR_RESPONSE_MESSAGE = "message";
    private static final String FIELD_NAME_ERROR_RESPONSE_DATE_TIME = "dateTime";

    private final ControllerExceptionHandler exceptionHandler = new ControllerExceptionHandler();

    @Test
    public void noSuchEntityExceptionShouldBeHandled() {
        final String givenDescription = "exception-description";
        final NoSuchEntityException givenException = new NoSuchEntityException(givenDescription);

        final ResponseEntity<?> actual = this.exceptionHandler.handleException(givenException);
        final HttpStatus expectedHttpStatus = NOT_FOUND;
        assertSame(expectedHttpStatus, actual.getStatusCode());

        final Object actualErrorResponse = actual.getBody();

        final HttpStatus actualErrorResponseHttpStatus = findErrorResponseHttpStatus(actualErrorResponse);
        assertSame(expectedHttpStatus, actualErrorResponseHttpStatus);

        final String actualErrorResponseMessage = findErrorResponseMessage(actualErrorResponse);
        assertSame(givenDescription, actualErrorResponseMessage);

        final LocalDateTime actualErrorResponseDateTime = findErrorResponseDateTime(actualErrorResponse);
        assertNotNull(actualErrorResponseDateTime);
    }

    @Test
    public void notValidSpecificationExceptionShouldBeHandled() {
        final List<String> givenFailedPropertyNames = List.of("first-property", "second-property", "third-property");
        final SpecificationValidatingResult givenValidatingResult = createValidatingResult(givenFailedPropertyNames);

        final NotValidSpecificationException givenException = new NotValidSpecificationException(givenValidatingResult);

        final ResponseEntity<?> actual = this.exceptionHandler.handleException(givenException);
        final HttpStatus expectedHttpStatus = NOT_ACCEPTABLE;
        assertSame(expectedHttpStatus, actual.getStatusCode());

        final Object actualErrorResponse = actual.getBody();

        final HttpStatus actualErrorResponseHttpStatus = findErrorResponseHttpStatus(actualErrorResponse);
        assertSame(expectedHttpStatus, actualErrorResponseHttpStatus);

        final String actualErrorResponseMessage = findErrorResponseMessage(actualErrorResponse);
        final String expectedErrorResponseMessage = "Not valid properties: "
                + "first-property, second-property, third-property";
        assertEquals(expectedErrorResponseMessage, actualErrorResponseMessage);

        final LocalDateTime actualErrorResponseDateTime = findErrorResponseDateTime(actualErrorResponse);
        assertNotNull(actualErrorResponseDateTime);
    }

    @Test
    public void exceptionShouldBeHandled() {
        final String givenDescription = "exception-description";
        final Exception givenException = new Exception(givenDescription);

        final ResponseEntity<?> actual = this.exceptionHandler.handleException(givenException);
        final HttpStatus expectedHttpStatus = INTERNAL_SERVER_ERROR;
        assertSame(expectedHttpStatus, actual.getStatusCode());

        final Object actualErrorResponse = actual.getBody();

        final HttpStatus actualErrorResponseHttpStatus = findErrorResponseHttpStatus(actualErrorResponse);
        assertSame(expectedHttpStatus, actualErrorResponseHttpStatus);

        final String actualErrorResponseMessage = findErrorResponseMessage(actualErrorResponse);
        assertSame(givenDescription, actualErrorResponseMessage);

        final LocalDateTime actualErrorResponseDateTime = findErrorResponseDateTime(actualErrorResponse);
        assertNotNull(actualErrorResponseDateTime);
    }

    private static HttpStatus findErrorResponseHttpStatus(final Object errorResponse) {
        return findProperty(
                errorResponse,
                FIELD_NAME_ERROR_RESPONSE_HTTP_STATUS,
                HttpStatus.class
        );
    }

    private static String findErrorResponseMessage(final Object errorResponse) {
        return findProperty(
                errorResponse,
                FIELD_NAME_ERROR_RESPONSE_MESSAGE,
                String.class
        );
    }

    private static LocalDateTime findErrorResponseDateTime(final Object errorResponse) {
        return findProperty(
                errorResponse,
                FIELD_NAME_ERROR_RESPONSE_DATE_TIME,
                LocalDateTime.class
        );
    }

    private static SpecificationValidatingResult createValidatingResult(final List<String> failedPropertyNames) {
        final SpecificationValidatingResult validatingResult = mock(SpecificationValidatingResult.class);
        when(validatingResult.findFailedPropertyNames()).thenReturn(failedPropertyNames);
        return validatingResult;
    }
}
