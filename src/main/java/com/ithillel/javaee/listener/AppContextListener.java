package com.ithillel.javaee.listener;

import com.ithillel.javaee.data.Database;
import com.ithillel.javaee.model.Role;
import com.ithillel.javaee.model.User;
import com.ithillel.javaee.services.UserService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Arrays;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Database database = new Database();

//        not work
//        UserYamlGetter getter = new UserYamlGetter();
//        database.putUser(getter.getParameters("admin.yml"));
//        database.putUser(getter.getParameters("support.yml"));

        database.putUser(new User.UserParameters("admin", "admin", Role.ADMIN));
        database.putUser(new User.UserParameters("support", "support", Role.SUPPORT));

        sce.getServletContext().setAttribute("roleList", Arrays.asList(Role.values()));
        sce.getServletContext().setAttribute("database", database);
        sce.getServletContext().setAttribute("userService", new UserService(database));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("roleList");
        sce.getServletContext().removeAttribute("database");
        sce.getServletContext().removeAttribute("userService");
    }
}
