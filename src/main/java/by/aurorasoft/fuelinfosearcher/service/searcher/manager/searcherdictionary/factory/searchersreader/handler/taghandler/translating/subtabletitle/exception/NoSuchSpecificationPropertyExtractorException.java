package by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.factory.searchersreader.handler.taghandler.translating.subtabletitle.exception;

import by.aurorasoft.fuelinfosearcher.service.searcher.manager.searcherdictionary.factory.searchersreader.handler.taghandler.translating.exception.NoSuchKeyException;

public final class NoSuchSpecificationPropertyExtractorException extends NoSuchKeyException {

    public NoSuchSpecificationPropertyExtractorException() {

    }

    public NoSuchSpecificationPropertyExtractorException(final String key) {
        super(key);
    }

    public NoSuchSpecificationPropertyExtractorException(final Exception cause) {
        super(cause);
    }

    public NoSuchSpecificationPropertyExtractorException(final String key, final Exception cause) {
        super(key, cause);
    }

}
