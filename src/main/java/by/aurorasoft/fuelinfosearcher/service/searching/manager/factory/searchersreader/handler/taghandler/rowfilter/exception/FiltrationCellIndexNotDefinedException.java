package by.aurorasoft.fuelinfosearcher.service.searching.manager.factory.searchersreader.handler.taghandler.rowfilter.exception;

public final class FiltrationCellIndexNotDefinedException extends RuntimeException {

    public FiltrationCellIndexNotDefinedException() {

    }

    public FiltrationCellIndexNotDefinedException(final String description) {
        super(description);
    }

    public FiltrationCellIndexNotDefinedException(final Exception cause) {
        super(cause);
    }

    public FiltrationCellIndexNotDefinedException(final String description, final Exception cause) {
        super(description, cause);
    }

}
