package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.fueltablesearcher.exception;

public final class FuelTableNotExistException extends RuntimeException {

    public FuelTableNotExistException() {

    }

    public FuelTableNotExistException(final String description) {
        super(description);
    }

    public FuelTableNotExistException(final Exception cause) {
        super(cause);
    }

    public FuelTableNotExistException(final String description, final Exception cause) {
        super(description, cause);
    }

}
