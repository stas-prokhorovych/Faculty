package com.example.model.dao.mysql;

import com.example.model.dao.GenericDAO;
import com.example.model.dao.UserDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.db.DataSource;
import com.example.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.model.constants.Query.*;

public class MySQLUserDAO extends GenericDAO<User> implements UserDAO {
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

    public List<User> findTeachers() throws DAOException {
        List<User> teachers = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(FIND_TEACHERS)) {
            while (rs.next()) {
                teachers.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return teachers;
    }

    @Override
    public List<User> findStudents() throws DAOException {
        List<User> students = new ArrayList<>();
        try (Connection con = DataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(FIND_STUDENTS)) {
            while (rs.next()) {
                students.add(mapToEntity(rs));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return students;
    }

    @Override
    public void enrollStudentOnCourse(Integer studentId, Integer courseId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_STUDENT_ON_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void blockUser(String studentId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(BLOCK_USER)) {
            pst.setString(1, studentId);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void unblockUser(String studentId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UNBLOCK_USER)) {
            pst.setString(1, studentId);
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
    protected User mapToEntity(ResultSet rs) throws DAOException {
        User user;
        try {
            user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            String role = rs.getString("role");
            switch (role) {
                case "Admin":
                    user.setRole(User.Role.ADMIN);
                    break;
                case "Teacher":
                    user.setRole(User.Role.TEACHER);
                    break;
                default:
                    user.setRole(User.Role.STUDENT);
                    break;
            }
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setUserAccess(rs.getBoolean("user_access"));
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }
}
