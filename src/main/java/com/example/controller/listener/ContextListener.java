package com.example.controller.listener;

import com.example.model.db.DataSource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DataSource.shutdown();
    }
}
