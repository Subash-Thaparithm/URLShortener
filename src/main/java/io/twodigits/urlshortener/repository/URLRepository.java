package io.twodigits.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import io.twodigits.urlshortener.model.URL;

public interface URLRepository extends CrudRepository<URL, String> {

    /**
     * Find a URL by the identifier of the user as well as its ID.
     *
     * @param id
     *            the unique ID of an URL
     * @param user
     *            the ID of a user
     * @return a collection of URL objects
     */
    Optional<URL> findByIdAndUser(String id, String user);

    /**
     * Find a URL by the identifier of the user as well as its long url.
     *
     * @param url
     *            the URL
     * @param user
     *            the ID of a user
     * @return a collection of URL objects
     */
    Optional<URL> findByUrlAndUser(String url, String user);

    /**
     * Find a URL by the identifier of the user.
     *
     * @param user
     *            the ID of a user
     * @return a collection of URL objects
     */
    Iterable<URL> findByUser(String user);

}
