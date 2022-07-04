package com.example.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter change local variable value if correspondent request was committed
 */
@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(SessionLocaleFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String languageChange = req.getParameter("sessionLocale");

        if (languageChange != null) {
            LOG.trace("Session locale was changed to: " + languageChange);
            req.getSession().setAttribute("lang", languageChange);
        }
        chain.doFilter(request, response);
    }
}
