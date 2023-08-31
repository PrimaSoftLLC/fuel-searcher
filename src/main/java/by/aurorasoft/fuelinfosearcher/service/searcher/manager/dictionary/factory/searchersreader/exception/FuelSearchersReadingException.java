package by.aurorasoft.fuelinfosearcher.service.searcher.manager.dictionary.factory.searchersreader.exception;

public final class FuelSearchersReadingException extends RuntimeException {

    public FuelSearchersReadingException() {

    }

    public FuelSearchersReadingException(final String description) {
        super(description);
    }

    public FuelSearchersReadingException(final Exception cause) {
        super(cause);
    }

    public FuelSearchersReadingException(final String description, final Exception cause) {
        super(description, cause);
    }

}
