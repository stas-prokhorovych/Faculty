package com.example.model.dao;

import com.example.model.dao.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
    //<V> List<T> findEntitiesByField(String sql, V value) throws DAOException;

    <V> List<T> findEntitiesByField(String sql, V... value) throws DAOException;

    <V, T> T findEntityByField(String sql, V value) throws DAOException;

    <V> void chooseVariableType(V value, PreparedStatement statement) throws SQLException;

    T mapToEntity(ResultSet rs) throws DAOException;
}
