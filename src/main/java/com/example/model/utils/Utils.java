package com.example.model.utils;

import com.example.model.entity.Course;

import java.net.PortUnreachableException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {
    private Utils() {
    }

    public static List<Course> sortCourses(List<Course> courses, String sort, String order) {
        if(sort.equals("sort-name")) {
            courses.sort(Comparator.comparing(Course::getName));
        }

        if(sort.equals("sort-duration")) {
            courses.sort(Comparator.comparing(Course::getDurationInDays));
        }

        return orderCourses(courses, sort, order);
    }

    private static List<Course> orderCourses(List<Course> courses, String sort, String order) {
        if (order.equals("descending") && sort.equals("sort-name")) {
            courses.sort(Comparator.comparing(Course::getName).reversed());
        } else if (order.equals("descending") && sort.equals("sort-duration")) {
            courses.sort(Comparator.comparing(Course::getDurationInDays).reversed());
        }
        return courses;
    }


    public static Map<Course, Integer> sortByStudentEnrolled(List<Course> courses, List<Integer> studentsEnrolled, String order) {

        Map<Course, Integer> map = IntStream.range(0, courses.size())
                .boxed()
                .collect(Collectors.toMap(courses::get, studentsEnrolled::get));

        //LinkedHashMap preserve the ordering of elements in which they are inserted
        Map<Course, Integer> result = new LinkedHashMap<>();

        if(order.equals("ascending")) {
            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        } else {
            map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        }
        return result;
    }
}

