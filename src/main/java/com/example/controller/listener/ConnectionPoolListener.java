package com.example.controller.listener;

import com.example.model.db.DataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;

/**
 * ConnectionPoolListener is listener for ServletContext initialization
 * and destroying. And used to initialize and destroy DB connection pool.
 */
@WebListener
public class ConnectionPoolListener implements ServletContextListener {
    private static final Logger LOG = LogManager.getLogger(ConnectionPoolListener.class);

    public ConnectionPoolListener() {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DataSource.shutdown();
//        LOG.info("Pool successfully initialized");
    }
    @Override
    public void contextInitialized(ServletContextEvent event) {
        DataSource.getInstance();
//        LOG.info("Pool successfully destroyed");
    }
}
