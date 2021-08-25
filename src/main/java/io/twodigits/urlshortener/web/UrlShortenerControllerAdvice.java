package io.twodigits.urlshortener.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//The controller advice takes precedence over the error pages under resources/public/error/500.html.
//Disabled currently, so that the content of our error pages is displayed.
@ControllerAdvice(basePackageClasses = UrlShortenerController.class)
public class UrlShortenerControllerAdvice extends ResponseEntityExceptionHandler {

    private HttpStatus getStatus(final HttpServletRequest request) {
        final Integer code = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus status = null;
        if (code != null) {
            status = HttpStatus.resolve(code);
        }
        return (status != null) ? status : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleControllerException(final HttpServletRequest request, final Throwable ex) {
        final HttpStatus status = getStatus(request);
        return new ResponseEntity<>(new MyErrorBody(status.value(), ex.getMessage()), status);
    }

}
