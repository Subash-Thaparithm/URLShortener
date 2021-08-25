package io.twodigits.urlshortener.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class GetURLMetricsResponse {
    private UUID          id;

    private String        urlId;

    private int           accessCount;

    private String        caller;

    private LocalDateTime lastAccessedDatetime;

    private String        responseCode;

    private String        responseMessage;

    private Long          responseDuration;

    public int getAccessCount() {
        return accessCount;
    }

    public String getCaller() {
        return caller;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getLastAccessedDatetime() {
        return lastAccessedDatetime;
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

    public String getUrlId() {
        return urlId;
    }

    public void setAccessCount(final int accessCount) {
        this.accessCount = accessCount;
    }

    public void setCaller(final String caller) {
        this.caller = caller;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public void setLastAccessedDatetime(final LocalDateTime lastAccessedDatetime) {
        this.lastAccessedDatetime = lastAccessedDatetime;
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

    public void setUrlId(final String urlId) {
        this.urlId = urlId;
    }
}
