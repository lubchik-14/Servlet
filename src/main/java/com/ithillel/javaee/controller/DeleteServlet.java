package com.ithillel.javaee.controller;

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
import java.util.Arrays;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private Service<User, User.UserParameters> userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Arrays.stream(req.getParameterValues("deleteId"))
                .map(Integer::parseInt)
                .forEach(userService::delete);
        resp.sendRedirect("/delete");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("/WEB-INF/delete.jsp").forward(req, resp);
    }
}
