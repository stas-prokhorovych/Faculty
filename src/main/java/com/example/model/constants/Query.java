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
    public static final String BLOCK_USER = "UPDATE user SET user_access = false WHERE id=?";
    public static final String UNBLOCK_USER = "UPDATE user SET user_access = true WHERE id=?";
    public static final String FIND_GRADUATES = "SELECT * FROM user WHERE id IN (SELECT id_user FROM course_student WHERE id_course = ?)";

    // Course queries
    public static final String CREATE_COURSE_WITH_TEACHER = "INSERT INTO course(name, theme, start_date, end_date, id_lecturer,course_status) VALUES(?,?,?,?,?,?)";
    public static final String CREATE_COURSE_NO_TEACHER = "INSERT INTO course(name, theme, start_date, end_date,course_status) VALUES(?,?,?,?,?)";
    public static final String DELETE_COURSE = "DELETE FROM course WHERE id=?";
    public static final String UPDATE_COURSE = "UPDATE course SET name=? WHERE id=?";
    public static final String FIND_ALL_COURSE_BY_ID = "SELECT * FROM course WHERE id=?";
    public static final String FIND_THEMES = "SELECT DISTINCT theme FROM course";
    public static final String FIND_NO_TEACHER_COURSES = "SELECT * FROM course WHERE course_status = 'Closed, no teacher assigned yet'";
    public static final String FIND_ALL_FINISHED_COURSES_BY_TEACHER_ID = "SELECT * FROM course WHERE id_lecturer=? AND course_status = 'Finished'";
    public static final String FIND_ALL_IN_PROGRESS_COURSES_BY_TEACHER_ID = "SELECT * FROM course WHERE id_lecturer=? AND course_status = 'In progress'";
    public static final String FIND_ID_STUDENT_ON_COURSE = "SELECT id FROM course_student WHERE id_user=? AND id_course=?";
    public static final String UPDATE_COURSE_INFO_TO_FINISH = "UPDATE course SET course_status=? WHERE id=?";
    public static final String SELECT_STUDENT_REGISTERED_COURSES = "SELECT * FROM course WHERE course_status='Opened for registration' AND id IN ( SELECT id_course FROM course_student WHERE id_user=?)";
    public static final String SELECT_STUDENT_IN_PROGRESS_COURSES = "SELECT * FROM course WHERE course_status='In progress' AND id IN ( SELECT id_course FROM course_student WHERE id_user=?)";
    public static final String SELECT_STUDENT_FINISHED_COURSES = "SELECT * FROM course WHERE course_status='Finished' AND id IN(SELECT id_course FROM course_student WHERE id_user=?)";
    public static final String FIND_COURSE_BY_NAME = "SELECT * FROM course WHERE name=?";
    public static final String FIND_SELECTED_COURSE = "SELECT id FROM course_student WHERE id_user = ? AND id_course = ?";

    // pagination
    public static final String SELECT_COURSES_LIMIT_HEAD = "SELECT course.* , user.*, (SELECT COUNT(id) FROM course_student WHERE id_course=course.id) as student_enrolled FROM course INNER JOIN user ON course.id_lecturer = user.id ";
    public static final String SELECT_COURSES_LIMIT_TAIL = "LIMIT ?, ?";
    public static final String FIND_NUMBER_OF_RECORDS_HEAD = "SELECT COUNT(course.id) FROM course ";

    // Journal queries
    public static final String WRITE_MARKS_TO_JOURNAL = "INSERT INTO journal(id_student_course, mark) VALUES (?, ?)";
    public static final String FIND_JOURNAL_INFO = "SELECT * FROM journal WHERE journal.id_student_course = (SELECT course_student.id FROM course_student WHERE id_user=? AND id_course =?)";
}
