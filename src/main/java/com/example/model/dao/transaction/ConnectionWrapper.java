package com.example.model.dao.transaction;

import com.example.model.dao.exception.DAOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Connection wrapper for transaction
 */
public class ConnectionWrapper {

    private static final Logger LOG = LogManager.getLogger(ConnectionWrapper.class);
    private Connection connection;
    private boolean inTransaction = false;

    /**
     * Instantiates a new Connection wrapper.
     *
     * @param connection the connection
     */
    public ConnectionWrapper(Connection connection) {
        this.connection = connection;
    }

    public void beginTransaction() throws DAOException {
        try {
            connection.setAutoCommit(false);
            inTransaction = true;
            LOG.info("Transaction started...");
        } catch (SQLException e) {
            LOG.error("Error while beginning transaction ", e);
            throw new DAOException(e);
        }
    }

    public void commitTransaction() throws DAOException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            inTransaction = false;
            LOG.info("Transaction was committed...");
        } catch (SQLException e) {
            LOG.error("Error while committing transaction ", e);
            throw new DAOException(e);
        }
    }

    public void rollbackTransaction() throws DAOException {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            inTransaction = false;
            LOG.info("Transaction was rolled back... ");
        } catch (SQLException e) {
            LOG.error("Error while rolling back transaction ", e);
            throw new DAOException(e);
        }
    }

    public void close() throws DAOException {
        try {
            if (inTransaction) {
                rollbackTransaction();
            }
            connection.close();
            LOG.info("Connection was closed...");
        } catch (SQLException e) {
            LOG.error("Error while closing transaction ", e);
            throw new DAOException(e);
        }
    }

    public Connection getConnection() {
        LOG.info("Getting connection...");
        return connection;
    }
}
