package by.aurorasoft.fuelsearcher.service.searcher.manager.exception;

public final class NoSuchFuelException extends RuntimeException {

    public NoSuchFuelException() {

    }

    public NoSuchFuelException(final String description) {
        super(description);
    }

    public NoSuchFuelException(final Exception cause) {
        super(cause);
    }

    public NoSuchFuelException(final String description, final Exception cause) {
        super(description, cause);
    }

}
