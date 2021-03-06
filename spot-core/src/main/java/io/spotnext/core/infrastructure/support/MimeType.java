package io.spotnext.core.infrastructure.support;

public enum MimeType {
	JAVASCRIPT("application/javascript"), JSON("application/json"), PLAINTEXT("text/plain"),;

	private String mimeType;

	private MimeType(final String mimeType) {
		this.mimeType = mimeType;
	}

	@Override
	public String toString() {
		return this.mimeType;
	}
}
