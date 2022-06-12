package com.example.model.service.mysql;

import com.example.model.dao.CourseDAO;
import com.example.model.dao.factory.DaoFactory;
import com.example.model.service.CourseService;

public class MySqlCourseService implements CourseService {
    private static CourseDAO courseDAO;
    private static DaoFactory daoFactory;
    private static MySqlCourseService instance;

    private MySqlCourseService() {
        try{
            daoFactory = DaoFactory.getDaoFactory("MYSQL");
            courseDAO = daoFactory.getCourseDAO();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static CourseService getInstance() {
        if(instance == null) {
            instance = new MySqlCourseService();
        }
        return instance;
    }
}
