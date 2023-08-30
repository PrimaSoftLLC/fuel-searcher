package by.aurorasoft.fuelinfosearcher.service.searcher.manager.exception;

public final class SeveralSearchersForOneTableException extends RuntimeException {

    public SeveralSearchersForOneTableException() {

    }

    public SeveralSearchersForOneTableException(final String description) {
        super(description);
    }

    public SeveralSearchersForOneTableException(final Exception cause) {
        super(cause);
    }

    public SeveralSearchersForOneTableException(final String description, final Exception cause) {
        super(description, cause);
    }

}
