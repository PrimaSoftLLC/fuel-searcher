package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.fuelheadername.exception;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.exception.NoSuchKeyException;

public final class NoSuchFuelHeaderNameException extends NoSuchKeyException {

    public NoSuchFuelHeaderNameException() {

    }

    public NoSuchFuelHeaderNameException(final String description) {
        super(description);
    }

    public NoSuchFuelHeaderNameException(final Exception cause) {
        super(cause);
    }

    public NoSuchFuelHeaderNameException(final String description, final Exception cause) {
        super(description, cause);
    }

}
