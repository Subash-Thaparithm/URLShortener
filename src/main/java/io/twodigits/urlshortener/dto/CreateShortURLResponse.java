package io.twodigits.urlshortener.dto;

import javax.persistence.Id;

public class CreateShortURLResponse {
    /**
     * The unique ID of an URL
     */
    @Id
    private String id;

    /**
     * The URL for which a short URL is provided
     */
    private String url;

    /**
     * The ID of a user to which this URL belongs
     */
    private String user;
    private String responseCode;

    private String responseMessage;

    private Long   responseDuration;

    public String getId() {
        return this.id;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public Long getResponseDuration() {
        return responseDuration;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getURL() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setResponseCode(final String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponseDuration(final Long responseDuration) {
        this.responseDuration = responseDuration;
    }

    public void setResponseMessage(final String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setUser(final String user) {
        this.user = user;
    }
}
