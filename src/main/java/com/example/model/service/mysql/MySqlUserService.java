package com.example.model.service.mysql;

import com.example.model.dao.UserDAO;
import com.example.model.dao.exception.DAOException;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.entity.User;
import com.example.model.service.exception.ServiceException;
import com.example.model.service.exception.UserServiceException;
import com.example.model.service.UserService;
import com.example.model.utils.Validator;

import java.util.List;

public class MySqlUserService implements UserService {

    private static UserDAO userDAO;
    private static DaoFactory daoFactory;
    private static MySqlUserService instance;

    private MySqlUserService() {
        try {
            daoFactory = DaoFactory.getDaoFactory("MYSQL");
            userDAO = daoFactory.getUserDAO();
        } catch (IllegalArgumentException e) {
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
            throw new ServiceException(e);
        }

        if (user == null) {
            throw new UserServiceException("no user with specified login");
        }
        if (!Validator.isPasswordsMatch(password, user.getPassword())) {
            throw new UserServiceException("password is not correct");
        }

        return user;
    }

    @Override
    public void addUser(User user) throws ServiceException {
        try {
            userDAO.addUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllStudents() throws ServiceException {
        try {
            return userDAO.findStudents();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getAllTeachers() throws ServiceException {
        try {
            return userDAO.findTeachers();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void enrollStudentOnCourse(Integer studentId, Integer courseId) throws ServiceException {
        try {
            userDAO.enrollStudentOnCourse(studentId, courseId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void blockUser(String studentId) throws ServiceException {
        try {
            userDAO.blockUser(studentId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllGraduates(Integer courseId) throws ServiceException {
        try {
            return userDAO.findAllGraduates(courseId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void unblockUser(String studentId) throws ServiceException {
        try {
            userDAO.unblockUser(studentId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void findUserByLogin(String login) throws ServiceException {
        User user;
        try {
            user = userDAO.findUserByLogin(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        if (user != null) {
            throw new UserServiceException("user with such login already exist");
        }
    }

    @Override
    public void leaveCourse(Integer studentId, Integer courseId) throws ServiceException {
        try {
            userDAO.leaveCourse(studentId, courseId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> getNewStudents() throws ServiceException {
        try {
            return userDAO.getNewStudents();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void createTeacher(String studentId) throws ServiceException {
        try {
            userDAO.createTeacher(studentId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserById(String studentId) throws ServiceException {
        try {
            return userDAO.findUserById(studentId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
