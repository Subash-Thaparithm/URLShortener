package io.twodigits.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.twodigits.urlshortener.model.URLMetrics;
import io.twodigits.urlshortener.repository.URLMetricsRepository;

@Service
@Transactional
public class URLMetricsService {
    @Autowired
    private URLMetricsRepository repository;

    public Iterable<URLMetrics> getURLMEtrics(final String id) throws Exception {
        final Iterable<URLMetrics> metrics = repository.findByUrlId(id);

        return metrics;

    }

}
