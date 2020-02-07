package com.ithillel.javaee.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/create")
public class StrongPassCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("StrongPassCheckFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String password = request.getParameter("password");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        if (password != null) {
            if (password.trim().length() < 4) {
                session.setAttribute("message", "Try again!");
                session.setAttribute("errorMessage", "Password is too weak");
                resp.sendRedirect(req.getContextPath() + "/create");
            } else {
                session.removeAttribute("errorMessage");
                session.setAttribute("message", "Successful");
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println("StrongPassCheckFilter destroy");
    }
}
