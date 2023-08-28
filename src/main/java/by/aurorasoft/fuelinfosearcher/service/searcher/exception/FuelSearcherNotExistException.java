package by.aurorasoft.fuelinfosearcher.service.searcher.exception;

public final class FuelSearcherNotExistException extends RuntimeException {

    public FuelSearcherNotExistException() {

    }

    public FuelSearcherNotExistException(final String description) {
        super(description);
    }

    public FuelSearcherNotExistException(final Exception cause) {
        super(cause);
    }

    public FuelSearcherNotExistException(final String description, final Exception cause) {
        super(description, cause);
    }

}
