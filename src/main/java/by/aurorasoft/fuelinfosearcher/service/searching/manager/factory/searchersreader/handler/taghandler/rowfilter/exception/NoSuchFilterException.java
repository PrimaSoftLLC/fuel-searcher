package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.exception;

public final class NoSuchFilterException extends RuntimeException {

    public NoSuchFilterException() {

    }

    public NoSuchFilterException(final String description) {
        super(description);
    }

    public NoSuchFilterException(final Exception cause) {
        super(cause);
    }

    public NoSuchFilterException(final String description, final Exception cause) {
        super(description, cause);
    }
}
