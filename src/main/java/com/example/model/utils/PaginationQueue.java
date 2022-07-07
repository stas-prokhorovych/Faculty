package com.example.model.utils;

import java.util.StringJoiner;

import static com.example.model.constants.Query.*;

/**
 * Main queue used for pagination
 */
public class PaginationQueue {
    private PaginationQueue() {
    }

    /**
     * @param role role of user to create request
     * @param theme theme if selected
     * @param teacher teacher if selected
     * @return queue to obtain total number of records
     */
    public static String numberOfPages(String role, String theme, Integer teacher) {
        return FIND_NUMBER_OF_RECORDS_HEAD + addWhereClause(role, theme, teacher);
    }

    /**
     * Main queue generator
     *
     * @param role role of the user
     * @param theme theme if selected
     * @param teacher teacher if selected
     * @param sort sort parameter if selected
     * @param order order of sort if selected
     * @return main queue for pagination
     */
    public static String makeQueue(String role, String theme, Integer teacher, String sort, String order) {
        return addHead() +
                addWhereClause(role, theme, teacher) +
                addSort(sort, order) +
                addTail();
    }

    /**
     * @return head of queue
     */
    private static String addHead() {
        return SELECT_COURSES_LIMIT_HEAD;
    }

    /**
     * @param role role of the user
     * @param theme theme if selected
     * @param teacher teacher if selected
     * @return part of pagination queue, where clause
     */
    private static String addWhereClause(String role, String theme, Integer teacher) {
        StringJoiner sj;

        if(role == null || role.equals("Teacher") || role.equals("Student")) {
            sj = new StringJoiner(" AND ", "", " ");
            sj.add(" WHERE course_status='Opened for registration'");
            if (role == null && theme == null && teacher == null) {
                return sj.toString();
            }
        } else {
            sj = new StringJoiner(" AND ", "", " ");
            sj.add(" WHERE course_status<>'Closed, no teacher assigned yet'");
            if (theme == null && teacher == null) {
                return sj.toString();
            }
        }

        if (theme != null) {
            sj.add("theme=?");
        }
        if (teacher != null) {
            sj.add("id_lecturer=?");
        }

        return sj.toString();
    }

    /**
     * @param sort sort parameter if selected
     * @param order order to sort if selected
     * @return part of pagination queue, order by clause
     */
    private static String addSort(String sort, String order) {
        if (sort == null) {
            return "";
        }
        if(order == null || order.equals("ascending")) {
            return "ORDER BY " + sort +" ASC ";
        }
        return "ORDER BY " + sort + " DESC ";
    }

    /**
     * @return tail of the pagination queue
     */
    private static String addTail() {
        return SELECT_COURSES_LIMIT_TAIL;
    }
}
