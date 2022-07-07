package com.example.model.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Data source of the whole web-application.
 */
public class DataSource {
    private static final Logger LOG = LogManager.getLogger(DataSource.class);

    private static HikariDataSource dataSource;
    private static volatile DataSource instance;

    /**
     *  Set configuration for data source
     *  invoke only one time
     */
    private DataSource() {
        Properties properties = loadProperties();
        HikariConfig config = new HikariConfig();

        config.setDriverClassName(properties.getProperty("driver"));
        config.setJdbcUrl(properties.getProperty("url"));
        config.setUsername(properties.getProperty("user"));
        config.setPassword(properties.getProperty("password"));

        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);
    }

    /**
     * @return recourse with give file with db configuration
     */
    private Properties loadProperties() {
        Properties properties = new Properties();
        try {
            properties.load(DataSource.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            LOG.error("Load properties error", e);
        }
        return properties;
    }

    /**
     * @return open new connection
     * @throws SQLException in case of some exception with
     *                      a data source or a connection with it
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Shutdown data source
     */
    public static void shutdown() {
        dataSource.close();
    }

    /**
     * Get instance of data source
     * Invoke only one time
     *
     * @return data source of the app
     */
    public static DataSource getInstance() {
        DataSource localInstance = instance;
        if (localInstance == null) {
            synchronized (DataSource.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new DataSource();
            }
        }
        return localInstance;
    }
}
