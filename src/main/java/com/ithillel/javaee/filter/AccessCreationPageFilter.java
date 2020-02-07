package com.ithillel.javaee.filter;

import com.ithillel.javaee.model.Role;
import com.ithillel.javaee.model.User;
import com.ithillel.javaee.services.Service;
import com.ithillel.javaee.services.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AccessCreationPageFilter", urlPatterns = "/create")
public class AccessCreationPageFilter implements Filter {
    private Service<User, User.UserParameters> userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AccessCreationPageFilter init");
        this.userService = (UserService) filterConfig.getServletContext().getAttribute("userService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        int userId = (int) session.getAttribute("userId");
        if (userService.get(userId).getUserParameters().getRole() == Role.USER) {
            session.setAttribute("errorMessage", "Creating is not acceptable");
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("AccessCreationPageFilter destroy");
    }
}