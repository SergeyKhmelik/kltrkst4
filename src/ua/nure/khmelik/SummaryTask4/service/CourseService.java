package ua.nure.khmelik.SummaryTask4.service;

import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Course;

public interface CourseService {

    ArrayList<Course> getIncomingCourses();

    ArrayList<Course> getIncomingCourses(int idTeacher);

    ArrayList<Course> getPassedCourses(int idStudent);

    ArrayList<Course> getCurrentCourses(int idTeacher);

    int createCourse(Course course);

    int updateCourse(Course course);

    int deleteCourse(int idCourse);

}