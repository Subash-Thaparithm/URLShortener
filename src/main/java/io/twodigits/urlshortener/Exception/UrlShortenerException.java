package io.twodigits.urlshortener.Exception;

import java.io.Serializable;

public class UrlShortenerException extends Exception {
    private static final long serialVersionUID = 1L;

    public UrlShortenerException() {
        super();
    }

    public UrlShortenerException(final String message) {
        super(message);
    }

    public UrlShortenerException(final String message, final Serializable[] context) {
        super(message);

    }

    public UrlShortenerException(final String message, final Serializable[] context, final Throwable cause) {
        super(message, cause);

    }

    public UrlShortenerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UrlShortenerException(final String message, final Throwable cause, final boolean enableSuppression,
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UrlShortenerException(final Throwable cause) {
        super(cause);
    }
}
