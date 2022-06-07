package com.example.model.dao;

import com.example.model.db.DataSource;
import com.example.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.model.query.Query.*;

public class UserDAO {

    public static User findUserByLogin(String login) {
        User user = null;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(SELECT_USER_BY_LOGIN)) {
            pst.setString(1, login);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    user = mapResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    public static void addUser(User user) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getRole().toString());
            pst.setString(5, user.getFirstName());
            pst.setString(6, user.getLastName());
            pst.setString(7, user.getPhoneNumber());
            pst.setBoolean(8, user.isUserAccess());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> findTeachers() {
        List<User> teachers = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(FIND_TEACHERS)) {
            while (rs.next()) {
                teachers.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teachers;
    }

//    public static String findUserPassword(String) {
//
//    }

    private static User mapResultSet(ResultSet rs) {
        User user = null;
        try {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));

            if (rs.getString("role").equals("Admin")) {
                user.setRole(User.Role.ADMIN);
            } else if (rs.getString("role").equals("Teacher")) {
                user.setRole(User.Role.TEACHER);
            } else {
                user.setRole(User.Role.STUDENT);
            }

            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setUserAccess(rs.getBoolean("user_access"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
