package by.aurorasoft.fuelinfosearcher.service.searching.exception;

public final class FuelInfoSearchingServiceNotExistException extends RuntimeException {

    public FuelInfoSearchingServiceNotExistException() {

    }

    public FuelInfoSearchingServiceNotExistException(final String description) {
        super(description);
    }

    public FuelInfoSearchingServiceNotExistException(final Exception cause) {
        super(cause);
    }

    public FuelInfoSearchingServiceNotExistException(final String description, final Exception cause) {
        super(description, cause);
    }

}
