package com.example.model.constants;

public final class Query {
    private Query() {
    }

    // User queries
    public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";
    public static final String CREATE_USER = "INSERT INTO user(login, password, email, role, first_name, last_name, phone_number, user_access) VALUES (?,?,?,?,?,?,?,?)";
    public static final String FIND_TEACHERS = "SELECT * FROM user WHERE role = 'Teacher'";

    // Course queries
    public static final String SELECT_COURSES_LIMIT = "SELECT  *  FROM course LIMIT ?, ?";
    public static final String FIND_NUMBER_OF_RECORDS = "SELECT COUNT(id) FROM course";

    public static final String SELECT_COURSES_BY_THEME_LIMIT = "SELECT  *  FROM course  WHERE theme=? LIMIT ?, ?";
    public static final String FIND_NUMBER_OF_RECORDS_BY_THEME = "SELECT COUNT(id) FROM course WHERE theme=?";




    public static final String SELECT_COURSES_BY_THEME = "SELECT * FROM course WHERE theme = ?";
    public static final String CREATE_COURSE = "INSERT INTO course(name, theme, start_date, end_date, id_lecturer,course_status) VALUES(?,?,?,?,?,?)";
    public static final String DELETE_COURSE = "DELETE FROM course WHERE id=?";
    public static final String UPDATE_COURSE = "UPDATE course SET name=? WHERE id=?";
    public static final String FIND_COURSE_BY_ID = "SELECT name FROM course WHERE id=?";
    public static final String FIND_THEMES = "SELECT DISTINCT theme FROM course";
    public static final String FIND_COURSES_BY_TEACHER = "SELECT * FROM course WHERE course.id_lecturer = ?";
}
