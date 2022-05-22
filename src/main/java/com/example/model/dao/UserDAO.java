package com.example.model.dao;

import com.example.model.db.DataSource;
import com.example.model.entity.User;

import java.sql.*;

public class UserDAO {
    private static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";
    private static final String SQL_ADD_USER =
            "INSERT INTO user(login, password, email, role, first_name, last_name, phone_number) VALUES (?,?,?,?,?,?,?)";


    public static User findUserByLogin(String login) {
        User user = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_GET_USER_BY_LOGIN)) {
            pst.setString(1, login);
            try(ResultSet rs = pst.executeQuery()) {
                if(rs.next()) {
                    user = mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public static void addUser(User user) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SQL_ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getRole().toString());
            pst.setString(5, user.getFirstName());
            pst.setString(6, user.getLastName());
            pst.setString(7, user.getPhoneNumber());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static User mapResultSet(ResultSet rs) {
        User user = null;
        try {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setRole(User.Role.STUDENT);
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPhoneNumber(rs.getString("phone_number"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
