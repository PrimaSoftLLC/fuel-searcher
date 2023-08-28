package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.intermediate.exception;

public final class FilterFactoryNotExistException extends RuntimeException {

    public FilterFactoryNotExistException() {

    }

    public FilterFactoryNotExistException(final String description) {
        super(description);
    }

    public FilterFactoryNotExistException(final Exception cause) {
        super(cause);
    }

    public FilterFactoryNotExistException(final String description, final Exception cause) {
        super(description, cause);
    }
}
