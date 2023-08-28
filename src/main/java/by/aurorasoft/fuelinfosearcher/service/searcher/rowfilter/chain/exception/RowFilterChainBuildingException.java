package by.aurorasoft.fuelinfosearcher.service.searcher.rowfilter.chain.exception;

public final class RowFilterChainBuildingException extends RuntimeException {

    public RowFilterChainBuildingException() {

    }

    public RowFilterChainBuildingException(final String description) {
        super(description);
    }

    public RowFilterChainBuildingException(final Exception cause) {
        super(cause);
    }

    public RowFilterChainBuildingException(final String description, final Exception cause) {
        super(description, cause);
    }

}
