package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.translating.rowfilter.exception;

import by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.translating.exception.NoSuchKeyException;

public final class NoSuchFilterException extends NoSuchKeyException {

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
