package com.example.model.dao.factory;

import com.example.model.dao.CourseDAO;
import com.example.model.dao.JournalDAO;
import com.example.model.dao.UserDAO;
import com.example.model.dao.mysql.factory.MySqlDaoFactory;

/**
 * DAO factory
 */
public abstract class DaoFactory {

    public static DaoFactory getDaoFactory (String db) {
        switch (db) {
            case "MYSQL":
                return MySqlDaoFactory.getInstance();
            default:
                throw new IllegalArgumentException();
        }
    }

    public abstract CourseDAO getCourseDAO();

    public abstract JournalDAO getJournalDAO();

    public abstract UserDAO getUserDAO();
}
