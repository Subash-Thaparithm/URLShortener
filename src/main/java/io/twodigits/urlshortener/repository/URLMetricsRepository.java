package io.twodigits.urlshortener.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import io.twodigits.urlshortener.model.URLMetrics;

public interface URLMetricsRepository extends CrudRepository<URLMetrics, UUID> {

    /**
     * Find a URLMetrics by the identifier of its url ID.
     *
     * @param id
     *            the unique ID of an URL
     * @return a URLMetric object
     */

    Optional<URLMetrics> findByUrlId(String id);

}
