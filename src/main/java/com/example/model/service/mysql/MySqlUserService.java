package com.example.model.service.mysql;

import com.example.model.dao.UserDAO;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.entity.Course;
import com.example.model.entity.User;
import com.example.model.exception.UserServiceException;
import com.example.model.service.UserService;
import com.example.model.utils.Validator;

import java.util.List;

public class MySqlUserService implements UserService {

    private static UserDAO userDAO;
    private static DaoFactory daoFactory;
    private static MySqlUserService instance;

    private MySqlUserService() {
        try{
            daoFactory = DaoFactory.getDaoFactory("MYSQL");
            userDAO = daoFactory.getUserDAO();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static UserService getInstance() {
        if(instance == null) {
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
     *                          or validation of data
     */
    @Override
    public User getUser(String login, String password) throws UserServiceException {

        User user = userDAO.findUserByLogin(login);

        if (user == null) {
            throw new UserServiceException("no user with specified login");
        }
        if (!Validator.isPasswordCorrect(password, user.getPassword())) {
            throw new UserServiceException("password is not correct");
        }

        return user;
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public List<User> getAllStudents() {
        return userDAO.findStudents();
    }

    @Override
    public List<User> getAllTeachers() {
        return userDAO.findTeachers();
    }

    @Override
    public List<User> findTeacherByCourse(List<Course> courses) {
        return userDAO.findTeacherByCourse(courses);
    }

    @Override
    public void enrollStudentOnCourse(Integer studentId, Integer courseId) {
        userDAO.enrollStudentOnCourse(studentId, courseId);
    }
}
