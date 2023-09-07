package by.aurorasoft.fuelsearcher.controller.exception;

import by.aurorasoft.fuelsearcher.service.validator.SpecificationValidatingResult;
import lombok.Getter;

public final class NotValidSpecificationException extends RuntimeException {

    @Getter
    private final SpecificationValidatingResult validatingResult;

    public NotValidSpecificationException(final SpecificationValidatingResult validatingResult) {
        this.validatingResult = validatingResult;
    }

    @SuppressWarnings("unused")
    public NotValidSpecificationException(final String description,
                                          final SpecificationValidatingResult validatingResult) {
        super(description);
        this.validatingResult = validatingResult;
    }

    @SuppressWarnings("unused")
    public NotValidSpecificationException(final Exception cause, final SpecificationValidatingResult validatingResult) {
        super(cause);
        this.validatingResult = validatingResult;
    }

    @SuppressWarnings("unused")
    public NotValidSpecificationException(final String description,
                                          final Exception cause,
                                          final SpecificationValidatingResult validatingResult) {
        super(description, cause);
        this.validatingResult = validatingResult;
    }

}
