package io.twodigits.urlshortener.web;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import io.twodigits.urlshortener.Exception.UrlShortenerException;
import io.twodigits.urlshortener.dto.CreateShortURLRequest;
import io.twodigits.urlshortener.dto.CreateShortURLResponse;
import io.twodigits.urlshortener.dto.DeleteShortURLResponse;
import io.twodigits.urlshortener.dto.GetLongURLResponse;
import io.twodigits.urlshortener.dto.GetURLMetricsResponse;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.model.URLMetrics;
import io.twodigits.urlshortener.repository.URLMetricsRepository;
import io.twodigits.urlshortener.service.URLMetricsService;
import io.twodigits.urlshortener.service.URLShortenerServiceImpl;
import io.twodigits.urlshortener.utils.Mapper;

@RestController
public class UrlShortenerController {

    private static final Logger     LOGGER = LoggerFactory.getLogger(UrlShortenerController.class);

    @Autowired
    private URLShortenerServiceImpl service;

    @Autowired
    private URLMetricsService       metricsService;

    @Autowired
    private URLMetricsRepository    metricsRepository;

    @RequestMapping(path = "${pathPattern:/urls}", method = POST, consumes = { "text/*", "application/json" })
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<CreateShortURLResponse> createShortURL(@RequestBody final CreateShortURLRequest body,
            @RequestHeader(HttpHeaders.CONTENT_TYPE) final Object contentType) {

        final StopWatch sw = new StopWatch();
        sw.start();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start createListEntry() with the request body object\n" + body.toString());
        }
        ResponseEntity<CreateShortURLResponse> response = new ResponseEntity<>(HttpStatus.OK);

        try {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            final String user = authentication.getName();
            response = new ResponseEntity<>(
                    Mapper.convertCreateURLEntityToResponse(service.addURL(user, body.getURL())), HttpStatus.CREATED);

        } catch (final UrlShortenerException e) {
            LOGGER.error("Could not create a new list", e);
            final CreateShortURLResponse createresponse = new CreateShortURLResponse();
            createresponse.setResponseMessage(e.getMessage());
            createresponse.setResponseCode("409");
            response = new ResponseEntity<>(createresponse, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (final Exception e) {
            LOGGER.error("Could not create a new list", e);
            final CreateShortURLResponse createresponse = new CreateShortURLResponse();
            createresponse.setResponseMessage(e.getMessage());

            response = new ResponseEntity<>(createresponse, HttpStatus.INTERNAL_SERVER_ERROR);

        } finally {
            sw.stop();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("End createListEntry() with the response object\n" + response.toString());
            }
        }
        return response;
    }

    @RequestMapping(path = "${pathPattern:/urls/{id}}", method = DELETE)
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<DeleteShortURLResponse> deleteURL(@PathVariable("id") final String id,
            @RequestParam(required = true) final String user) {

        final StopWatch sw = new StopWatch();
        sw.start();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start deleteURL() for user {} and short url {}", user, id);
        }

