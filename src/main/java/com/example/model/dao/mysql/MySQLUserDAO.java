package com.example.model.dao.mysql;

import com.example.model.dao.UserDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.db.DataSource;
import com.example.model.entity.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

import static com.example.model.constants.Query.*;

public class MySQLUserDAO extends MySQLGenericDAO<User> implements UserDAO {
    private static final Logger LOG = LogManager.getLogger(MySQLUserDAO.class);

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

    /**
     * @param login login to find user by
     * @return user with such login
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    public User findUserByLogin(String login) throws DAOException {
        return findEntityByField(SELECT_USER_BY_LOGIN, login);
    }

    /**
     * @param email email to find user by
     * @return user if such email exist or null otherwise
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public User findUserByEmail(String email) throws DAOException {
        return findEntityByField(FIND_USER_BY_EMAIL, email);
    }

    /**
     * @param user user to add
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
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
            LOG.error("Unable to add user", e);
            throw new DAOException(e);
        }
    }

    /**
     * @param userTypeStudent type of the user
     * @return student that has to course that he study
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public List<User> getNewStudents(String userTypeStudent) throws DAOException {
        return findEntitiesByField(FIND_NEW_USER, userTypeStudent);
    }

    /**
     * @param role role of user
     * @return list of user by role
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public List<User> findByRole(String role) throws DAOException {
        return findEntitiesByField(FIND_BY_ROLE, role);
    }

    /**
     * @param studentId student id that will be teacher
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public void createTeacher(String studentId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(CREATE_TEACHER)) {
            statement.setString(1, "Teacher");
            statement.setString(2, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Unable to create user", e);
            throw new DAOException(e);
        }
    }

    /**
     * @param studentId student id to enroll
     * @param courseId course id to enroll
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public void enrollStudentOnCourse(Integer studentId, Integer courseId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(ENROLL_STUDENT_ON_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Unable to enroll student on course", e);
            throw new DAOException(e);
        }
    }

    /**
     * @param studentId id student to leave
     * @param courseId id course that student leave
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public void leaveCourse(Integer studentId, Integer courseId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(LEAVE_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Unable to leave course", e);
            throw new DAOException(e);
        }
    }

    /**
     * @param access access to change on
     * @param studentId student id which access will be changed
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public void updateUserAccess(boolean access, String studentId) throws DAOException {
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_USER_ACCESS)) {
            pst.setBoolean(1, access);
            pst.setString(2, studentId);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Unable to update user access", e);
            throw new DAOException(e);
        }
    }

    /**
     * @param studentId id to select
     * @param courseId course to find
     * @return true if student already enrolled
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public boolean studentAlreadyEnrolled(Integer studentId, Integer courseId) throws DAOException {
        boolean studentAlreadyEnrolled = false;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_ID_STUDENT_ON_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    studentAlreadyEnrolled = true;
                }
            }
        } catch (SQLException e) {
            LOG.error("Unable to check student already enrolled on course", e);
            throw new DAOException(e);
        }
        return studentAlreadyEnrolled;
    }

    /**
     * @param studentId id of student
     * @param courseId course of student
     * @return true if student already leave course
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public boolean studentAlreadyLeave(Integer studentId, Integer courseId) throws DAOException {
        boolean studentAlreadyLeave = false;
        try (Connection con = DataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(FIND_ID_STUDENT_ON_COURSE, Statement.RETURN_GENERATED_KEYS)) {
            pst.setInt(1, studentId);
            pst.setInt(2, courseId);
            try (ResultSet rs = pst.executeQuery()) {
                if (!rs.next()) {
                    studentAlreadyLeave = true;
                }
            }
        } catch (SQLException e) {
            LOG.error("Unable to check that course leave", e);
            throw new DAOException(e);
        }
        return studentAlreadyLeave;
    }

    /**
     * @param studentId id by whihh find user
     * @return user if exist or null
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public User findUserById(String studentId) throws DAOException {
        return findEntityByField(FIND_USER_BY_ID, studentId);
    }

    /**
     * @param courseId id of course that finished
     * @return user of such course
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public List<User> findAllGraduates(Integer courseId) throws DAOException {
        return findEntitiesByField(FIND_GRADUATES, courseId);
    }

    /**
     * Used to map object
     *
     * @param rs rs of give executed statement
     * @return  return mapped object
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
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
            LOG.error("Unable to map to entity User", e);
            throw new DAOException(e);
        }
        return user;
    }
}
