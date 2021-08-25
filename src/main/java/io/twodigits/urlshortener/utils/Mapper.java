package io.twodigits.urlshortener.utils;

import io.twodigits.urlshortener.dto.CreateShortURLResponse;
import io.twodigits.urlshortener.dto.DeleteShortURLResponse;
import io.twodigits.urlshortener.dto.GetLongURLResponse;
import io.twodigits.urlshortener.dto.GetURLMetricsResponse;
import io.twodigits.urlshortener.dto.GetURLMetricsResponse.eachMetric;
import io.twodigits.urlshortener.model.URL;
import io.twodigits.urlshortener.model.URLMetrics;

public class Mapper {
    public static CreateShortURLResponse convertCreateURLEntityToResponse(final URL shortURL) {
        final CreateShortURLResponse response = new CreateShortURLResponse();
        response.setId(shortURL.getId());
        response.setUrl(shortURL.getURL());
        response.setUser(shortURL.getUser());

        return response;
    }

    public static DeleteShortURLResponse convertDeleteURLEntityToResponse(final Object object) {
        final DeleteShortURLResponse response = new DeleteShortURLResponse();

        return response;
    }

    public static GetLongURLResponse convertGetURLEntityToResponse(final URL shortURL) {
        final GetLongURLResponse response = new GetLongURLResponse();

        response.setUrl(shortURL.getURL());
        response.setUser(shortURL.getUser());

        return response;
    }

    public static GetURLMetricsResponse convertGetURLMetricsEntityToResponse(final Iterable<URLMetrics> metrics) {
        final GetURLMetricsResponse response = new GetURLMetricsResponse();

        for (final URLMetrics metric : metrics) {
            final eachMetric eachmetric = new eachMetric();

            eachmetric.setAccessCount(metric.getAccessCount());
            eachmetric.setCaller(metric.getCaller());
            eachmetric.setLastAccessedDatetime(metric.getDatetime());
            eachmetric.setUrlId(metric.getUrlId());
            eachmetric.setId(metric.getId());

            response.getMetrics().add(eachmetric);
        }
        return response;
    }
}
