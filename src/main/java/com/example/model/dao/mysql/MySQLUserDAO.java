package com.example.model.dao.mysql;

import com.example.model.dao.UserDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.db.DataSource;
import com.example.model.entity.User;

import java.sql.*;
import java.util.List;

import static com.example.model.constants.Query.*;

public class MySQLUserDAO extends MySQLGenericDAO<User> implements UserDAO {
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

    public User findUserByLogin(String login) throws DAOException {
        return findEntityByField(SELECT_USER_BY_LOGIN, login);
    }

    public void addUser(User user) throws DAOException {
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
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> getNewStudents(String userTypeStudent) throws DAOException {
        return findEntitiesByField(FIND_NEW_USER, userTypeStudent);
    }

    @Override
    public List<User> findByRole(String role) throws DAOException {
        return findEntitiesByField(FIND_BY_ROLE, role);
    }

    @Override
    public void createTeacher(String studentId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(CREATE_TEACHER)) {
            statement.setString(1, "Teacher");
            statement.setString(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void enrollStudentOnCourse(Integer studentId, Integer courseId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(ENROLL_STUDENT_ON_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void leaveCourse(Integer studentId, Integer courseId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(LEAVE_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void updateUserAccess(boolean access, String studentId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_USER_ACCESS)) {
            pst.setBoolean(1, access);
            pst.setString(2, studentId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> findAllGraduates(Integer courseId) throws DAOException {
        return findEntitiesByField(FIND_GRADUATES, courseId);
    }

    @Override
    public User mapToEntity(ResultSet rs) throws DAOException {
        User user;
        try {
            String givenRole = rs.getString("role");
            User.Role role;
            switch (givenRole) {
                case "Admin":
                    role = User.Role.ADMIN;
                    break;
                case "Teacher":
                    role = User.Role.TEACHER;
                    break;
                default:
                    role = User.Role.STUDENT;
                    break;
            }

            user = new User.Builder()
                    .setId(rs.getInt("id"))
                    .setLogin(rs.getString("login"))
                    .setPassword(rs.getString("password"))
                    .setEmail(rs.getString("email"))
                    .setRole(role)
                    .setFirstName(rs.getString("first_name"))
                    .setLastName(rs.getString("last_name"))
                    .setPhoneNumber(rs.getString("phone_number"))
                    .setUserAccess(rs.getBoolean("user_access"))
                    .build();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }
}
