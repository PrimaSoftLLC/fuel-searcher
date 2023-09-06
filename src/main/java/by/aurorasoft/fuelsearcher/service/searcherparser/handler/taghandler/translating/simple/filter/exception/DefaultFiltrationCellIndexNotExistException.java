package by.aurorasoft.fuelsearcher.service.searcherparser.handler.taghandler.translating.simple.filter.exception;

public final class DefaultFiltrationCellIndexNotExistException extends RuntimeException {

    public DefaultFiltrationCellIndexNotExistException() {

    }

    public DefaultFiltrationCellIndexNotExistException(final String description) {
        super(description);
    }

    public DefaultFiltrationCellIndexNotExistException(final Exception cause) {
        super(cause);
    }

    public DefaultFiltrationCellIndexNotExistException(final String description, final Exception cause) {
        super(description, cause);
    }

}