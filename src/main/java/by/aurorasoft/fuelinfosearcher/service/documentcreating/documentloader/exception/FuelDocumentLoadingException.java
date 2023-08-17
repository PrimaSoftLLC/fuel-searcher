package by.aurorasoft.fuelinfosearcher.service.documentcreating.documentloader.exception;

public final class FuelDocumentLoadingException extends RuntimeException {
    public FuelDocumentLoadingException() {

    }

    public FuelDocumentLoadingException(final String description) {
        super(description);
    }

    public FuelDocumentLoadingException(final Exception cause) {
        super(cause);
    }

    public FuelDocumentLoadingException(final String description, final Exception cause) {
        super(description, cause);
    }
}
