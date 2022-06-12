package com.example.model.dao.mysql.factory;

import com.example.model.dao.CourseDAO;
import com.example.model.dao.JournalDAO;
import com.example.model.dao.UserDAO;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.dao.mysql.MySQLCourseDAO;
import com.example.model.dao.mysql.MySQLJournalDAO;
import com.example.model.dao.mysql.MySQLUserDAO;

import java.sql.Connection;

public class MySqlDaoFactory extends DaoFactory {

    private static MySqlDaoFactory instance;

    public static DaoFactory getInstance() {
        if(instance == null) {
            instance = new MySqlDaoFactory();
        }
        return instance;
    }

    @Override
    public CourseDAO getCourseDAO() {
        return MySQLCourseDAO.getInstance();
    }

    @Override
    public JournalDAO getJournalDAO() {
        return MySQLJournalDAO.getInstance();
    }

    @Override
    public UserDAO getUserDAO() {
        return MySQLUserDAO.getInstance();
    }

}
