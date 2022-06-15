package com.example.controller.filter;

import com.example.model.constants.RoleEnum;
import com.example.model.constants.UserAccess;
import com.example.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebFilter(filterName = "SecurityFilter",
        urlPatterns = {"/*"}
)
public class SecurityFilter implements Filter {

    /**
     * Map of restricted pages according to user ROLE.
     */
    private HashMap<RoleEnum, List<String>> urls = new HashMap<RoleEnum, List<String>>() {
        {
            put(RoleEnum.COMMON, UserAccess.getCommonUrls());
            put(RoleEnum.STUDENT, UserAccess.getStudentUrls());
            put(RoleEnum.TEACHER, UserAccess.getTeacherUrls());
            put(RoleEnum.ADMIN, UserAccess.getAdminUrls());
        }
    };

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = null;
        String attribute = null;
        if(req.getQueryString() == null) {
            path = req.getServletPath();
        } else {
            path = req.getServletPath();
            attribute = req.getQueryString();
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
        urls = null;
    }
}
