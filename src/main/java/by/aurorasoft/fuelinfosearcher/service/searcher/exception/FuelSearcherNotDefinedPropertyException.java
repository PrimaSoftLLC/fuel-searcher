package by.aurorasoft.fuelinfosearcher.service.searcher.exception;

public final class FuelSearcherNotDefinedPropertyException extends RuntimeException {

    public FuelSearcherNotDefinedPropertyException() {

    }

    public FuelSearcherNotDefinedPropertyException(final String description) {
        super(description);
    }

    public FuelSearcherNotDefinedPropertyException(final Exception cause) {
        super(cause);
    }

    public FuelSearcherNotDefinedPropertyException(final String description, final Exception cause) {
        super(description, cause);
    }

}
