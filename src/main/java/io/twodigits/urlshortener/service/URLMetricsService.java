package io.twodigits.urlshortener.service;

import java.util.Optional;

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

    public URLMetrics getURLMEtrics(final String id) throws Exception {
        final Optional<URLMetrics> optional = repository.findByUrlId(id);
        final Iterable<URLMetrics> metrics = repository.findAll();

        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }

    }

}
