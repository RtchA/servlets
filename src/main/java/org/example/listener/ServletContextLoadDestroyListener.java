package org.example.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.example.attribute.ContextAttributes;
import org.example.controller.UserController;
import org.example.handler.WebHandler;
import org.example.manager.UserManager;

import java.util.Map;

@WebListener

public class ServletContextLoadDestroyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final ServletContext servletContext = sce.getServletContext();
        final UserManager userManager = new UserManager();
        final UserController userController = new UserController(userManager);
        final Map<String, WebHandler> handlers = Map.of(
                "/users.getAll", userController::getAll,
                "/users.getById", userController::getById
        );
        servletContext.setAttribute(ContextAttributes.HANDLERS_CONTEXT_ATTR, handlers);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
