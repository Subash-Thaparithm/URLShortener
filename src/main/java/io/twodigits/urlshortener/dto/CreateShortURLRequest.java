package io.twodigits.urlshortener.dto;

public class CreateShortURLRequest {

    /**
     * The URL for which a short URL is provided
     */
    private String url;

    public String getURL() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

}
