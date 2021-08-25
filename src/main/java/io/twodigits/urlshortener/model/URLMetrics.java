package io.twodigits.urlshortener.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class URLMetrics {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private UUID          id;

    private String        urlId;

    private int           accessCount;

    private String        caller;

    private LocalDateTime lastAccessedDatetime;

    /*
     * @OneToOne(fetch = FetchType.LAZY, optional = false)
     * @JoinColumn(name = "urlId", nullable = false)
     */
    // private URL url;

    public int getAccessCount() {
        return accessCount;
    }

    public String getCaller() {
        return caller;
    }

    public LocalDateTime getDatetime() {
        return lastAccessedDatetime;
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

    public void setDatetime(final LocalDateTime localDateTime) {
        this.lastAccessedDatetime = localDateTime;
    }

    public void setUrlId(final String urlId) {
        this.urlId = urlId;
    }

}
