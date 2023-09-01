package by.aurorasoft.fuelsearcher.service.dictionary.exception;

public final class SeveralValuesForOneAliasException extends RuntimeException {

    public SeveralValuesForOneAliasException() {

    }

    public SeveralValuesForOneAliasException(final String description) {
        super(description);
    }

    public SeveralValuesForOneAliasException(final Exception cause) {
        super(cause);
    }

    public SeveralValuesForOneAliasException(final String description, final Exception cause) {
        super(description, cause);
    }

}
