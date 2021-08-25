package io.twodigits.urlshortener.web;

public class MyErrorBody {
    int    status;

    String message;

    public MyErrorBody(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setStatus(final int status) {
        this.status = status;
    }
}
