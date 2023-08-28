package by.aurorasoft.fuelinfosearcher.service.searcher.exception;

public final class FuelInfoSearchingException extends RuntimeException {

    public FuelInfoSearchingException() {

    }

    public FuelInfoSearchingException(final String description) {
        super(description);
    }

    public FuelInfoSearchingException(final Exception cause) {
        super(cause);
    }

    public FuelInfoSearchingException(final String description, final Exception cause) {
        super(description, cause);
    }

}
