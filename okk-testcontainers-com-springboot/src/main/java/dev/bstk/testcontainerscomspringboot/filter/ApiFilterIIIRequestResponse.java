package dev.bstk.testcontainerscomspringboot.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@Order(1)
@Component
public class ApiFilterIIIRequestResponse extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            final var requestWrapper = new ContentCachingRequestWrapper(request);
            final var responseWrapper = new ContentCachingResponseWrapper(response);

            filterChain.doFilter(requestWrapper, responseWrapper);

            logRequestBody(requestWrapper);
            logResponseBody(responseWrapper);
        }
    }

    private void logRequestBody(ContentCachingRequestWrapper request) {
        final var content = request.getContentAsByteArray();
        logContent(content);
    }

    private void logResponseBody(ContentCachingResponseWrapper response) throws IOException {
        final var content = response.getContentAsByteArray();
        logContent(content);
        response.copyBodyToResponse();
    }

    private void logContent(byte[] content) {
        if (content.length > 0) {
            final var body = new String(content, StandardCharsets.UTF_8);
            log.info("\n{}\n", body);
        }
    }
}
