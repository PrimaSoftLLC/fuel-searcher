package by.aurorasoft.fuelinfosearcher.service.searcher.manager.factory.searchersreader.handler.taghandler.headername.exception;

public final class NoSuchPropertyExtractorException extends RuntimeException {

    public NoSuchPropertyExtractorException() {

    }

    public NoSuchPropertyExtractorException(final String description) {
        super(description);
    }

    public NoSuchPropertyExtractorException(final Exception cause) {
        super(cause);
    }

    public NoSuchPropertyExtractorException(final String description, final Exception cause) {
        super(description, cause);
    }

}
