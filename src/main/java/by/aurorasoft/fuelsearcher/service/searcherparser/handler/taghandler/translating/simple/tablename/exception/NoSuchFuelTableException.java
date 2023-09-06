package by.aurorasoft.fuelsearcher.service.searcherparser.handler.taghandler.translating.simple.tablename.exception;

import by.aurorasoft.fuelsearcher.service.searcherparser.handler.taghandler.translating.exception.NoSuchKeyException;

public final class NoSuchFuelTableException extends NoSuchKeyException {

    public NoSuchFuelTableException() {

    }

    public NoSuchFuelTableException(final String key) {
        super(key);
    }

    public NoSuchFuelTableException(final Exception cause) {
        super(cause);
    }

    public NoSuchFuelTableException(final String key, final Exception cause) {
        super(key, cause);
    }

}
