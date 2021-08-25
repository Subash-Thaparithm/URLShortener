package io.twodigits.urlshortener.dto;

public class CreateShortURLRequest {

    /**
     * The URL for which a short URL is provided
     */
    private String url;

    /**
     * The ID of a user to which this URL belongs
     */
    private String user;

    public String getURL() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setUser(final String user) {
        this.user = user;
    }
}
