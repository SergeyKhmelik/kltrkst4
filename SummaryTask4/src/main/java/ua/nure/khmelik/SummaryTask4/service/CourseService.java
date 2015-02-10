package ua.nure.khmelik.SummaryTask4.service;

import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.data.CourseData;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseTheme;

public interface CourseService {

    ArrayList<CourseData> getPassedCourses() throws SQLException;
    ArrayList<CourseData> getPassedStudentsCourses(int idStudent) throws SQLException;    
    ArrayList<CourseData> getPassedTeachersCourses(int idTeacher) throws SQLException;
    
    ArrayList<CourseData> getCurrentCourses() throws SQLException;
    ArrayList<CourseData> getCurrentStudentsCourses(int idStudent) throws SQLException;
    ArrayList<CourseData> getCurrentTeachersCourses(int idTeacher) throws SQLException;
    
    ArrayList<CourseData> getIncomingCourses() throws SQLException;  
     
    ArrayList<CourseTheme> getCourseThemes() throws SQLException;

    int createCourse(CourseData course) throws SQLException;

    int updateCourse(CourseData course) throws SQLException;

    int deleteCourse(int idCourse) throws SQLException;
    
    int addStudent(int idCourse, int idStudent) throws SQLException;
    
}