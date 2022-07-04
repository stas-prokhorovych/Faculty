package com.example.model.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class PaginationQueueTest {
    @Test
    void makeQueueTest() {
        String queue1 = PaginationQueue.makeQueue("Student", "Computer Science", 3, "name", "ascending");
        assertEquals("SELECT course.* , user.*, (SELECT COUNT(id) FROM course_student WHERE id_course=course.id) as student_enrolled " +
                "FROM course " +
                "INNER JOIN user ON course.id_lecturer = user.id  " +
                "WHERE course_status='Opened for registration' AND theme=? AND id_lecturer=? " +
                "ORDER BY name ASC " +
                "LIMIT ?, ?", queue1);

        String queue2 = PaginationQueue.makeQueue(null, null, null, null, "ascending");
        assertEquals("SELECT course.* , user.*, (SELECT COUNT(id) FROM course_student WHERE id_course=course.id) as student_enrolled " +
                "FROM course " +
                "INNER JOIN user ON course.id_lecturer = user.id  " +
                "WHERE course_status='Opened for registration' " +
                "LIMIT ?, ?", queue2);

        String queue3 = PaginationQueue.makeQueue("Student", "Computer Science", 3, "name", "descending");
        assertEquals("SELECT course.* , user.*, (SELECT COUNT(id) FROM course_student WHERE id_course=course.id) as student_enrolled " +
                "FROM course " +
                "INNER JOIN user ON course.id_lecturer = user.id  " +
                "WHERE course_status='Opened for registration' AND theme=? AND id_lecturer=? " +
                "ORDER BY name DESC " +
                "LIMIT ?, ?", queue3);

    }

    @Test
    void numberOfPagesTest() {
        String queue1 = PaginationQueue.numberOfPages("Admin", null, null);
        assertEquals("SELECT COUNT(course.id) FROM course  WHERE course_status<>'Closed, no teacher assigned yet' ", queue1);
    }
}
