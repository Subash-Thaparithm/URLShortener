package io.twodigits.urlshortener.dto;

public class DeleteShortURLResponse {

    private String responseCode;

    private String responseMessage;

    private Long   responseDuration;

    public String getResponseCode() {
        return responseCode;
    }

    public Long getResponseDuration() {
        return responseDuration;
    }

    public String getResponseMessage() {
        return responseMessage;
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
}
