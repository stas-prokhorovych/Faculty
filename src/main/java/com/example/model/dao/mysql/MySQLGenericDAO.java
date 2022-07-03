package com.example.model.dao.mysql;

import com.example.model.dao.GenericDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.db.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class MySQLGenericDAO<T> implements GenericDAO<T> {
    @SafeVarargs
    public final <V> List<T> findEntitiesByField(String sql, V... values) throws DAOException {
        List<T> list = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            for (int i = 1; i <= values.length; i++) {
                V value = values[i - 1];
                switch (value.getClass().getSimpleName()) {
                    case "Integer":
                        statement.setInt(i, (Integer) value);
                        break;
                    case "String":
                        statement.setString(i, (String) value);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
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

    public <V, T> T findEntityByField(String sql, V value) throws DAOException {
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

    public  <V> void chooseVariableType(V value, PreparedStatement statement) throws SQLException {
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
}