package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.intermediate.exception;

public final class FilterNotExistException extends RuntimeException {

    public FilterNotExistException() {

    }

    public FilterNotExistException(final String description) {
        super(description);
    }

    public FilterNotExistException(final Exception cause) {
        super(cause);
    }

    public FilterNotExistException(final String description, final Exception cause) {
        super(description, cause);
    }
}
