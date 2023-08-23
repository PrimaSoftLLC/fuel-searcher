package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searcherreader.streamreader.exception;

public final class XMLReadingException extends RuntimeException {

    public XMLReadingException() {

    }

    public XMLReadingException(final String description) {
        super(description);
    }

    public XMLReadingException(final Exception cause) {
        super(cause);
    }

    public XMLReadingException(final String description, final Exception cause) {
        super(description, cause);
    }

}
