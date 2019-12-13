package com.translator.gwa.application.filter;

import brave.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestFilter extends OncePerRequestFilter {

    private Tracer tracer;

    /**
     * RequestFilter constructor.
     *
     * @param tracer Tracer object.
     */
    @Autowired
    public RequestFilter(Tracer tracer) {

        this.tracer = tracer;
    }

    /**
     * ${inheritDoc}
     */
    @Override
    public void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                 FilterChain filterChain) throws IOException, ServletException {

        httpServletRequest.setAttribute("requestId", this.tracer.currentSpan().context().traceIdString());
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
