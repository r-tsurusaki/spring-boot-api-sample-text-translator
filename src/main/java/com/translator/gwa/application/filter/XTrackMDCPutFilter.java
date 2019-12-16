package com.translator.gwa.application.filter;

import org.springframework.context.annotation.Configuration;
import org.terasoluna.gfw.web.logging.mdc.AbstractMDCPutFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.regex.Pattern;

@Configuration
public class XTrackMDCPutFilter extends AbstractMDCPutFilter {

    private static final Pattern UUID_REPLACE_PATTERN = Pattern.compile("-");

    private static final String ATTRIBUTE_NAME = "X-Track";

    /**
     * ${inheritDoc}
     */
    @Override
    protected String getMDCKey(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return ATTRIBUTE_NAME;
    }

    /**
     * ${inheritDoc}
     */
    @Override
    protected String getMDCValue(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String xTrack = httpServletRequest.getHeader(ATTRIBUTE_NAME);
        if (xTrack == null) {
            xTrack = createXTrack();
        }
        String cutXTrack = cutValue(xTrack);
        httpServletResponse.setHeader(ATTRIBUTE_NAME, cutXTrack);
        httpServletRequest.setAttribute(ATTRIBUTE_NAME, cutXTrack);
        return cutXTrack;
    }

    private String createXTrack() {
        String uuid = UUID.randomUUID().toString();
        return UUID_REPLACE_PATTERN.matcher(uuid).replaceAll("");
    }
}
