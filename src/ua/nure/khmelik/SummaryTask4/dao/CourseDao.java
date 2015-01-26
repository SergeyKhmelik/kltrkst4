package ua.nure.khmelik.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import ua.nure.khmelik.SummaryTask4.entity.dbentities.Course;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.CourseTheme;
import ua.nure.khmelik.SummaryTask4.entity.dbentities.StudentCourse;

public interface CourseDao {

    ArrayList<Course> getIncomingCourses(Connection conn) throws SQLException;

    ArrayList<Course> getIncomingCourses(Connection conn, int teacherId)
	    throws SQLException;

    ArrayList<Course> getPassedCourses(Connection conn, int studentId)
	    throws SQLException;

    ArrayList<Course> getCurrentCourses(Connection conn, int teacherId)
	    throws SQLException;

    ArrayList<CourseTheme> getCourseThemes(Connection conn) throws SQLException;

    int createCourse(Connection conn, Course course) throws SQLException;

    int updateCourse(Connection conn, Course course) throws SQLException;

    int deleteCourse(Connection conn, int courseId) throws SQLException;

    int addStudent(Connection conn, StudentCourse stc) throws SQLException;
    
}
