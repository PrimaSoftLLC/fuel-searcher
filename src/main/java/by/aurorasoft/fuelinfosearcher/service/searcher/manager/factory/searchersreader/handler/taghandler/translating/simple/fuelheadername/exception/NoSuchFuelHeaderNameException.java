package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.simple.fuelheadername.exception;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.translating.exception.NoSuchKeyException;

public final class NoSuchFuelHeaderNameException extends NoSuchKeyException {

    public NoSuchFuelHeaderNameException() {

    }

    public NoSuchFuelHeaderNameException(final String key) {
        super(key);
    }

    public NoSuchFuelHeaderNameException(final Exception cause) {
        super(cause);
    }

    public NoSuchFuelHeaderNameException(final String key, final Exception cause) {
        super(key, cause);
    }

}