        ResponseEntity<DeleteShortURLResponse> response = new ResponseEntity<>(HttpStatus.OK);
        try {

            service.deleteURL(user, id);

        } catch (final Exception e) {
            LOGGER.error("Could not deleteURLthe url", e);
            final DeleteShortURLResponse deleteresponse = new DeleteShortURLResponse();
            deleteresponse.setResponseMessage(e.getMessage());

            response = new ResponseEntity<>(deleteresponse, HttpStatus.INTERNAL_SERVER_ERROR);

        } finally {
            sw.stop();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("End deleteURL() with the response object\n" + response.toString());
            }

        }
        return response;
    }

    @GetMapping(path = "${pathPattern:/urls/{id}}")
    public ResponseEntity<GetLongURLResponse> getLongURL(@PathVariable("id") final String id,
            @RequestParam(required = false) final String user) throws Exception {

        final StopWatch sw = new StopWatch();
        sw.start();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start getLongURL() for user {} and short url {}", user, id);
        }

        ResponseEntity<GetLongURLResponse> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<URL> url = null;
        try {

            GetLongURLResponse getResponse = new GetLongURLResponse();
            if (user == null) {
                url = service.getURL(id);
            } else {
                url = service.getURL(user, id);
            }
            if (url.isPresent()) {
                getResponse = Mapper.convertGetURLEntityToResponse(url.get());
            } else {
                throw new Exception("The url corresponding to the given short url " + id + " for the user " + user
                        + " is not found.");
            }
            response = new ResponseEntity<>(getResponse, HttpStatus.OK);

        } catch (final Exception e) {
            LOGGER.error("Could not get the long url", e);
            GetLongURLResponse getResponse = new GetLongURLResponse();
            getResponse = new GetLongURLResponse();
            getResponse.setResponseMessage(e.getMessage());

            response = new ResponseEntity<>(getResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        } finally {
            sw.stop();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("End getLongURL() with the response object\n" + response.toString());
            }

        }

        return response;
    }

    @GetMapping(path = "${pathPattern:/urlmetrics/{id}}")

    public ResponseEntity<GetURLMetricsResponse> getUrlMetrics(@PathVariable("id") final String id,
            @RequestParam(required = false) final String user) {

        final StopWatch sw = new StopWatch();
        sw.start();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start getLongURL() for user {} and short url {}", user, id);
        }

        ResponseEntity<GetURLMetricsResponse> response = new ResponseEntity<>(HttpStatus.OK);
        Iterable<URLMetrics> urlMetrics = null;
        try {

            GetURLMetricsResponse getResponse = new GetURLMetricsResponse();

            urlMetrics = metricsService.getURLMEtrics(id);

            if (urlMetrics != null && ((Collection<?>) urlMetrics).size() != 0) {
                getResponse = Mapper.convertGetURLMetricsEntityToResponse(urlMetrics);
            } else {
                throw new Exception("The urlmetrics corresponding to the given short url " + id + " is not found.");
            }
            response = new ResponseEntity<>(getResponse, HttpStatus.OK);

        } catch (final Exception e) {
            LOGGER.error("Could not get the long url", e);
            final GetURLMetricsResponse getResponse = new GetURLMetricsResponse();

            getResponse.setResponseMessage(e.getMessage());

            response = new ResponseEntity<>(getResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        } finally {
            sw.stop();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("End getLongURL() with the response object\n" + response.toString());
            }

        }

        return response;
    }

    @GetMapping(path = "${pathPattern:/{id}}")
    public RedirectView redirectLongURL(@PathVariable("id") final String id) throws Exception {

        final StopWatch sw = new StopWatch();
        sw.start();

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String user = authentication.getName();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Start getLongURL() for user {} and short url {}", user, id);
        }

        ResponseEntity<GetLongURLResponse> response = new ResponseEntity<>(HttpStatus.OK);
        Optional<URL> url = null;
        try {

            GetLongURLResponse getResponse = new GetLongURLResponse();

            url = service.getURL(id);

            if (url.isPresent()) {
                getResponse = Mapper.convertGetURLEntityToResponse(url.get());
            } else {
                throw new Exception("The url corresponding to the given short url " + id + " for the user " + user
                        + " is not found.");
            }
            response = new ResponseEntity<>(getResponse, HttpStatus.OK);
            // Save the metrics
            URLMetrics metrics = new URLMetrics();

            final Optional<URLMetrics> optionalMetrics = metricsRepository.findByUrlIdAndCaller(id, user);
            if (optionalMetrics.isPresent()) {
                metrics = optionalMetrics.get();
                final int accessCount = metrics.getAccessCount() + 1;
                metrics.setDatetime(LocalDateTime.now());
                metrics.setCaller(user);
                metrics.setAccessCount(accessCount);
            } else {
                metrics.setDatetime(LocalDateTime.now());
                metrics.setCaller(user);
                metrics.setAccessCount(1);
                metrics.setUrlId(id);
            }

            metricsRepository.save(metrics);

        } catch (final Exception e) {
            LOGGER.error("Could not get the long url", e);
            GetLongURLResponse getResponse = new GetLongURLResponse();
            getResponse = new GetLongURLResponse();
            getResponse.setResponseMessage(e.getMessage());

            response = new ResponseEntity<>(getResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        } finally {
            sw.stop();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("End getLongURL() with the response object\n" + response.toString());
            }

        }
        final RedirectView redirectView = new RedirectView();
        if (url.isPresent()) {
            redirectView.setUrl("http://" + url.get().getURL());
        } else {
            throw new Exception("Website not found. 404");
        }

        return redirectView;
    }

}
