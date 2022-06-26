package com.example.model.utils.pagination;

import java.util.StringJoiner;

import static com.example.model.constants.Query.*;

public class PaginationQueue {
    public static String numberOfPages(String role, String theme, Integer teacher) {
        return FIND_NUMBER_OF_RECORDS_HEAD + addWhereClause(role, theme, teacher);
    }

    public static String makeQueue(String role, String theme, Integer teacher, String sort, String order) {
        return addHead() +
                addWhereClause(role, theme, teacher) +
                addSort(sort, order) +
                addTail();
    }

    private static String addHead() {
        return SELECT_COURSES_LIMIT_HEAD;
    }

    private static String addWhereClause(String role, String theme, Integer teacher) {
        StringJoiner sj = null;

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

    private static String addSort(String sort, String order) {
        if (sort == null) {
            return "";
        }
        if(order == null || order.equals("ascending")) {
            return "ORDER BY " + sort +" ASC ";
        }
        return "ORDER BY " + sort + " DESC ";
    }

    private static String addTail() {
        return SELECT_COURSES_LIMIT_TAIL;
    }
}
