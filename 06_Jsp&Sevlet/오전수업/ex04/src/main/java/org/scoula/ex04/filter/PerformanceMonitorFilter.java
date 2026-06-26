package org.scoula.ex04.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

public class PerformanceMonitorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        LocalDateTime receivedAt = LocalDateTime.now();
        long start = System.currentTimeMillis();

        try {
            chain.doFilter(request, response);
        } finally {
            long elapsedTime = System.currentTimeMillis() - start;
            System.out.printf(
                    "[%s] %s - %dms 소요%n",
                    receivedAt,
                    httpRequest.getRequestURI(),
                    elapsedTime
            );
        }
    }
}
