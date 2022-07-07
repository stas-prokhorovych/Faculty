package com.example.model.service.mysql;

import com.example.model.dao.UserDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.entity.User;
import com.example.model.service.UserService;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.exception.UserServiceException;
import com.example.model.utils.PasswordHelper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class MySqlUserService implements UserService {
    private static final Logger LOG = LogManager.getLogger(MySqlUserService.class);

    private static UserDAO userDAO;
    private static DaoFactory daoFactory;
    private static MySqlUserService instance;

    private MySqlUserService() {
        try {
            daoFactory = DaoFactory.getDaoFactory("MYSQL");
            userDAO = daoFactory.getUserDAO();
        } catch (IllegalArgumentException e) {
            LOG.error("Cannot get user dao factory", e);
            e.printStackTrace();
        }
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new MySqlUserService();
        }
        return instance;
    }

    /**
     * Return a user from a data source by id and role
     *
     * @param login    a login of user
     * @param password a password of the user
     * @return a user
     * @throws UserServiceException in case of error occurred with a data source
     *                              or validation of data
     */
    @Override
    public User getUser(String login, String password) throws ServiceException {
        User user;
        try {
            user = userDAO.findUserByLogin(login);
        } catch (DAOException e) {
            LOG.error("Cannot get user", e);
            throw new ServiceException(e);
        }

        if (user == null) {
            throw new UserServiceException("no user with specified login");
        }
        if (!PasswordHelper.match(password, user.getPassword())) {
            throw new UserServiceException("password is not correct");
        }
        return user;
    }

    /**
     * @param user to add
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void addUser(User user) throws ServiceException {
        try {
            userDAO.addUser(user);
        } catch (DAOException e) {
            LOG.error("Cannot add user", e);
            throw new ServiceException(e);
        }
    }

    /**
     * @param studentId student to start study
     * @param courseId course that student will be study
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void enrollStudentOnCourse(Integer studentId, Integer courseId) throws ServiceException {
        try {
            userDAO.enrollStudentOnCourse(studentId, courseId);
        } catch (DAOException e) {
            LOG.error("Cannot enroll student on course", e);
            throw new ServiceException(e);
        }
    }

    /**
     * @param courseId course that finished
     * @return students that finish this course
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public List<User> findAllGraduates(Integer courseId) throws ServiceException {
        try {
            return userDAO.findAllGraduates(courseId);
        } catch (DAOException e) {
            LOG.error("Cannot find all graduates", e);
            throw new ServiceException(e);
        }
    }

    /**
     * @param login login of the user
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void findUserByLogin(String login) throws ServiceException {
        User user;
        try {
            user = userDAO.findUserByLogin(login);
        } catch (DAOException e) {
            LOG.error("Cannot find user by login", e);
            throw new ServiceException(e);
        }
        if (user != null) {
            throw new UserServiceException("user with such login already exist");
        }
    }

    /**
     * @param studentId if of student that will leave course
     * @param courseId course that student will leave
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void leaveCourse(Integer studentId, Integer courseId) throws ServiceException {
        try {
            userDAO.leaveCourse(studentId, courseId);
        } catch (DAOException e) {
            LOG.error("Cannot leave course", e);
            throw new ServiceException(e);
        }
    }

    /**
     * @param studentId student that ought to become teacher
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void createTeacher(String studentId) throws ServiceException {
        try {
            userDAO.createTeacher(studentId);
        } catch (DAOException e) {
            LOG.error("Cannot create teacher", e);
            throw new ServiceException(e);
        }
    }

    /**
     * @param userTypeStudent student type
     * @return newly created user
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public List<User> getNewStudents(String userTypeStudent) throws ServiceException {
        try {
            return userDAO.getNewStudents(userTypeStudent);
        } catch (DAOException e) {
            LOG.error("Cannot get new student", e);
            throw new ServiceException(e);
        }
    }

    /**
     * @param role specified role
     * @return user with such role
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public List<User> findByRole(String role) throws ServiceException {
        try {
            return userDAO.findByRole(role);
        } catch (DAOException e) {
            LOG.error("Cannot find user by role", e);
            throw new ServiceException(e);
        }
    }

    /**
     * @param access access that will be changed
     * @param studentId student that access will be changed
     * @throws ServiceException dao layer error or validation error
     */
    @Override
    public void updateUserAccess(boolean access, String studentId) throws ServiceException {
        try {
            userDAO.updateUserAccess(access, studentId);
        } catch (DAOException e) {
            LOG.error("Cannot update user access", e);
            throw new ServiceException(e);
        }
    }
}
