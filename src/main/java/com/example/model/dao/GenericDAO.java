package com.example.model.dao;

import com.example.model.dao.exception.DAOException;
import com.example.model.db.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDAO<T> {
    protected <V> List<T> findEntitiesByField(String sql, V value) throws DAOException {
        List<T> list = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            chooseVariableType(value, statement);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    list.add(mapToEntity(rs));
                }
            }
        } catch (SQLException e) {

            throw new DAOException(e);
        }
        return list;
    }

    protected <V, T> T findEntityByField(String sql, V value) throws DAOException {
        T entity = null;

        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            chooseVariableType(value, statement);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    entity = (T)mapToEntity(rs);
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return entity;
    }

    private <V> void chooseVariableType(V value, PreparedStatement statement) throws SQLException {
        switch (value.getClass().getSimpleName()) {
            case "Integer":
                statement.setInt(1, (Integer) value);
                break;
            case "String":
                statement.setString(1, (String) value);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }


    protected abstract T mapToEntity(ResultSet rs) throws DAOException;
}
