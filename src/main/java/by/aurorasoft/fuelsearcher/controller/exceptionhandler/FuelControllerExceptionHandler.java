package by.aurorasoft.fuelsearcher.controller.exceptionhandler;

import by.aurorasoft.fuelsearcher.controller.exception.NoSuchFuelException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public final class FuelControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<RestErrorResponse> handleException(final NoSuchFuelException exception) {
        final HttpStatus httpStatus = NOT_FOUND;
        final RestErrorResponse errorResponse = createErrorResponse(exception, httpStatus);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    private static RestErrorResponse createErrorResponse(final NoSuchFuelException exception,
                                                         final HttpStatus httpStatus) {
        final String message = exception.getMessage();
        final LocalDateTime currentDateTime = now();
        return new RestErrorResponse(httpStatus, message, currentDateTime);
    }

    @Value
    private static class RestErrorResponse {
        HttpStatus httpStatus;
        String message;

        @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH-mm-ss")
        LocalDateTime dateTime;
    }
}
