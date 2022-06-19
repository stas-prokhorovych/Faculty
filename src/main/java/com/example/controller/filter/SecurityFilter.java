package com.example.controller.filter;

import com.example.model.constants.UserAccess;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.example.model.constants.Pages.LOGIN_PAGE;

@WebFilter(filterName = "SecurityFilter",
        urlPatterns = {"/*"}
)
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

        String path = req.getServletPath();;
//        String attribute = null;
        HttpSession session = req.getSession();

        String role = (String) session.getAttribute("role");

        if (Objects.isNull(role)) {
            role = "Guest";
//            session.setAttribute("role", role);
        }

//        System.out.println(path);
        List<String> accessibleUrls = urls.get(role);
//        if (!accessibleUrls.contains(path)) {
//            System.out.println("HERE");
////            res.sendRedirect(LOGIN_PAGE);
//        } else {
//            System.out.println("AND HERE");
////            chain.doFilter(req, res);
//        }

//
//        // here .jsp files
//        if(req.getQueryString() == null) {
//            path = req.getServletPath();
//
//        }
        // here commands
//        else {
//            path = req.getServletPath();
//            attribute = req.getQueryString();
//            System.out.println(path);
//            System.out.println(attribute);
//        }

        chain.doFilter(request, response);
    }

    public void destroy() {
        urls = null;
    }
}
