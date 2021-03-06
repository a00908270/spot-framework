package io.spotnext.core.infrastructure.exception;

public class ModelNotFoundException extends AbstractModelException {
	private static final long serialVersionUID = 1L;

	public ModelNotFoundException(final String message) {
		super(message);
	}

	public ModelNotFoundException(final String message, final Throwable rootCause) {
		super(message, rootCause);
	}

	public ModelNotFoundException(final Throwable rootCause) {
		super(rootCause);
	}
}
