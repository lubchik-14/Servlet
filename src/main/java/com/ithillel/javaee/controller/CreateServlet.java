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
import java.io.IOException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private Service<User, User.UserParameters> userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Role role = Role.valueOf(req.getParameter("role").toUpperCase());
        User newUser = new User(new User.UserParameters(login, password, role));
        int userId = userService.add(newUser);

        req.getSession().setAttribute("message", userId > -1 ? "Created successful" : "User exists");
        resp.sendRedirect("/create");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/create.jsp").forward(req, resp);
    }
}