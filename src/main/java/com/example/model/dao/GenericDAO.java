package com.example.model.dao;

import com.example.model.dao.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
    /**
     * @param sql query to be executed
     * @param value values to be inserted
     * @return return list of found object
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    <V> List<T> findEntitiesByField(String sql, V... value) throws DAOException;

    /**
     * @param sql sql query to be executed
     * @param value values to be inserted
     * @return return found object
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    <V, T> T findEntityByField(String sql, V value) throws DAOException;

    /**
     *  Help class choose variable type
     *
     * @param value specific value
     * @param statement prepared statement to set parameter
     * @throws SQLException in case of some exception with
     *                      a data source or a connection with it
     */
    <V> void chooseVariableType(V value, PreparedStatement statement) throws SQLException;

    /**
     * Used to map object
     *
     * @param rs rs of give executed statement
     * @return  return mapped object
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    T mapToEntity(ResultSet rs) throws DAOException;
}
