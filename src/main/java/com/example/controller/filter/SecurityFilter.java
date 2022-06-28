package com.example.controller.filter;

import com.example.model.constants.UserAccess;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.example.model.constants.Pages.LOGIN_PAGE;
import static com.example.model.constants.Pages.PROFILE_PAGE;

@WebFilter(filterName = "SecurityFilter",
        urlPatterns = {"/*"}
)

/**
 * Security filter manages application authorization access.
 * By default user has anonymous role. Trying access to restricted pages redirect user to login page.
 */

public class SecurityFilter implements Filter {

    /**
     * Map of restricted pages according to user ROLE.
     */
    private HashMap<String, List<String>> urls = new HashMap<String, List<String>>() {
        {
            put("Guest", UserAccess.getGuestUrls());
            put("Student", UserAccess.getStudentUrls());
            put("Teacher", UserAccess.getTeacherUrls());
            put("Admin", UserAccess.getAdminUrls());
        }
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path;
        if (req.getServletPath() != null && !req.getServletPath().equals("/controller")) {
            path = req.getServletPath();
        } else {
            path = req.getQueryString();
            // remove url attributes
            // ex: command=COURSE_CATALOGUE&page=1 => command=COURSE_CATALOGUE
            if (path.contains("&")) {
                String[] parts = path.split("&");
                path = parts[0];
            }
        }

        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");

        if (Objects.isNull(role)) {
            role = "Guest";
        }

        Boolean access = (Boolean) session.getAttribute("access");
        if(!Objects.isNull(access)) {
            if(!access && !path.equals("command=LOGOUT")) {
                request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
                return;
            }
        }

        List<String> accessibleUrls = urls.get(role);
        if (!accessibleUrls.contains(path)) {
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        } else {
            chain.doFilter(req, res);
        }
    }

    public void destroy() {
        urls = null;
    }
}
