package io.twodigits.urlshortener.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.repository.URLRepository;
import io.twodigits.urlshortener.utils.EncoderDecoder;

@Service
@Transactional
public class URLShortenerServiceImpl implements URLShortenerService {
    @Autowired
    private URLRepository repository;

    @Override
    public URL addURL(final String user, final String url) throws Exception {
        final Optional<URL> existingURL = repository.findByUrlAndUser(url, user);
        final Iterable<URL> urls = repository.findAll();
        if (existingURL.isPresent()) {
            throw new Exception("url " + url + " for the user " + user + " already exists.");
        }
        final long currCount = repository.count() + 1;
        final String shortUrl = EncoderDecoder.Encode((int) currCount);

        final URL objUrl = new URL();
        objUrl.setId(shortUrl);
        objUrl.setUrl(url);
        objUrl.setUser(user);

        repository.save(objUrl);

        return objUrl;
    }

    @Override
    public void deleteURL(final String user, final String id) throws Exception {
        final Optional<URL> url = repository.findByIdAndUser(id, user);
        if (url.isPresent()) {
            repository.delete(url.get());
        } else {
            throw new Exception("The url " + id + " for the given user " + user + " is not found.");
        }
    }

    @Override
    public Optional<URL> getURL(final String id) {
        return repository.findById(id);
    }

    @Override
    public Optional<URL> getURL(final String user, final String id) {
        return repository.findByIdAndUser(id, user);
    }

    @Override
    public Iterable<URL> listURLs(final String user) {
        return repository.findByUser(user);
    }
}
