package by.aurorasoft.fuelsearcher.service.searcher.exception;

public final class FuelOffsetNotExistException extends RuntimeException {

    public FuelOffsetNotExistException() {

    }

    public FuelOffsetNotExistException(final String description) {
        super(description);
    }

    public FuelOffsetNotExistException(final Exception cause) {
        super(cause);
    }

    public FuelOffsetNotExistException(final String description, final Exception cause) {
        super(description, cause);
    }

}
