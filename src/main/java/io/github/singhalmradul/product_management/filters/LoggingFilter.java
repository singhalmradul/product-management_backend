package io.github.singhalmradul.product_management.filters;

import static java.util.Collections.list;

import java.io.IOException;

import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(httpRequest);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(httpResponse);

        // Proceed with the filter chain first, so the request body is read and cached
        chain.doFilter(wrappedRequest, wrappedResponse);

        // Log request details
        log.info("Request: {} {}", wrappedRequest.getMethod(), wrappedRequest.getRequestURI());
        log.info("Request Headers: {}", list(wrappedRequest.getHeaderNames()));
        log.info("Request Parameters: {}", wrappedRequest.getParameterMap());

        // Read and log the cached request body
        byte[] requestBody = wrappedRequest.getContentAsByteArray();
        if (requestBody.length > 0) {
            log.info("Request Body: {}", new String(requestBody, wrappedRequest.getCharacterEncoding()));
        } else {
            log.info("Request Body: [empty]");
        }

        // Log response details
        log.info("Response: {}", wrappedResponse.getStatus());
        log.info("Response Headers: {}", wrappedResponse.getHeaderNames());

        // Read and log the cached response body
        byte[] responseBody = wrappedResponse.getContentAsByteArray();
        if (responseBody.length > 0) {
            log.info("Response Body: {}", new String(responseBody, wrappedResponse.getCharacterEncoding()));
        } else {
            log.info("Response Body: [empty]");
        }

        // Ensure the response body is copied back to the response
        wrappedResponse.copyBodyToResponse();
    }

}
