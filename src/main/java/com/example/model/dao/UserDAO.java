package com.example.model.dao;

import com.example.model.db.DBConnection;
import com.example.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

    public static User findUserByLogin(String login) {
        User user = null;
        try (Connection con = DBConnection.getInstance();
             PreparedStatement pst = con.prepareStatement(SQL_GET_USER_BY_LOGIN)) {
            pst.setString(1, login);
            try(ResultSet rs = pst.executeQuery()) {
                if(rs.next()) {
                    user = mapResultSet(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private static User mapResultSet(ResultSet rs) {
        User user = null;
        try {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
