package by.aurorasoft.fuelsearcher.controller;

import by.aurorasoft.fuelsearcher.controller.exception.NoSuchEntityException;
import by.aurorasoft.fuelsearcher.controller.exception.NotValidSpecificationException;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingResult;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static by.aurorasoft.fuelsearcher.testutil.ReflectionUtil.findProperty;
import static java.lang.Class.forName;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public final class ControllerExceptionHandlerTest {
    private static final String CLASS_NAME_ERROR_RESPONSE
            = "by.aurorasoft.fuelsearcher.controller.ControllerExceptionHandler$RestErrorResponse";
    private static final Class<?> CLASS_ERROR_RESPONSE = findClassErrorResponse();

    private static final String FIELD_NAME_ERROR_RESPONSE_HTTP_STATUS = "httpStatus";
    private static final String FIELD_NAME_ERROR_RESPONSE_MESSAGE = "message";
    private static final String FIELD_NAME_ERROR_RESPONSE_DATE_TIME = "dateTime";

    private final ControllerExceptionHandler exceptionHandler = new ControllerExceptionHandler();

    @Test
    public void noSuchFuelExceptionShouldBeHandled() {
        final String givenExceptionDescription = "exception-description";
        final NoSuchEntityException givenException = new NoSuchEntityException(givenExceptionDescription);

        final ResponseEntity<?> actual = this.exceptionHandler.handleException(givenException);
        final HttpStatus expectedHttpStatus = NOT_FOUND;
        assertSame(expectedHttpStatus, actual.getStatusCode());

        final Object actualErrorResponse = actual.getBody();

        final HttpStatus actualErrorResponseHttpStatus = findErrorResponseHttpStatus(actualErrorResponse);
        assertSame(expectedHttpStatus, actualErrorResponseHttpStatus);

        final String actualErrorResponseMessage = findErrorResponseMessage(actualErrorResponse);
        assertSame(givenExceptionDescription, actualErrorResponseMessage);

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

    private static Class<?> findClassErrorResponse() {
        try {
            return forName(CLASS_NAME_ERROR_RESPONSE);
        } catch (final ClassNotFoundException cause) {
            throw new RuntimeException(cause);
        }
    }

    private static HttpStatus findErrorResponseHttpStatus(final Object errorResponse) {
        return findErrorResponseProperty(
                errorResponse,
                FIELD_NAME_ERROR_RESPONSE_HTTP_STATUS,
                HttpStatus.class
        );
    }

    private static String findErrorResponseMessage(final Object errorResponse) {
        return findErrorResponseProperty(
                errorResponse,
                FIELD_NAME_ERROR_RESPONSE_MESSAGE,
                String.class
        );
    }

    private static LocalDateTime findErrorResponseDateTime(final Object errorResponse) {
        return findErrorResponseProperty(
                errorResponse,
                FIELD_NAME_ERROR_RESPONSE_DATE_TIME,
                LocalDateTime.class
        );
    }

    @SuppressWarnings("unchecked")
    private static <P> P findErrorResponseProperty(final Object errorResponse,
                                                   final String fieldName,
                                                   final Class<P> propertyType) {
        return findProperty(
                errorResponse,
                (Class<Object>) CLASS_ERROR_RESPONSE,
                fieldName,
                propertyType
        );
    }

    private static SpecificationValidatingResult createValidatingResult(final List<String> failedPropertyNames) {
        final SpecificationValidatingResult validatingResult = mock(SpecificationValidatingResult.class);
        when(validatingResult.findFailedPropertyNames()).thenReturn(failedPropertyNames);
        return validatingResult;
    }
}
