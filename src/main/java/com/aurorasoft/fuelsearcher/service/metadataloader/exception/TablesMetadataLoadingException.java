package com.aurorasoft.fuelsearcher.service.metadataloader.exception;

public final class TablesMetadataLoadingException extends RuntimeException {

    @SuppressWarnings("unused")
    public TablesMetadataLoadingException() {

    }

    @SuppressWarnings("unused")
    public TablesMetadataLoadingException(final String description) {
        super(description);
    }

    public TablesMetadataLoadingException(final Exception cause) {
        super(cause);
    }

    @SuppressWarnings("unused")
    public TablesMetadataLoadingException(final String description, final Exception cause) {
        super(description, cause);
    }

}
