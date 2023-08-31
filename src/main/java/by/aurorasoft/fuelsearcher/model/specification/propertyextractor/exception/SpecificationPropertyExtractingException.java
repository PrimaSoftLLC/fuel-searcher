package by.aurorasoft.fuelsearcher.model.specification.propertyextractor.exception;

public final class SpecificationPropertyExtractingException extends RuntimeException {

    public SpecificationPropertyExtractingException() {

    }

    public SpecificationPropertyExtractingException(final String description) {
        super(description);
    }

    public SpecificationPropertyExtractingException(final Exception cause) {
        super(cause);
    }

    public SpecificationPropertyExtractingException(final String description, final Exception cause) {
        super(description, cause);
    }

}
