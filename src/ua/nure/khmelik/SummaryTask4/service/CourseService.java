package ua.nure.khmelik.SummaryTask4.service;

import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Course;

public interface CourseService {

    ArrayList<Course> getIncomingCourses() throws SQLException;

    ArrayList<Course> getIncomingCourses(int idTeacher) throws SQLException;

    ArrayList<Course> getPassedCourses(int idStudent) throws SQLException;

    ArrayList<Course> getCurrentCourses(int idTeacher) throws SQLException;

    int createCourse(Course course) throws SQLException;

    int updateCourse(Course course) throws SQLException;

    int deleteCourse(int idCourse) throws SQLException;
    
    int addStudent(int idCourse, int idStudent) throws SQLException;

}