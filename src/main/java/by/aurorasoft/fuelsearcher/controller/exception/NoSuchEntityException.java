package by.aurorasoft.fuelsearcher.controller.exception;

public abstract class NoSuchEntityException extends RuntimeException {

    public NoSuchEntityException() {

    }

    public NoSuchEntityException(final String description) {
        super(description);
    }

    public NoSuchEntityException(final Exception cause) {
        super(cause);
    }

    public NoSuchEntityException(final String description, final Exception cause) {
        super(description, cause);
    }

}
