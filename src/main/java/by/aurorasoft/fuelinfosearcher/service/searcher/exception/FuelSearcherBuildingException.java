package by.aurorasoft.fuelinfosearcher.service.searcher.exception;

public final class FuelSearcherBuildingException extends RuntimeException {

    public FuelSearcherBuildingException() {

    }

    public FuelSearcherBuildingException(final String description) {
        super(description);
    }

    public FuelSearcherBuildingException(final Exception cause) {
        super(cause);
    }

    public FuelSearcherBuildingException(final String description, final Exception cause) {
        super(description, cause);
    }
}
