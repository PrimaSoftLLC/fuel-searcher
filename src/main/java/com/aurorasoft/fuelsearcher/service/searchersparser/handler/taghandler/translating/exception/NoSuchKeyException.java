package com.aurorasoft.fuelsearcher.service.searchersparser.handler.taghandler.translating.exception;

public abstract class NoSuchKeyException extends RuntimeException {
    private static final String DESCRIPTION_TEMPLATE = "There is no mapping for '%s'";

    public NoSuchKeyException() {

    }

    public NoSuchKeyException(final String key) {
        this(key, null);
    }

    public NoSuchKeyException(final Exception cause) {
        super(cause);
    }

    public NoSuchKeyException(final String key, final Exception cause) {
        super(createDescription(key), cause);
    }

    private static String createDescription(final String key) {
        return DESCRIPTION_TEMPLATE.formatted(key);
    }

    @FunctionalInterface
    public interface NoSuchKeyExceptionFactory<E extends NoSuchKeyException> {
        E create(final String key);
    }
}
