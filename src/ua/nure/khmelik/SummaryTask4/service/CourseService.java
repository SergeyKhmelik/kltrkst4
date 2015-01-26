package ua.nure.khmelik.SummaryTask4.service;

import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Course;

public interface CourseService {

    ArrayList<Course> getIncomingCourses();

    ArrayList<Course> getIncomingCourses(int teacherId);

    ArrayList<Course> getPassedCourses(int studentId);

    ArrayList<Course> getCurrentCourses(int teacherId);

    int createCourse(Course course);

    int updateCourse(Course course);

    int deleteCourse(int courseId);

}