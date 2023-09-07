package by.aurorasoft.fuelsearcher.controller.exceptionhandler;

import by.aurorasoft.fuelsearcher.controller.exception.NoSuchFuelException;
import by.aurorasoft.fuelsearcher.controller.exception.NotValidSpecificationException;
import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingResult;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static java.lang.String.join;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public final class FuelControllerExceptionHandler {
    private static final String TEMPLATE_MESSAGE_NOT_VALID_SPECIFICATION = "Specification should contain properties: %s";
    private static final String DELIMITER_SPECIFICATION_PROPERTIES = ", ";

    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleException(final NoSuchFuelException exception) {
        final String message = exception.getMessage();
        return createResponseEntity(message, NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleException(final NotValidSpecificationException exception) {
        final String message = findMessage(exception);
        return createResponseEntity(message, NOT_ACCEPTABLE);
    }

    private static ResponseEntity<RestErrorResponse> createResponseEntity(final String message,
                                                                          final HttpStatus httpStatus) {
        final RestErrorResponse errorResponse = createErrorResponse(message, httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    private static RestErrorResponse createErrorResponse(final String message, final HttpStatus httpStatus) {
        final LocalDateTime currentDateTime = now();
        return new RestErrorResponse(httpStatus, message, currentDateTime);
    }

    private static String findMessage(final NotValidSpecificationException exception) {
        final SpecificationValidatingResult validatingResult = exception.getValidatingResult();
        final String seperatedFailedPropertyNames = findSeperatedFailedPropertyNames(validatingResult);
        return TEMPLATE_MESSAGE_NOT_VALID_SPECIFICATION.formatted(seperatedFailedPropertyNames);
    }

    private static String findSeperatedFailedPropertyNames(final SpecificationValidatingResult validatingResult) {
        final List<String> failedPropertyNames = validatingResult.findFailedPropertyNames();
        return join(DELIMITER_SPECIFICATION_PROPERTIES, failedPropertyNames);
    }

    @Value
    private static class RestErrorResponse {
        HttpStatus httpStatus;
        String message;

        @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH-mm-ss")
        LocalDateTime dateTime;
    }
}
