package by.aurorasoft.fuelsearcher.controller.exception;

public final class NoSuchFuelException extends RuntimeException {

    @SuppressWarnings("unused")
    public NoSuchFuelException() {

    }

    public NoSuchFuelException(final String description) {
        super(description);
    }

    @SuppressWarnings("unused")
    public NoSuchFuelException(final Exception cause) {
        super(cause);
    }

    @SuppressWarnings("unused")
    public NoSuchFuelException(final String description, final Exception cause) {
        super(description, cause);
    }

}
