package io.twodigits.urlshortener.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class URL {
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

    // @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
    // "url")
    // private URLMetrics urlStat;

    public String getId() {
        return this.id;
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

    public void setUrl(final String url) {
        this.url = url;
    }

    public void setUser(final String user) {
        this.user = user;
    }
}
