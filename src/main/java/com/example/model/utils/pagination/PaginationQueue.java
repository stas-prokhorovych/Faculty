package com.example.model.utils.pagination;

import java.util.StringJoiner;

import static com.example.model.constants.Query.SELECT_COURSES_LIMIT_HEAD;
import static com.example.model.constants.Query.SELECT_COURSES_LIMIT_TAIL;

public class PaginationQueue {
    public static String makeQueue(String type, String theme, Integer teacher, String sort, String order) {
        return addHead() +
                addWhereClause(type, theme, teacher) +
                addSort(sort, order) +
                addTail();
    }

    private static String addHead() {
        return SELECT_COURSES_LIMIT_HEAD;
    }

    private static String addWhereClause(String type, String theme, Integer teacher) {
        if (type == null && theme == null && teacher == null) {
            return "";
        }

        StringJoiner sj = new StringJoiner(" AND ", "WHERE ", " ");

        if (type != null) {
            sj.add("course_status=?");
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
