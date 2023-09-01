package by.aurorasoft.fuelsearcher.service.builder.exception;

public final class NotDefinedPropertyException extends RuntimeException {

    public NotDefinedPropertyException() {

    }

    public NotDefinedPropertyException(final String description) {
        super(description);
    }

    public NotDefinedPropertyException(final Exception cause) {
        super(cause);
    }

    public NotDefinedPropertyException(final String description, final Exception cause) {
        super(description, cause);
    }

}
