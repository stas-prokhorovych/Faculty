package com.example.model.constants;

public final class Query {
    private Query() {
    }

    // User queries
    public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";
    public static final String CREATE_USER = "INSERT INTO user(login, password, email, role, first_name, last_name, phone_number, user_access) VALUES (?,?,?,?,?,?,?,?)";
    public static final String FIND_TEACHERS = "SELECT * FROM user WHERE role = 'Teacher'";
    public static final String FIND_STUDENTS = "SELECT * FROM user WHERE role = 'Student'";
    public static final String CREATE_STUDENT_ON_COURSE = "INSERT INTO course_student(id_user, id_course) VALUES (?, ?)";

    // Course queries
    public static final String SELECT_COURSES_LIMIT = "SELECT  *  FROM course LIMIT ?, ?";
    public static final String SELECT_COURSES_BY_THEME_LIMIT = "SELECT  *  FROM course  WHERE theme=? LIMIT ?, ?";
    public static final String SELECT_COURSES_BY_TEACHER_LIMIT = "SELECT * FROM course WHERE course.id_lecturer = ? LIMIT ?, ?";
    public static final String FIND_NUMBER_OF_RECORDS = "SELECT COUNT(id) FROM course";
    public static final String FIND_NUMBER_OF_RECORDS_BY_THEME = "SELECT COUNT(id) FROM course WHERE theme=?";
    public static final String FIND_NUMBER_OF_RECORDS_BY_TEACHER = "SELECT COUNT(id) FROM course WHERE id_lecturer=?";
    public static final String SELECT_COURSES_BY_THEME = "SELECT * FROM course WHERE theme = ?";
    public static final String CREATE_COURSE = "INSERT INTO course(name, theme, start_date, end_date, id_lecturer,course_status) VALUES(?,?,?,?,?,?)";
    public static final String DELETE_COURSE = "DELETE FROM course WHERE id=?";
    public static final String UPDATE_COURSE = "UPDATE course SET name=? WHERE id=?";
    public static final String FIND_COURSE_BY_ID = "SELECT name FROM course WHERE id=?";
    public static final String FIND_THEMES = "SELECT DISTINCT theme FROM course";
    public static final String FIND_NO_TEACHER_COURSES = "SELECT * FROM course WHERE course_status = 'Closed, no teacher assigned yet'";
    public static final String FIND_STUDENTS_ENROLLED = "SELECT COUNT(id) FROM course_student WHERE id_course=?";
    public static final String FIND_TEACHER_BY_COURSE = "SELECT * FROM user WHERE user.id = ?";
    public static final String FIND_ALL_FINISHED_COURSES_BY_TEACHER_ID = "SELECT * FROM course WHERE id_lecturer=? AND course_status = 'Finished'";
}
