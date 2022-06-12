package com.example.model.service.mysql;

import com.example.model.dao.JournalDAO;
import com.example.model.dao.UserDAO;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.dao.mysql.MySQLUserDAO;
import com.example.model.entity.User;
import com.example.model.exception.ServiceException;
import com.example.model.exception.ServiceWrongLoginException;
import com.example.model.exception.ServiceWrongPasswordException;
import com.example.model.service.JournalService;
import com.example.model.service.UserService;
import com.example.model.utils.Validator;

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
     * @throws ServiceException in case of error occurred with a data source
     *                          or validation of data
     */
    public User getUser(String login, String password) throws ServiceException {
        if (!Validator.isLoginValid(login)) {
            throw new ServiceWrongLoginException("login is not valid");
        }
        if (!Validator.isPasswordValid(password)) {
            throw new ServiceWrongPasswordException("password is not valid");
        }

        User user = userDAO.findUserByLogin(login);

        if (user == null) {
            throw new ServiceWrongLoginException("no user with specified login");
        }
        if (!Validator.isPasswordCorrect(password, user.getPassword())) {
            throw new ServiceWrongPasswordException("password is not correct");
        }

        return user;
    }
}