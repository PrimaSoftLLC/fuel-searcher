package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.translating.exception;

public abstract class NoSuchKeyException extends RuntimeException {

    public NoSuchKeyException() {

    }

    public NoSuchKeyException(final String description) {
        super(description);
    }

    public NoSuchKeyException(final Exception cause) {
        super(cause);
    }

    public NoSuchKeyException(final String description, final Exception cause) {
        super(description, cause);
    }

}
