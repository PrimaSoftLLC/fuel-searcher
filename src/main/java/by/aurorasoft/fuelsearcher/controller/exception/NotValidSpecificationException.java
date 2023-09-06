package by.aurorasoft.fuelsearcher.controller.exception;

public final class NotValidSpecificationException extends RuntimeException {

    public NotValidSpecificationException() {

    }

    public NotValidSpecificationException(final String description) {
        super(description);
    }

    public NotValidSpecificationException(final Exception cause) {
        super(cause);
    }

    public NotValidSpecificationException(final String description, final Exception cause) {
        super(description, cause);
    }

}
