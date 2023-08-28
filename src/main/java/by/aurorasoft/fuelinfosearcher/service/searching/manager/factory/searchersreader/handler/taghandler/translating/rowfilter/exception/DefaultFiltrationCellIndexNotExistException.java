package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.translating.rowfilter.exception;

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
