package com.example.model.query;

public class Query {
    private Query() {
    }

    public static class UserQuery {
        private UserQuery() {
        }

        public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";
        public static final String CREATE_USER = "INSERT INTO user(login, password, email, role, first_name, last_name, phone_number, user_access) VALUES (?,?,?,?,?,?,?,?)";
        public static final String FIND_TEACHERS = "SELECT DISTINCT user.* \n" +
                                                   "FROM user \n" +
                                                   "INNER JOIN course ON user.id = course.id_lecturer;";


    }

    public static class CourseQuery {
        private CourseQuery() {
        }

        public static final String SELECT_COURSES_BY_THEME = "SELECT * FROM course WHERE theme = ?";
        public static final String CREATE_COURSE = "INSERT INTO course(name, theme, start_date, end_date, id_lecturer,course_status) VALUES(?,?,?,?,?,?)";
        public static final String DELETE_COURSE = "DELETE FROM course WHERE id=?";
        public static final String UPDATE_COURSE = "UPDATE course SET name=? WHERE id=?";
        public static final String FIND_COURSE_BY_ID = "SELECT name FROM course WHERE id=?";
        public static final String FIND_THEMES = "SELECT DISTINCT theme FROM course";
        public static final String FIND_COURSES_BY_TEACHER = "SELECT * FROM course WHERE course.id_lecturer = ?";
    }
}
