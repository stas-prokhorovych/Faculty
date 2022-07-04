package com.example.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * Filter used to maintain character encoding UTF-8
 */
@WebFilter(filterName = "EncodingFilter",
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding param"),
                @WebInitParam(name = "content-type", value = "text/html", description = "Content type param")})
public class EncodingFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(EncodingFilter.class);
    private String encodingCode;
    private String contentType;

    public void init(FilterConfig config) {
        LOG.trace("Filter initialization starts");
        encodingCode = config.getInitParameter("encoding");
        contentType = config.getInitParameter("content-type");
        LOG.trace("Filter initialization finished");
    }

    public void destroy() {
        LOG.trace("Filter destruction starts");
        encodingCode = null;
        contentType = null;
        LOG.trace("Filter destruction finished");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        LOG.trace("Filter starts");
        String codeRequest = request.getCharacterEncoding();

        if (encodingCode != null && !encodingCode.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(encodingCode);
            response.setCharacterEncoding(encodingCode);
        }
        if(contentType != null) {
            response.setContentType(contentType);
        }

        LOG.trace("Filter finished");
        chain.doFilter(request, response);
    }
}
