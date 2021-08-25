package io.twodigits.urlshortener.service;

import java.util.Optional;

import io.twodigits.urlshortener.model.URL;

public interface URLShortenerService {
    /**
     * Add a new URL to the collection of URLs for a user.
     *
     * @param user
     * @param url
     * @return The URL object which has been created
     * @throws Exception
     */
    URL addURL(String user, String url) throws Exception;

    /**
     * Delete a specific URL which belongs to a user.
     * 
     * @param user
     * @param id
     * @throws Exception
     */
    void deleteURL(String user, String id) throws Exception;

    /**
     * Return a specific URL by ID.
     *
     * @param id
     * @return The URL object
     */
    Optional<URL> getURL(String id);

    /**
     * Get a specific URL of a user by its ID.
     * 
     * @param user
     * @param id
     * @return The URL object
     */
    Optional<URL> getURL(String user, String id);

    /**
     * Get a list of all URLs that belong to a user.
     *
     * @param user
     * @return a list of users
     */
    Iterable<URL> listURLs(String user);
}
