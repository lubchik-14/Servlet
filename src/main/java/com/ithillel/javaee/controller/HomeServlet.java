package com.ithillel.javaee.controller;

import com.ithillel.javaee.model.Role;
import com.ithillel.javaee.model.User;
import com.ithillel.javaee.services.Service;
import com.ithillel.javaee.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private Service<User, User.UserParameters> userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        Map<String, String[]> parameters = req.getParameterMap();

        int userId = userService.getId(new User.UserParameters(parameters.get("login")[0],
                parameters.get("password")[0],
                Role.valueOf(parameters.get("role")[0].toUpperCase())));

        if (userId > -1) {
            session.removeAttribute("errorMessage");
            session.removeAttribute("message");
            session.setAttribute("userId", userId);
            session.setAttribute("userName", userService.get(userId).getUserParameters().getLogin());
        } else {
            session.setAttribute("errorMessage", "User not found");
        }
        resp.sendRedirect("/home");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String referrer = req.getHeader("referer");
        if (referrer != null) {
            if (referrer.equals("http://localhost:8080/create")) {
                session.removeAttribute("message");
                session.removeAttribute("errorMessage");
            }
        }
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }
}