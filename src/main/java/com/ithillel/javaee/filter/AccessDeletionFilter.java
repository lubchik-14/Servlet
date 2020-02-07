package com.ithillel.javaee.filter;

import com.ithillel.javaee.model.Role;
import com.ithillel.javaee.model.User;
import com.ithillel.javaee.services.Service;
import com.ithillel.javaee.services.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "AccessDeletionFilter", urlPatterns = "/delete")
public class AccessDeletionFilter implements Filter {
    private Service<User, User.UserParameters> userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("AccessDeletionFilter init");
        this.userService = (UserService) filterConfig.getServletContext().getAttribute("userService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("AccessDeletionFilter start");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        int userId = (int) session.getAttribute("userId");
        Role userRole = userService.get(userId).getUserParameters().getRole();
        List<Role> restrictions = new ArrayList<>();
        restrictions.add(Role.ADMIN);

        switch (userRole) {
            case SUPPORT: {
                restrictions.add(Role.SUPPORT);
                break;
            }
            case USER: {
                restrictions.add(Role.SUPPORT);
                restrictions.add(Role.USER);
                break;
            }
        }

        session.setAttribute("restrictions", restrictions);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("AccessDeletionFilter destroy");
    }
}