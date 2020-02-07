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

@WebFilter(filterName = "AccessCreationUsersFilter", urlPatterns = "/create" )
public class AccessCreationUsersFilter implements Filter {
    private Service<User, User.UserParameters> userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AccessCreationUsersFilter init");
        this.userService = (UserService) filterConfig.getServletContext().getAttribute("userService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        int userId = (int) session.getAttribute("userId");
        User user = userService.get(userId);
        if (!req.getMethod().equals("GET")) {
            Role role = Role.valueOf(req.getParameter("role").toUpperCase());
            switch (user.getUserParameters().getRole()) {
                case USER: {
                    session.setAttribute("errorMessage", "Creating is not acceptable");
                    resp.sendRedirect(req.getContextPath() + "/home");
                    break;
                }
                case ADMIN: {
                    if (role.equals(Role.USER) || role.equals(Role.SUPPORT)) {
                        chain.doFilter(request, response);
                        break;
                    }
                }
                case SUPPORT: {
                    if (role.equals(Role.USER)) {
                        chain.doFilter(request, response);
                        break;
                    }
                }
                default: {
                    session.removeAttribute("message");
                    session.setAttribute("errorMessage", "Creating with that role is not acceptable");
                    resp.sendRedirect(req.getContextPath() + "/create");
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("AccessCreationUsersFilter destroy");
    }
}