package com.example.model.dao.mysql;

import com.example.model.dao.CourseDAO;
import com.example.model.dao.UserDAO;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.db.DataSource;
import com.example.model.entity.Course;
import com.example.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.model.constants.Query.*;

public class MySQLUserDAO implements UserDAO {
    private static MySQLUserDAO instance;
    private static DaoFactory daoFactory;

    static {
        daoFactory = DaoFactory.getDaoFactory("MYSQL");
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new MySQLUserDAO();
        }
        return instance;
    }

    public User findUserByLogin(String login) {
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

    public void addUser(User user) {
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

    public List<User> findTeachers() {
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

    @Override
    public List<User> findStudents() {
        List<User> students = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(FIND_STUDENTS)) {
            while (rs.next()) {
                students.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public List<User> findTeacherByCourse(List<Course> courses) {
        List<User> result = new ArrayList<>();

        try (Connection con = DataSource.getConnection()) {
            con.setAutoCommit(false);
            for (Course course : courses) {
                try (PreparedStatement pst = con.prepareStatement(FIND_TEACHER_BY_COURSE)) {
                    pst.setInt(1, course.getLecturer());
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            User user = mapResultSet(rs);
                            result.add(user);
                        }
                    }
                }
            }
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void enrollStudentOnCourse(Integer studentId, Integer courseId) {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_STUDENT_ON_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User mapResultSet(ResultSet rs) {
        User user;
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
