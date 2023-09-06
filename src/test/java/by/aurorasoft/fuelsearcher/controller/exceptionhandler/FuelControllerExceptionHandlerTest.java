package by.aurorasoft.fuelsearcher.controller.exceptionhandler;

import by.aurorasoft.fuelsearcher.controller.exception.NoSuchFuelException;
import by.aurorasoft.fuelsearcher.controller.exception.NotValidSpecificationException;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import static java.lang.Class.forName;
import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public final class FuelControllerExceptionHandlerTest {
    private static final String CLASS_NAME_ERROR_RESPONSE
            = "by.aurorasoft.fuelsearcher.controller.exceptionhandler.FuelControllerExceptionHandler$RestErrorResponse";
    private static final Class<?> CLASS_ERROR_RESPONSE = findClassErrorResponse();

    private static final String FIELD_NAME_ERROR_RESPONSE_HTTP_STATUS = "httpStatus";
    private static final String FIELD_NAME_ERROR_RESPONSE_MESSAGE = "message";
    private static final String FIELD_NAME_ERROR_RESPONSE_DATE_TIME = "dateTime";

    private final FuelControllerExceptionHandler exceptionHandler = new FuelControllerExceptionHandler();

    @Test
    public void noSuchFuelExceptionShouldBeHandled()
            throws Exception {
        final String givenExceptionDescription = "exception-description";
        final NoSuchFuelException givenException = new NoSuchFuelException(givenExceptionDescription);

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
    public void notValidSpecificationExceptionShouldBeHandled()
            throws Exception {
        final String givenExceptionDescription = "exception-description";
        final NotValidSpecificationException givenException = new NotValidSpecificationException(givenExceptionDescription);

        final ResponseEntity<?> actual = this.exceptionHandler.handleException(givenException);
        final HttpStatus expectedHttpStatus = NOT_ACCEPTABLE;
        assertSame(expectedHttpStatus, actual.getStatusCode());

        final Object actualErrorResponse = actual.getBody();

        final HttpStatus actualErrorResponseHttpStatus = findErrorResponseHttpStatus(actualErrorResponse);
        assertSame(expectedHttpStatus, actualErrorResponseHttpStatus);

        final String actualErrorResponseMessage = findErrorResponseMessage(actualErrorResponse);
        assertSame(givenExceptionDescription, actualErrorResponseMessage);

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

    private static HttpStatus findErrorResponseHttpStatus(final Object errorResponse)
            throws Exception {
        return findErrorResponseProperty(errorResponse, FIELD_NAME_ERROR_RESPONSE_HTTP_STATUS, HttpStatus.class);
    }

    private static String findErrorResponseMessage(final Object errorResponse)
            throws Exception {
        return findErrorResponseProperty(errorResponse, FIELD_NAME_ERROR_RESPONSE_MESSAGE, String.class);
    }

    private static LocalDateTime findErrorResponseDateTime(final Object errorResponse)
            throws Exception {
        return findErrorResponseProperty(errorResponse, FIELD_NAME_ERROR_RESPONSE_DATE_TIME, LocalDateTime.class);
    }

    private static <T> T findErrorResponseProperty(final Object errorResponse,
                                                   final String fieldName,
                                                   final Class<T> propertyType)
            throws Exception {
        final Field field = CLASS_ERROR_RESPONSE.getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            final Object property = field.get(errorResponse);
            return propertyType.cast(property);
        } finally {
            field.setAccessible(false);
        }
    }
}
